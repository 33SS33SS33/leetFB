package hard;

/**
 * Find duplicates in O(n) time and O(1) extra space
 * Given an array of n elements which contains elements from 0 to n-1, with any
 * of these numbers appearing any number of times. Find these repeating numbers
 * in O(n) and using only constant memory space.
 * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer
 * should be 1, 3 and 6.
 * Tags: Array
 */

/**
 * 两种解法 重要
 * 第一种十分巧妙  其实这道题是链表找环的变形 数组的值就是表示的下一个链表  next[0]就是这个链表的入口 未实现
 * 第二种就是使用binary search 但是注意这里search都是用的索引  而不是值
 * 使用值来查找是无法区分左右关系的  所以start和end的起始值才是1和len(nums)-1 表示的是 这个数组的范围是1到n-1 返回值也是一个索引
 * 数组的值只是用来分类左右的
 */
class FindDup {
    public static void main(String[] args) {
        int[] num = { 1, 2, 3, 1, 4, 3 };
        new FindDup().printRepeating(num);
    }

    /**
     * Check the sign of A[abs(A[i])]
     * if   positive then make it negative by A[abs(A[i])] = -A[abs(A[i])]
     * else this element (ith element of list) is a duplication
     */
    public void printRepeating(int[] num) {
        if (num == null || num.length == 0)
            return;
        for (int i = 0; i < num.length; i++) {
            if (num[Math.abs(num[i])] < 0) { // duplicate
                System.out.println(Math.abs(num[i])); // print dups
            } else { // set flag
                num[Math.abs(num[i])] = -num[Math.abs(num[i])]; // mark
            }
        }
    }

}
