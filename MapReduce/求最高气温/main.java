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
        job.setInputFormatClass(TextInputFormat.class);

        //设置输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //可以设置分区
        //job.setPartitionerClass(mypartition.class);

        //设置合成类
        //job.setCombinerClass(MaxtempTemp.class);

        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);
    }
}