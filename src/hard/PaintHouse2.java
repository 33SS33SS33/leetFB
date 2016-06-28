package hard;

/**
 * "There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 * Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 * Follow up:
 * Could you solve it in O(nk) runtime?"

 * "这道题关键的地方在于 其实根据上一座房子来计算当前的房子的cost的时候 只需要上一座房子的最小的cost和第二小的cost
 * 原因是 如果不考虑各个房子要不同颜色 每座房子都选最小的cost就行了 那么现在需要不同颜色了  如果两个临近的房子撞色了  那么就可以参考第二小的cost就行
 * 所以代码里使用了reduce来处理 然后前一座cost只存的有最小的那个 还有第二小的 注意一下index 最小的cost的那个位置其实存的是第二小的cost
 * 剩下的位置就都是最小的cost 这样在和当前的房子进行合并计算的时候就不会出错 直接计算最小值即可"
 */
public class PaintHouse2 {
    public static void main(String[] args) {
        int[][] costs = { {}, {}, {} };
        System.out.print(new PaintHouse2().minCostII(costs));
    }
/*
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length, k = costs[0].length;
// min1 is the index of the 1st-smallest cost till previous house
// min2 is the index of the 2nd-smallest cost till previous house int min1 = -1, min2 = -1;
        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1;
            min2 = -1;
            for (int j = 0; j < k; j++) {
                if (j != last1) {
// current color j is different to last min1
                costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1]; } else {
                costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2]; }
// find the indices of 1st and 2nd smallest cost of painting current
                if (min1 < 0 || costs[i][j] < costs[i][min1]) { min2 = min1; min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) { min2 = j;
                } }
        }
        return costs[n - 1][min1]; }*/

    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int m = costs.length, n = costs[0].length, m1 = 0, m2 = 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            int t1 = m1, t2 = m2;
            m1 = Integer.MAX_VALUE;
            m2 = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                dp[j] = (dp[j] == t1 ? t2 : t1) + costs[i][j];
                if (m1 <= dp[j]) {
                    m2 = Math.min(dp[j], m2);
                } else {
                    m2 = m1;
                    m1 = dp[j];
                }
            }
        }
        return m1;
    }
}
