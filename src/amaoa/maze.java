package amaoa;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class maze {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] end = {4, 4};
        System.out.println(shortPath(maze, start, end));
    }

    public static int shortPath(int[][] maze, int[] start, int[] dest) {
        int m = maze.length, n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] dir : dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                int sum = 0;
                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    sum++;
                }
                if (distance[curr[0]][curr[1]] + sum < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[curr[0]][curr[1]] + sum;
                    q.offer(new int[]{x - dir[0], y - dir[1]});
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    //*************BFS *******************************************
 /*   public static int shortestBFS(int[][] maze, int[] start, int[] dest) {
        int m = maze.length, n = maze[0].length;
        int[][] path = new int[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int x = curr[0], y = curr[1], count = 0;
                while (x + dirs[d] >= 0 && x + dirs[d] < m && y + dirs[d + 1] >= 0 && y + dirs[d + 1] < n &&
                        maze[x + dirs[d]][y + dirs[d + 1]] == 0) {
                    x += dirs[d];
                    y += dirs[d + 1];
                    count++;
                }
                if ((x != start[0] || y != start[1]) &&
                        (path[x][y] == 0 || path[x][y] > path[curr[0]][curr[1]] + count)) {
                    path[x][y] = path[curr[0]][curr[1]] + count;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return path[dest[0]][dest[1]] == 0 ? -1 : path[dest[0]][dest[1]];
    }*/

}
