package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/5/6.
 */

import java.util.List;

/**
 "You are given a m x n 2D grid initialized with these three possible values.
 -1 - A wall or an obstacle.
 0 - A gate.
 INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 For example, given the 2D grid:
 INF  -1  0  INF
 INF INF INF  -1
 INF  -1 INF  -1
 0  -1 INF INF
 After running your function, the 2D grid should be:
 3  -1   0   1
 2   2   1  -1
 1  -1   2  -1
 0  -1   3   4"
 */

/**"首先找到gate的位置 然后从gate的位置开始进行BFS
 这道题最关键的地方在于 对于下一个点进行判断是否是INF的值 这样就不用记录访问了哪些点 因为如果已经算过的店 肯定小于 INF的值 而且这样也可以防止覆盖掉之前已经计算过的点"
 */
public class WallsAndGates {
    public static void main(String[] args) {
        int[][] rooms = { { 0, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        wallsAndGates(rooms);
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                System.out.print(rooms[i][j]);
            }
        }
    }

    public static void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0)
            return;
        int m = rooms.length;
        int n = rooms[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    rooms[i][j] = search(rooms, visited, i, j, m, n);
                }
            }
        }
        return;
    }

    private static int search(int[][] rooms, boolean[][] visited, int i, int j, int m, int n) {
        if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || rooms[i][j] == -1)
            return Integer.MAX_VALUE;
        if (rooms[i][j] == 0)
            return 0;
        if (visited[i][j])
            return rooms[i][j];
        visited[i][j] = true;
        if (rooms[i][j] > 0 && rooms[i][j] < Integer.MAX_VALUE)
            return rooms[i][j];
        int up = search(rooms, visited, i - 1, j, m, n);
        int down = search(rooms, visited, i + 1, j, m, n);
        int left = search(rooms, visited, i, j - 1, m, n);
        int right = search(rooms, visited, i, j + 1, m, n);
        visited[i][j] = false;
        int min = Math.min(Math.min(up, down), Math.min(left, right));
        return min == Integer.MAX_VALUE ? min : min + 1;
    }
}
