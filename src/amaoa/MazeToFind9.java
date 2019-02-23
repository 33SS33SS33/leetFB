package amaoa;

import java.util.LinkedList;

/**
 * Created by shanshan on 2/21/19.
 */
public class MazeToFind9 {
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
