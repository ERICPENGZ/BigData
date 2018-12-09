package Complex;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class reducer extends Reducer<Combokey, NullWritable, Text,NullWritable> {

    protected void reduce(Combokey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> it = values.iterator();
        it.next();
        int type = key.getType();
        int cid = key.getCid() ;
        String cinfo = key.getCustomerInfo() ;
        System.out.println(cinfo+"===================================================");
        while(it.hasNext()){
            it.next();
            String oinfo = key.getOrderinfo();
            System.out.println(cinfo);
            context.write(new Text(cinfo + "," + oinfo),NullWritable.get());
        }
    }
}
