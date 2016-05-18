package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/18.
 */
public class FindtheDuplicateNum {
    public static void main(String[] args) {
        int[] num = { 0, 1, 2, 4, 2, 3 };
//        new FindtheDuplicateNum().findDuplicate(num);
        System.out.println(new FindtheDuplicateNum().findDuplicateB(num));
    }

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++)
            nums[i]--;
        int slow = n - 1;
        int fast = n - 1;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = n - 1;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow + 1;
    }

    public static int findDuplicateB(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 0;
        int low = 1, high = nums.length - 1, mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
}
