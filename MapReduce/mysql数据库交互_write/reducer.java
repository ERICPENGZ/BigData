
public class reducer extends Reducer<Text, IntWritable,MydbWritable,NullWritable> {

    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for(IntWritable w : values){
            count += w.get();
        }
        MydbWritable key_out =  new MydbWritable();
        key_out.setWord(key.toString());
        key_out.setWordCount(count);
        context.write(key_out, NullWritable.get());
    }
}
