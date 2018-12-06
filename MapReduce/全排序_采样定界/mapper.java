public class maxtempmapper extends Mapper<IntWritable,IntWritable, IntWritable, IntWritable> {
        public maxtempmapper(){
        System.out.println("new maxtemapppr");
        }

    //去除气温
    protected void map(IntWritable key,IntWritable value,Context context) throws IOException, InterruptedException {
        context.write(key,value);


    }
}
