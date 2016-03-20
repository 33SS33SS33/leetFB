package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class PowerofTwo {


    public boolean isPowerOfTwo(int n) {
        if(n == 0) return false;
        if(n == 1) return true;
        if(n % 2 == 1) return false;
        return isPowerOfTwo(n / 2);
    }

    /**creek---*/
    public boolean isPowerOfTwoB(int n) {
        if(n<=0)
            return false;

        while(n>2){
            int t = n>>1;
            int c = t<<1;
            if(n-c != 0)
                return false;
            n = n>>1;
        }
        return true;
    }
}
