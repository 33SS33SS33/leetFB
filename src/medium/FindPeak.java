package medium;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 * 
 * Note:
 * Your solution should be in logarithmic complexity.
 * Tags: Array, Binary Search
 */
class FindPeak {
    public static void main(String[] args) {
        int[] num = { 1, 2, 1, 3, 1, 4, 1 };
        System.out.println(new FindPeak().findPeakElement(num));
        System.out.println(new FindPeak().findPeakElementB(num));
        System.out.println(new FindPeak().findPeakElementC(num));
    }

    /**
     * Binary search for a peak. Other peaks can be ignored.
     */
    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int n = num.length;
        if (n <= 1)
            return 0;
        // handle the first and last element in num[]
        if (num[0] > num[1])
            return 0;
        if (num[n - 1] > num[n - 2])
            return n - 1;
        int left = 1, right = n - 2;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1])
                return mid;
            else if (num[mid] > num[mid + 1])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    /**
     * creek--
     */
    public int findPeakElementB(int[] num) {
        int max = num[0];
        int index = 0;
        for (int i = 1; i <= num.length - 2; i++) {
            int prev = num[i - 1];
            int curr = num[i];
            int next = num[i + 1];

            if (curr > prev && curr > next && curr > max) {
                index = i;
                max = curr;
            }
        }
        if (num[num.length - 1] > max) {
            return num.length - 1;
        }
        return index;
    }

    /**
     * ??????????
     */
    public int findPeakElementC(int[] num) {
        return findPeakElement(num, 0, num.length);
    }

    int findPeakElement(int[] num, int from, int to) {
        if (to - from == 1)
            return from;
        int m = (to + from) / 2;
        int l = findPeakElement(num, from, m);
        int r = findPeakElement(num, m, to);
        if (num[l] > num[r])
            return l;
        else
            return r;
    }

}