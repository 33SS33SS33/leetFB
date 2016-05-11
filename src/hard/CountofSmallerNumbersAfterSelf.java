package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 */
/**"You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 Example:
 Given nums = [5, 2, 6, 1]
 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 Return the array [2, 1, 1, 0]."
 */
 /**"这道题有好多解法 树状数组 线段树 归并排序 待学习 未实现
 线段树 还有树状数组一定要学习 重要
 相似题目 Range Sum Query - Mutable
 这里用了BST 主要是加入了自身的节点数 以及左孩子的个数的属性 但是很慢
 这种叫做逆序数 一般都可以用merge sort来解决  待学习"
 "http://www.hawstein.com/posts/binary-indexed-trees.html
 http://bookshadow.com/weblog/2015/12/06/leetcode-count-of-smaller-numbers-after-self/
 https://leetcode.com/discuss/73256/mergesort-solution
 http://www.cnblogs.com/TenosDoIt/p/3453089.html
 https://leetcode.com/discuss/73509/nlogn-time-space-mergesort-solution-with-detail-explanation
 http://www.geeksforgeeks.org/counting-inversions/
 http://www.hrwhisper.me/leetcode-range-sum-query-mutable/
 http://www.cnblogs.com/zichi/p/4806998.html"
 */
public class CountofSmallerNumbersAfterSelf {
}
