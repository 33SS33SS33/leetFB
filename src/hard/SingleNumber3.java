package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
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
