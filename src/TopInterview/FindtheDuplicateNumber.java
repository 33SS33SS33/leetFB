package TopInterview;

import java.util.HashSet;
import java.util.Set;

public class FindtheDuplicateNumber {
    public static void main(String[] args) {
        int[] num = {1, 2, 4, 2, 3};
        System.out.println(new FindtheDuplicateNumber().findtheDuplicateNumber(num));
        System.out.println(new FindtheDuplicateNumber().findtheDuplicateNumberb(num));
    }

    /**
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     * Input: [1,3,4,2,2]Output: 2  Input: [3,1,3,4,2]Output: 3
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array,but it could be repeated more than once.
     */
    public int findtheDuplicateNumber(int[] nums) {
        if (nums.length > 1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }

    public static int findtheDuplicateNumberb(int[] nums) {
        if (nums == null || nums.length == 0)
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

    public int findDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num))
                return num;
            seen.add(num);
        }
        return -1;
    }
}
