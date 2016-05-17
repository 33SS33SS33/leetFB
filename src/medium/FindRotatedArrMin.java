package medium;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * Tags: Array, Binary Search
 */

/**
 * 使用二分查找即可  二分查找要变形一下 把中点和最后的元素比较
 * 如果中点元素比最后的元素大 则说明后面的是乱序的 最小值在里面
 * 如果中点元素比最后的小 则说明后面是正确的 那最小值就在前面
 * 这里注意 最小值在前面的时候 end要设置为midpoint 因为midpoint本身就可能是最小值
 */
class FindRotatedArrMin {
    public static void main(String[] args) {
        int[] num = { 3, 4, 5, 6, 1, 2 };
        System.out.println(findMin(num));
        System.out.println(findMinB(num));
        System.out.println(findMinC(num));
    }

    static int findMin(int[] num) {
        int l = 0;
        int r = num.length - 1;
        if (num.length == 1 || num[l] < num[r])
            return num[0];
        int mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (num[l] < num[mid])
                l = mid;
            else
                r = mid;
        }
        return num[l + 1];
    }

    /**
     * creek
     * Define a helper function, otherwise, we will need to use Arrays.copyOfRange() function, which may be expensive for large arrays.
     */
    public static int findMinB(int[] num) {
        return findMin(num, 0, num.length - 1);
    }

    public static int findMin(int[] num, int left, int right) {
        if (left == right)
            return num[left];
        if ((right - left) == 1)
            return Math.min(num[left], num[right]);
        int middle = left + (right - left) / 2;
        // not rotated
        if (num[left] < num[right]) {
            return num[left];
            // go right side
        } else if (num[middle] > num[left]) {
            return findMin(num, middle, right);
            // go left side
        } else {
            return findMin(num, left, middle);
        }
    }

    public static int findMinC(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return binarySearch(0, nums.length - 1, nums);
    }

    public static int binarySearch(int left, int right, int[] nums) {
        if (left >= right) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        if (mid >= 1 && nums[mid] < nums[mid - 1]) {
            return nums[(left + right) / 2];
        } else if (nums[mid] >= nums[left] && nums[mid] >= nums[right]) {
            return binarySearch(mid + 1, right, nums);
        } else
            return binarySearch(left, mid - 1, nums);
    }

}