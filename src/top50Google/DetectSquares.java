package top50Google;

import java.util.*;

public class DetectSquares {
    /**
     * You are given a stream of points on the X-Y plane. Design an algorithm that:
     * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
     * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
     * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
     * Implement the DetectSquares class:
     * DetectSquares() Initializes the object with an empty data structure.
     * void add(int[] point) Adds a new point point = [x, y] to the data structure.
     * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
     */
    int[][] cntPoints = new int[1001][1001];
    List<int[]> points = new ArrayList<>();

    public void add(int[] p) {
        cntPoints[p[0]][p[1]] += 1;
        points.add(p);
    }

    public int count(int[] p1) {
        int x1 = p1[0], y1 = p1[1], ans = 0;
        for (int[] p3 : points) {
            int x3 = p3[0], y3 = p3[1];
            if (Math.abs(x1 - x3) == 0 || Math.abs(x1 - x3) != Math.abs(y1 - y3))
                continue; // Skip empty square or invalid square point!
            ans += cntPoints[x1][y3] * cntPoints[x3][y1];
        }
        return ans;
    }
}
