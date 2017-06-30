package medium;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 * <p/>
 * 用dp即可 每个房子只可能有三个颜色 就是三个状态 0, 1, 2
 * 如果是0 那么前一个房子只可能是1,2
 * 如果是1 那么前一个房子只可能是0,2
 * 如果是2 那么前一个房子只可能是0,1
 * 所以每个dp保存当前房子3种颜色的分别最小的cost即可
 * dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + costs[i][0]
 * dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + costs[i][1]
 * dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + costs[i][2]
 */
public class PaintHouse {
    public static void main(String[] args) {
        int[][] costs = {{}, {}, {}};
        System.out.print(new PaintHouse().minCosta(costs));
        System.out.print(new PaintHouse().minCosta2(costs));
        //        System.out.print(new PaintHouse().minCost(costs));
    }

    public int minCosta(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][1], costs[i - 1][0]);
        }
        int n = costs.length - 1;
        return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }

    public int minCosta2(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;
        int[] prevRow = costs[0];
        for (int i = 1; i < costs.length; i++) {
            int[] currRow = new int[3];
            for (int j = 0; j < 3; j++)
                currRow[j] = costs[i][j] + Math.min(prevRow[(j + 1) % 3], prevRow[(j + 2) % 3]);
            prevRow = currRow;
        }
        return Math.min(prevRow[0], Math.min(prevRow[1], prevRow[2]));
    }

    static final int RED = 001;
    static final int BLUE = 100;
    static final int GREEN = 011;
    static final int NONE = 000;
    static final int ALL = 111;
    static final int[] COLORS = {RED, BLUE, GREEN};

    public int minCost(int[][] costs) {
        if (costs.length == 0)
            return 0;
        int[][] minCosts = new int[costs.length][COLORS.length];
        for (int c : COLORS) {
            minCosts[0][index(c)] = costs[0][index(c)];
        }
        for (int i = 1; i < costs.length; i++) {
            for (int c : COLORS) {
                minCosts[i][index(c)] = costs[i][index(c)] + min(minCosts[i - 1], c);
            }
        }
        return min(minCosts[costs.length - 1], NONE);
    }

    int index(int color) {
        return color / 2;
    }

    int min(int[] costs, int exclude) {
        int includes = ~(ALL & exclude);
        int min = Integer.MAX_VALUE;
        for (int c : COLORS) {
            if ((c & includes) == c) {
                min = Math.min(costs[index(c)], min);
            }
        }
        return min;
    }

}
