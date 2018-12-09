package Complex;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;;
public class combeycompare extends WritableComparator {
    protected combeycompare() {
        super(Combokey.class, true);
    }

    public int compare(WritableComparable a, WritableComparable b) {
        Combokey k1 = (Combokey) a;
        Combokey k2 = (Combokey) b;
        return k1.compareTo(k2);
    }
}