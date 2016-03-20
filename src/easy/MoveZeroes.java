package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/12.
 */
public class MoveZeroes {
    public static void main(String[] args){
        int[] num={1,4,3,0,8,1,0,0};
        int[] num2={1,4,3,0,8,1,0,0};
        new MoveZeroes().moveZeroes1(num);
        for(int i:num){
            System.out.print(i+",");
        }
        System.out.println();
        new MoveZeroes().moveZeroes(num2);
        for(int i:num2){
            System.out.print(i+",");
        }
    }
    /**best*/
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }


    void shiftLeft(int[] nums, int p, final int c){
        if(c <= 0) return;
        while(p < nums.length){
            nums[p - c] = nums[p];
            if(nums[p] == 0) break;
            p++;
        }
        for(int i = p - c; i < p; i++){
            nums[i] = 0;
        }
    }
    public void moveZeroes(int[] nums) {
        int i = nums.length - 1;
        // trim tailing zero
        while(i >= 0 && nums[i] == 0) i--;
        int s = i;
        boolean metzero = false;
        while(i >= 0){
            if(nums[i] != 0){
                if(metzero){
                    shiftLeft(nums, s, s - i - 1);
                    metzero = false;
                }
                s = i;
            } else {
                metzero = true;
            }
            i--;
        }
        shiftLeft(nums, s, s);
    }
}
