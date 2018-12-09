package Complex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Map;

public class APP {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");

        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("ReduceJoinApp");                        //作业名称
        job.setJarByClass(APP.class);                 //搜索类

        //添加输入路径
        FileInputFormat.addInputPath(job,new Path("E:\\maps\\fina\\c.txt"));
        FileInputFormat.addInputPath(job,new Path("E:\\maps\\fina\\order.txt"));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path("E:\\mrss"));

        job.setMapperClass(mapers.class);             //mapper类
        job.setReducerClass(reducer.class);           //reducer类

        //设置Map输出类型
        job.setMapOutputKeyClass(Combokey.class);            //
        job.setMapOutputValueClass(NullWritable.class);      //

        //设置ReduceOutput类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);         //

        //设置分区类
        job.setPartitionerClass(mypartition.class);
        //设置分组对比器
        job.setGroupingComparatorClass(GroupCompare.class);
        //设置排序对比器
        job.setSortComparatorClass(combeycompare.class);
        job.setNumReduceTasks(2);                           //reduce个数
        job.waitForCompletion(true);
    }
}
