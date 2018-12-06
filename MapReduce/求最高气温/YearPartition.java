package maxTemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class YearPartition extends Partitioner<IntWritable,IntWritable> {

    public int getPartition(IntWritable year, IntWritable temp, int numPartitions) {
        int y0 = year.get() - 1999;
        if (y0<=0){
            return 2;
        }
        else if(y0==1){
            return 1;
        }
        else{
            return 0;
        }

    }
}
