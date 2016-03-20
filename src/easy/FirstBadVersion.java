package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**错的*/
public class FirstBadVersion{
    public static void main(String[] args) {
        System.out.println(new FirstBadVersion().firstBadVersion(3));
    }
    public int firstBadVersion(int n) {
        int good = 0; // not exists
        int bad  = n;

        for(;;) {
            if(bad - good <= 1) return bad;
            int t = (bad - good) / 2 + good; // fuck overflow
            if(isBadVersion(t)){
                bad  = t;
            } else {
                good = t;
            }
        }
    }
    boolean isBadVersion(int version){
        return true;
    }
}
