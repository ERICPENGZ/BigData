package mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("MapJoinApp");                        //作业名称
        job.setJarByClass(App.class);                 //搜索类

        //添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //没有reduce

        job.setNumReduceTasks(0);

        job.setMapperClass(mappersjoin.class);             //mapper类

        job.setMapOutputKeyClass(Text.class);           //
        job.setMapOutputValueClass(NullWritable.class);  //

        job.waitForCompletion(true);
    }
}
