package TopInterview;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class ProductofArrayExceptSelf {
    public static void main(String[] args) {
        int[] A = {1, 2, 8, 9, 3, 5};
        ProductofArrayExceptSelf s = new ProductofArrayExceptSelf();
        int[] res = s.productofArrayExceptSelf(A);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i == res.length - 1 ? res[i] : res[i] + ", ");
        }
    }

    /**
     * 由于output[i] = (x0 * x1 * ... * xi-1) * (xi+1 * .... * xn-1)
     * 因此执行两趟循环：
     * 第一趟正向遍历数组，计算x0 ~ xi-1的乘积
     * 第二趟反向遍历数组，计算xi+1 ~ xn-1的乘积 Space is O(1) 最好的
     */
    public int[] productofArrayExceptSelf(int[] nums) {
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

}
