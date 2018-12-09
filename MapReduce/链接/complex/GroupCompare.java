package Complex;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/*
* CID分组对比器
* */
public class GroupCompare extends WritableComparator{

    protected GroupCompare() {
        super(Combokey.class, true);
    }

    public int compare(WritableComparable a, WritableComparable b) {
        Combokey k1 = (Combokey) a;
        Combokey k2 = (Combokey) b;
        return k1.getCid() - k2.getCid();
    }
}
