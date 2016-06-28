package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * 就是使用二分法
 */
public class FirstBadVersion {
    public static void main(String[] args) {
        System.out.println(new FirstBadVersion().firstBadVersion(3));
        System.out.println(new FirstBadVersion().firstBadVersionB(3));
    }

    //最好的
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid))
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    public int firstBadVersionB(int n) {
        int good = 0; // not exists
        int bad = n;
        for (; ; ) {
            if (bad - good <= 1)
                return bad;
            int t = (bad - good) / 2 + good; // fuck overflow
            if (isBadVersion(t)) {
                bad = t;
            } else {
                good = t;
            }
        }
    }

    boolean isBadVersion(int version) {
        return true;
    }

}
