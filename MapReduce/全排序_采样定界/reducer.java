public class maxtempreducer extends Reducer<IntWritable, IntWritable,IntWritable,IntWritable> {
    public maxtempreducer(){
        System.out.println("new maxtempreducer");
    }
    protected  void  reducer(IntWritable key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable iw:values){
            max = max>iw.get() ? max:iw.get();
        }
        context.write(key,new IntWritable(max));
    }
}
