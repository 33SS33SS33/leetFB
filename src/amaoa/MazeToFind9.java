package amaoa;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shanshan on 2/21/19.
 */
public class MazeToFind9 {
    private final static int[] dx = {-1, 0, 0, 1};
    private final static int[] dy = {0, 1, -1, 0};

    public int mazeFind92(int[][] lot1) {
        if (lot1 == null || lot1.length == 0 || lot1[0].length == 0)
            return 0;
        if (lot1[0][0] == 9)
            return 1;
        int m = lot1.length, n = lot1[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{0, 0});
        lot1[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int[] next = {cur[0] + dx[i], cur[1] + dy[i]};
                if (next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n) {
                    if (lot1[next[0]][next[1]] == 9)
                        return 1;
                    else if (lot1[next[0]][next[1]] == 0) {
                        queue.offer(next);
                        lot1[next[0]][next[1]] = 1;
                    }
                }
            }
        }
        return 0;
    }

    public boolean mazeFind9(int[][] maze, int x, int y) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return false;
        }
        int n = maze.length;
        int m = maze[0].length;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y, maze[x][y]));
        int[] gx = {0, 0, 1, -1};
        int[] gy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Point p = queue.poll();
                if (p.val == 9) return true;
                if (p.val == 0) continue;
                for (int i = 0; i < 4; i++) {
                    int nx = gx[i] + p.x;
                    int ny = gy[i] + p.y;
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m ||
                            maze[nx][ny] <= 0) {
                        continue;
                    }
                    if (maze[nx][ny] == 9) return true;
                    queue.offer(new Point(nx, ny, maze[nx][ny]));
                    maze[nx][ny] = -1;
                }
            }
        }
        return false;
    }
    static class Point {
        int x;
        int y;
        int val;

        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }


}
