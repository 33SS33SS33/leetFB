package amaoa;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shanshan on 2/21/19.
 */
public class MazeToFind9 {
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

    private static boolean bfs(int[][] maze, int startx, int starty) {
        if (maze == null)
            return false;
        if (maze.length < 1 || maze[0].length < 1)
            return false;
        LinkedList<Point> que = new LinkedList<>();
        Point p1 = new Point(startx, starty, maze[startx][starty]);
        que.offer(p1);
        int width = maze[0].length;
        int height = maze.length;
        p1 = que.poll();
        while (p1.val != 9) {
            int x = p1.x;
            int y = p1.y;
            maze[x][y] = -1;
            if ((x + 1) < height) {
                if (maze[x + 1][y] > 0) {
                    Point p2 = new Point(x + 1, y, maze[x + 1][y]);
                    que.offer(p2);
                }
            }
            if ((x - 1) >= 0) {
                if (maze[x - 1][y] > 0) {
                    Point p2 = new Point(x - 1, y, maze[x - 1][y]);
                    que.offer(p2);
                }
            }
            if ((y + 1) < width)
                if (maze[x][(y + 1)] > 0) {
                    Point p2 = new Point(x, (y + 1), maze[x][(y + 1)]);
                    que.offer(p2);
                }
            if ((y - 1) >= 0)
                if (maze[x][(y - 1)] > 0) {
                    Point p2 = new Point(x, (y - 1), maze[x][(y - 1)]);
                    que.offer(p2);
                }
            if (que.isEmpty()) {
                break;
            } else
                p1 = que.poll();
        }
        if (p1.val == 9)
            return true;
        else
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
