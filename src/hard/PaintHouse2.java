package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/9.
 */

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
 */

 /** "这道题关键的地方在于 其实根据上一座房子来计算当前的房子的cost的时候 只需要上一座房子的最小的cost和第二小的cost
 * 原因是 如果不考虑各个房子要不同颜色 每座房子都选最小的cost就行了 那么现在需要不同颜色了  如果两个临近的房子撞色了  那么就可以参考第二小的cost就行
 * 所以代码里使用了reduce来处理 然后前一座cost只存的有最小的那个 还有第二小的 注意一下index 最小的cost的那个位置其实存的是第二小的cost
 * 剩下的位置就都是最小的cost 这样在和当前的房子进行合并计算的时候就不会出错 直接计算最小值即可"
 */
public class PaintHouse2 {
    public static void main(String[] args) {
        int[][] costs = { {}, {}, {} };
        System.out.print(new PaintHouse2().minCost(costs));
    }

    public int minCost(int[][] costs) {
        return 0;
    }
}
