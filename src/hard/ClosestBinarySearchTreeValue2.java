package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 */
/**
 "Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 Note:
 Given target value is a floating point.
 You may assume k is always valid, that is: k ≤ total nodes.
 You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 Follow up:
 Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?"

 "就是在中序的时候保留一个窗口 窗口是个最大堆 窗口不满的时候就往里加数字
 窗口满了开始比较  如果当前这个数字的距离比堆里那个最大的距离小 就替换
 如果比最大的距离还大就可以跳出了
 follow up 未实现"
 */
public class ClosestBinarySearchTreeValue2 {
}
