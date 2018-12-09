package Complex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class mapers extends Mapper<LongWritable, Text,Combokey, NullWritable> {

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        //判断客户还是订单
        FileSplit split = (FileSplit) context.getInputSplit();
        String path  = split.getPath().toString();
        Combokey key2 = new Combokey();
        //客户信息
        if(path.contains("c")){
            String cid = line.substring(0,line.indexOf(","));
            String custInfo = line ;
            key2.setType(0);
            key2.setCid(Integer.parseInt(cid));
            key2.setCustomerInfo(custInfo);
        }
        //order info
        else{
            String cid = line.substring(line.lastIndexOf(",") + 1);
            String oid = line.substring(0, line.indexOf(","));
            String oinfo = line.substring(0, line.lastIndexOf(","));
            key2.setType(1);
            key2.setCid(Integer.parseInt(cid));
            key2.setOid(Integer.parseInt(oid));
            key2.setOrderinfo(oinfo);
        }
        System.out.println(key2+"+++++++++++++++++++++++++++++++++++++++++++");
        context.write(key2,NullWritable.get());
    }
}
