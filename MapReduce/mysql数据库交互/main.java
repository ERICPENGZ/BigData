public class app {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);
        job.setJobName("appDB");
        job.setJarByClass(app.class);
        job.setInputFormatClass(DBInputFormat.class);
        //配置数据库信息
        String driverclass = "com.mysql.jdbc.Driver" ;
        String url = "jdbc:mysql://localhost:3306/big4" ;
        String username= "root";
        String password = "1" ;
        DBConfiguration.configureDB(job.getConfiguration(),driverclass,url,username,password);

        //数据库输入内容
        DBInputFormat.setInput(job,MydbWritable.class,"select id,name,txt from words","select count(*) from words");

        FileOutputFormat.setOutputPath(job,new Path("E:\\map-Mysql\\out"));

        job.setMapperClass(mapper.class);
        job.setReducerClass(reducer.class);

        job.setNumReduceTasks(3);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);



    }
}
