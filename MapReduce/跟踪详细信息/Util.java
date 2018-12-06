public class Util {
    //汇总输出
    public static  String getInfo(Object o,String msg){
        return getHostName()+":"+getId()+":"+getTid()+":"+getobjectinfo(o)+":"+msg;
    }
    //得到主机名
    public static  String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
    //获得当前程序进程ID
    public static int getId(){
        try {
            String info  = ManagementFactory.getRuntimeMXBean().getName();
            return Integer.parseInt(info.substring(0,info.indexOf('@')));

        }catch (Exception e){

        }
        return 0;
    }
    //获得当前线程ID

    public static String getTid(){
        try {
            return Thread.currentThread().getName();
        }catch (Exception e){

        }
        return  null;
    }
    public static  String getobjectinfo(Object o){
        try {
            //不包含包名本身
            String name = o.getClass().getSimpleName();
            return name+"@"+o.hashCode();
        }catch (Exception e){

        }
        return null;
    }

}