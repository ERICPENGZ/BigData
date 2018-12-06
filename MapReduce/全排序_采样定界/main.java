public class maxtemp {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        //1.现在本地跑
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);

        //设置基本信息
        job.setJobName("maxTemp");
        job.setJarByClass(maxtemp.class);
        job.setMapperClass(maxtempmapper.class);
        job.setReducerClass(maxtempreducer.class);
        //设置输入格式
        job.setInputFormatClass(SequenceFileInputFormat.class);

        //设置输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //job.setNumReduceTasks(1);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

         //可以设置分区全排序分区类
        job.setPartitionerClass(TotalOrderPartitioner.class);


        //创建随机采样器对象
        //freq:每个key被选中的概率
        //numberSample:抽取样本的总数
        //maxsplitsampled:最大采样切片数
        InputSampler.Sampler<IntWritable, IntWritable> sampler = new InputSampler.RandomSampler<IntWritable, IntWritable>(0.1,10000,3);


        job.setNumReduceTasks(3);
        TotalOrderPartitioner.setPartitionFile(conf,new Path("file:///E:/mr/par.list"));
        //将sampler数据写入分区文件
        InputSampler.writePartitionFile(job,sampler);

        //设置合成类
        //job.setCombinerClass(MaxtempTemp.class);

        //最后会出来三个part文件,若是多余3个会产生空文件



        job.waitForCompletion(true);

    }

}