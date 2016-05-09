package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
/**Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 For example:
 Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 Note:
 The order of the result is not important. So in the above example, [5, 3] is also correct.
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?*/

/**思路诡异 使用异或
 首先 从头到尾异或一遍 得出来的结果是那两个不同数字异或合体的结果
 把他拆出来的逻辑是
 首先 找到异或结果最右边的那个1的位置i 这个位置i有1说明这两个数字这一位是不同的
 这样的话 我们可以把数组分成两部分 这一位i是1的 和这一位i是0的  然后这两个数字肯定分别落在这两个分组中
 所以就可以通过异或第一个分组 找出来一个  异或第二个分组 找出来第二个*/
public class SingleNumber3 {

    public static void main(String[] args) {
        int[] A = {1, 1, 2, 2, 3, 4};
        int[] res=singleNumber(A);
        for(int i: res) {
            System.out.println(i);
        }
    }
    public static int[] singleNumber(int[] nums) {
        int aXORb = nums[0];

        for(int i = 1; i < nums.length; i++){
            aXORb ^= nums[i];
        }
        // this bit either in a or b
        int bit = Integer.lowestOneBit(aXORb);
        int a = 0;
        for(int n : nums){
            if((n & bit) == bit){
                a ^= n;
            }
        }
        return new int[]{a, aXORb ^ a};
    }
}
