
public class app {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);
        job.setJobName("appDB");
        job.setJarByClass(app.class);
        job.setInputFormatClass(DBInputFormat.class);
        //配置数据库信息
        String driverclass = "com.mysql.jdbc.Driver" ;
        String url = "jdbc:mysql://（IP）:3306/big4" ;//需要在mysql中授权，ip语句在末尾
        String username= "karos";
        String password = "mypassword" ;
        DBConfiguration.configureDB(job.getConfiguration(),driverclass,url,username,password);

        //数据库输入内容
        DBInputFormat.setInput(job,MydbWritable.class,"select id,name,txt from words","select count(*) from words");

        DBOutputFormat.setOutput(job,"stats","word","c");


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
GRANT ALL PRIVILEGES ON *.* TO 'root'@'IP地址' IDENTIFIED BY 'mypassword' WITH GRANT OPTION; 
