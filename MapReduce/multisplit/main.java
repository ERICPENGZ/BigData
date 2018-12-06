 public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
//        System.setProperty("hadoop.home.dir","E:/");

        //本地模式
       conf.set("fs.defaultFS","file:///");
        //存在则删除目录
//        if(args.length>1){
//            FileSystem.get(conf).delete( new  Path(args[1]));
//      }
        Job job = Job.getInstance(conf);
        ///设置job 的属性
        job.setJobName("Wcapp");//作业名称
        job.setJarByClass(WCapp.class);//搜索类

        //多个输入
        MultipleInputs.addInputPath(job,new Path("file:///E:/map/txt/map.txt"),TextInputFormat.class,maper.class);
        MultipleInputs.addInputPath(job,new Path("file:///E:/map/seq/1.seq"),SequenceFileInputFormat.class,seq_maper.class);

        FileOutputFormat.setOutputPath(job,new Path(args[0]));
//
//        job.setMapperClass(maper.class);//mapper类
//        job.setMapperClass(maper.class);//seqmapper类
        job.setReducerClass(reducer.class);//reduce
        job.setNumReduceTasks(1);       //一个输出//reduce 的个数
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);
    }