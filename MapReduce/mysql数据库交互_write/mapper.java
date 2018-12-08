
public class mapper extends Mapper<LongWritable,MydbWritable, Text, IntWritable> {

    /*
    *
    * */
    protected void map(LongWritable key, MydbWritable value, Context context) throws IOException, InterruptedException {
        System.out.println("key:"+key);
        String  line =  value.getTxt();
        String[] arr = line.split(" ");
        System.out.println("id: "+value.getId()+"name: "+value.getName());
        for(String s:arr){
            context.write(new Text(s),new IntWritable(1));
        }
    }
}
