package tree;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store
 * values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * Tags: Tree, DP
 * 和UniqueBST2 思路一样
 * 假设count(i)表示如果当前根是(i)那么一共有多少种BST组合
 * 那么count(i) = count(i-1) + count(n-i) 就是他的左子树的所有组合  加上他的右子树的所有组合
 * 而要求出所有的BST组合  那就是等于 count(1) + count(2) + …. count(n)
 * 然后就可以迭代求解
 * 设立一个rec数组来存每一个count(i) 然后大循环遍历1-n 小循环则求出当前的count(i)
 * 不要用迭代  会超时
 */
class UniqueBST {
    public static void main(String[] args) {
        System.out.println(numTreesA(5));
        System.out.println(numTrees(5));
    }

    //http://blog.csdn.net/linhuanmars/article/details/24761459
    public static int numTrees(int n) {
        if (n <= 0)
            return 0;
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                res[i] += res[j] * res[i - j - 1];
            }
        }
        return res[n];
    }

    /**
     * DP, Bottom-up approach.
     * a BST can be destruct to root, left subtree and right subtree.
     * if the root is fixed, every combination of unique left/right subtrees
     * forms a unique BST.
     * Let a[n] = number of unique BST's given values 1..n, then
     * a[n] = a[0] * a[n-1]     // put 1 at root, 2...n right
     * + a[1] * a[n-2]     // put 2 at root, 1 left, 3...n right
     * + ...
     * + a[n-1] * a[0]     // put n at root, 1...n-1 left
     * 时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，
     * 所以总时间复杂度是O(1+2+...+n)=O(n^2)。
     * 空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n)
     */
    public static int numTreesA(int n) {
        if (n < 0)
            return 0;
        int[] trees = new int[n + 1];
        trees[0] = 1; // initialize 0, only 1 type of tree
        for (int i = 1; i <= n; i++) // from 1 ~ n
            for (int j = 0; j < i; j++) // from 0 ~ i - 1
                trees[i] += trees[j] * trees[i - j - 1]; // note i-j-1 + j = i - 1
        return trees[n];
    }

}
