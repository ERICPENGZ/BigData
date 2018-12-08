
public class reducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for(IntWritable w : values){
            count += w.get();
        }
        context.write(key,new IntWritable(count));
    }
}
