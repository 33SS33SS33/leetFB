package medium;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * 
 * Tags: Array, Binary Search
 */
class FindRotatedArrMin {
    public static void main(String[] args) {
        int[] num = { 3, 4, 5, 6, 0, 1, 2 };
        System.out.println(findMin(num));
        System.out.println(findMinB(num));
    }
    
    static int findMin(int[] num) {
        int l = 0;
        int r = num.length - 1;
        if (num.length == 1 || num[l] < num[r]) return num[0];
        
        int mid = 0;
        while (l < r) {
            mid = l + (r - l) / 2;
            if (num[l] < num[mid]) l = mid;
            else r = mid;
        }
        return num[l + 1];
    }
    /**
     * creek
     * Define a helper function, otherwise, we will need to use Arrays.copyOfRange() function, which may be expensive for large arrays.*/
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
    
}