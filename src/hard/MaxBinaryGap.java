package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/3/4.
 */

public class MaxBinaryGap {
    public static void main(String[] args) {
        System.out.println(MaxBinaryGap(9));
    }

    public static int MaxBinaryGap(int N) {
        int max = 0;
        int count = -1;
        int r = 0;
        while (N > 0) {
            // get right most bit & shift right
            r = N & 1;
            N = N >> 1;
            if (0 == r && count >= 0) {
                count++;
            }
            if (1 == r) {
                max = count > max ? count : max;
                count = 0;
            }
        }
        return max;
    }

}
