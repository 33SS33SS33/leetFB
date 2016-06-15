package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.
 * Follow up:
 * Could you do it using only constant space complexity?
 * <p/>
 * 二叉搜索树先序遍历序列的特点是降序的部分一定是向左走的，一旦开始升序说明开始向右走了，
 * 则上一个降序的点则限定了后面的数的最小值。如果继续降序，说明又向左走了，这样等到下次向右走得时候也要再次更新最小值。
 * 10
 * /  \
 * 5    12
 * / \
 * 2   6
 * 如这个例子，我们在10的位置是没有最小值限定的，然后降序走到5，依然没有最小值，降序走到2，依然没有，然后开始升序了，
 * 遇到6，这时候之后的数字一定大于2，同时也大于5，所以最小值更新为之前遍历过的，且比当前数稍微小一点的那个数。这里我们可以用一个栈来暂存之前的路径
 * ，所以升序时就是将栈中元素不断pop出来直到栈顶大于当前数，而最小值就是最后一个pop出来的数，最后再把该数push进去。对于降序的时候，直接向里面push就行了。
 * 这样，序列无效的条件就是违反了这个最小值的限定。
 * BST的核心就是最小值  最大值  重要
 */
public class VerifyPreorderSequenceinBST {

    public boolean verifyPreordera(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> path = new Stack();
        for (int p : preorder) {
            if (p < low)
                return false;
            while (!path.empty() && p > path.peek())
                low = path.pop();
            path.push(p);
        }
        return true;
    }

    public boolean verifyPreordera2(int[] preorder) {
        int low = Integer.MIN_VALUE, i = -1;
        for (int p : preorder) {
            if (p < low)
                return false;
            while (i >= 0 && p > preorder[i])
                low = preorder[i--];
            preorder[++i] = p;
        }
        return true;
    }

    public boolean verifyPreorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        LinkedList<Integer> stack = new LinkedList<Integer>(); // fuck
        stack.push(0);
        stack.push(inorder.length);
        for (int p = 0; p < preorder.length; /*void*/) {
            int ed = stack.pop();
            int st = stack.pop();
            if (st >= ed)
                continue;
            int root = preorder[p++];
            int i = Arrays.binarySearch(inorder, st, ed, root);
            if (i < 0)
                return false;
            stack.push(i + 1);
            stack.push(ed);
            stack.push(st);
            stack.push(i);
        }
        return true;
    }
}
