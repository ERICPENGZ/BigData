public class WCapp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
//        System.setProperty("hadoop.home.dir","E:/");
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);
        ///设置job 的属性
        job.setJobName("Wcapp");//作业名称
        job.setJarByClass(WCapp.class);//搜索类
        job.setInputFormatClass(TextInputFormat.class);//设置输入格式
        //添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setMapperClass(maper.class);//mapper类
        job.setReducerClass(reducer.class);//reduce
        job.setNumReduceTasks(1);       //一个输出//reduce 的个数
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);
    }
}