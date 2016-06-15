package hard;
import java.util.LinkedList;
import java.util.Queue;

/**
 * "You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1."
 */


/**
 * "使用bfs即可 最关键的地方在于建立一个同样的矩阵记录距离
 * 然后原先的矩阵就当碰见一个building的时候 就bfs一下它到所有其他点得距离 这里比较方便的就是
 * 可以使用-1 -2 -3来给building编号 这样每次只用检查这个点是不是等于id+1 就可以知道这个点有没有走过
 * 最后的无解的判断也是通过id来进行的"
 */
public class ShortestDistancefromAllBuildings {
    public static void main(String[] args) {
        ShortestDistancefromAllBuildings r = new ShortestDistancefromAllBuildings();
        int[][] nums = { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0, }, { 0, 0, 1, 0, 0 } };
        System.out.println(r.shortestDistance(nums));
    }

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0)
            return 0;
        final int[] shift = new int[] { 0, 1, 0, -1, 0 };
        int row = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Queue<int[]> myQueue = new LinkedList<int[]>();
                    myQueue.offer(new int[] { i, j });
                    boolean[][] isVisited = new boolean[row][col];
                    int level = 1;
                    while (!myQueue.isEmpty()) {
                        int qSize = myQueue.size();
                        for (int q = 0; q < qSize; q++) {
                            int[] curr = myQueue.poll();
                            for (int k = 0; k < 4; k++) {
                                int nextRow = curr[0] + shift[k];
                                int nextCol = curr[1] + shift[k + 1];
                                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                        && grid[nextRow][nextCol] == 0
                                        && !isVisited[nextRow][nextCol]) {
                                    //The shortest distance from [nextRow][nextCol] to thic building
                                    // is 'level'.
                                    distance[nextRow][nextCol] += level;
                                    reach[nextRow][nextCol]++;
                                    isVisited[nextRow][nextCol] = true;
                                    myQueue.offer(new int[] { nextRow, nextCol });
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }
}
