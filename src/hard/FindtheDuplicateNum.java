package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/18.
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
//错的
public class FindtheDuplicateNum {
    public static void main(String[] args) {
        int[] num = { 0, 1, 2, 4, 2, 3 };
        System.out.println(new FindtheDuplicateNum().findDuplicate(num));
        System.out.println(new FindtheDuplicateNum().findDuplicateB(num));
    }

    public int findDuplicatea(int[] nums) {
        int n = nums.length;
        int slow = n;
        int fast = n;
        do {
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast);
        slow = n;
        while (slow != fast) {
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }
        return slow;
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
