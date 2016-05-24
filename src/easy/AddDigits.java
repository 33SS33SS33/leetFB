package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
/**Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 For example:
 Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 Follow up:
 Could you do it without any loop/recursion in O(1) runtime?*/

/**可以通过找规律直接算出来*/
public class AddDigits {
    public static void main(String[] args) {
        System.out.println(addDigits(222));
        System.out.println(addDigits(38));
    }

    public static int addDigits(int num) {
        return num - (num - 1) / 9 * 9;
    }
}
