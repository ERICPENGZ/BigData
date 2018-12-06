public class maxtempmapper extends Mapper<LongWritable,Text, IntWritable, IntWritable> {
        public maxtempmapper(){
        System.out.println("new maxtemapppr");
        }

    //去除气温
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] arr = line.split(" ");
        System.out.println(arr);
        context.write(new IntWritable(Integer.parseInt(arr[0])),new IntWritable(Integer.parseInt(arr[1])));

    }
}