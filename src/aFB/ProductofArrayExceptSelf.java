package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * 解法比较巧妙
 * 由于output[i] = (x0 * x1 * ... * xi-1) * (xi+1 * .... * xn-1)
 * 因此执行两趟循环：
 * 第一趟正向遍历数组，计算x0 ~ xi-1的乘积
 * 第二趟反向遍历数组，计算xi+1 ~ xn-1的乘积
 */
public class ProductofArrayExceptSelf {
    public static void main(String[] args) {
        int[] A = {1, 2, 8, 9, 3, 5};
        ProductofArrayExceptSelf s = new ProductofArrayExceptSelf();
        int[] res = s.productExceptSelf(A);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i == res.length - 1 ? res[i] : res[i] + ", ");
        }
        System.out.println();
        int[] res2 = s.productExceptSelfB(A);
        for (int i = 0; i < res2.length; i++) {
            System.out.print(i == res2.length - 1 ? res2[i] : res2[i] + ", ");
        }
        System.out.println();
    }

    // Space is O(1) 最好的
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    /**
     * creek  空间复杂度高
     */
    public int[] productExceptSelfB(int[] nums) {
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
