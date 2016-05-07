package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/7.
 */

/**Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?*/
public class HIndex2 {
    public static void main(String[] args) {
        int[] nums = { 0, 1, 3, 5, 6 };
        System.out.println(new HIndex2().hIndex2(nums));
    }

    public int hIndex2(int[] citations) {
        int len = citations.length;
        int lo = 0, hi = len - 1;
        while (lo <= hi) {
            int med = (hi + lo) / 2;
            if (citations[med] == len - med) {
                return len - med;
            } else if (citations[med] < len - med) {
                lo = med + 1;
            } else {
                //(citations[med] > len-med), med qualified as a hIndex,
                // but we have to continue to search for a higher one.
                hi = med - 1;
            }
        }
        return len - lo;
    }
}
