package Complex;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Combokey implements WritableComparable<Combokey> {

    private  int type;
    private  int cid;
    private  int oid;
    private String customerInfo = "";
    private String orderinfo = "";

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
    }

    /*
    * 写入
    * */

        public int compareTo(Combokey o) {
            int type0 = o.type ;
            int cid0= o.cid;
            int oid0 = o.oid;
            String customerInfo0 = o.customerInfo;
            String orderInfo0 = o.orderinfo ;
            //是否同一个customer的数据
            if(cid == cid0){
                //同一个客户的两个订单
                if(type == type0){
                    return oid - oid0 ;
                }
                //一个Customer + 他的order
                else{
                    if(type ==0)
                        return -1 ;
                    else
                        return 1 ;
                }
            }
            //cid不同
            else{
                return cid - cid0 ;
            }
        }


    public void write(DataOutput out) throws IOException {
        out.writeInt(type);
        out.writeInt(cid);
        out.writeInt(oid);
        out.writeUTF(customerInfo);
        out.writeUTF(orderinfo);
    }

    public void readFields(DataInput in) throws IOException {
        this.type = in.readInt();
        this.cid = in.readInt();
        this.oid = in.readInt();
        this.customerInfo = in.readUTF();
        this.orderinfo = in.readUTF();
    }
}
