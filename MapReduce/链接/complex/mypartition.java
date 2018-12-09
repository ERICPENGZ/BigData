package Complex;

import Complex.Combokey;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class mypartition extends Partitioner<Combokey,NullWritable>{

    public int getPartition(Combokey key, NullWritable nullWritable, int numPartitions) {
        return key.getCid() % numPartitions;
    }
}
