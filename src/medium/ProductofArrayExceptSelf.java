package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class ProductofArrayExceptSelf {

    public static void main(String[] args) {
        int[] A = { 1, 2, 8, 9, 3, 5 };
        ProductofArrayExceptSelf s=new ProductofArrayExceptSelf();
        int[] res = s.productExceptSelfA(A);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i == res.length - 1 ? res[i] : res[i] + ", ");
        }
        System.out.println();
        int[] res2 = s.productExceptSelfB(A);
        for (int i = 0; i < res2.length; i++) {
            System.out.print(i == res2.length - 1 ? res2[i] : res2[i] + ", ");
        }
        System.out.println();
        int[] res3 = s.productExceptSelfC(A);
        for (int i = 0; i < res3.length; i++) {
            System.out.print(i == res3.length - 1 ? res3[i] : res3[i] + ", ");
        }
        System.out.println();
    }

    public int[] productExceptSelfA(int[] nums) {
        int[] output = new int[nums.length];
        if (nums.length == 0)
            return output;
        output[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i - 1] * nums[i];
        }
        output[nums.length - 1] = output[nums.length - 2] * 1;
        int t = nums[nums.length - 1];
        for (int i = output.length - 2; i > 0; i--) {
            output[i] = t * output[i - 1];
            t *= nums[i];
        }
        output[0] = t;
        return output;
    }

    /**
     * creek  Space is O(1).
     */
    public int[] productExceptSelfB(int[] nums) {
        int[] result = new int[nums.length];
        result[result.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            result[i] = result[i + 1] * nums[i + 1];
        }
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] *= left;
            left *= nums[i];
        }
        return result;
    }

    /**
     * creek-----
     */
    public int[] productExceptSelfC(int[] nums) {
        int[] result = new int[nums.length];
        int[] t1 = new int[nums.length];
        int[] t2 = new int[nums.length];
        t1[0] = 1;
        t2[nums.length - 1] = 1;
        //scan from left to right
        for (int i = 0; i < nums.length - 1; i++) {
            t1[i + 1] = nums[i] * t1[i];
        }
        //scan from right to left
        for (int i = nums.length - 1; i > 0; i--) {
            t2[i - 1] = t2[i] * nums[i];
        }
        //multiply
        for (int i = 0; i < nums.length; i++) {
            result[i] = t1[i] * t2[i];
        }
        return result;
    }

}
