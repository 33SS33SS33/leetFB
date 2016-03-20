package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class AddDigits {

    public static void main(String[] args) {
        int num=222;
        System.out.println(addDigits(num));
    }
    public static int addDigits(int num) {
        return num - (num - 1) / 9 * 9;
    }
}
