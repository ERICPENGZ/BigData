public class maper  extends Mapper<LongWritable, Text,Text, IntWritable> {
        //四个泛型：输入KV，输出的kv
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        Text keyout = new Text();
        IntWritable valueOut = new IntWritable();
        String[] arr = value.toString().split(" ");
        for(String s: arr){
            keyout.set(s);
            valueOut.set(1);
            context.write(keyout,valueOut);
        }
    }
}
