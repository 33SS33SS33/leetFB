package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * LinkedIn Apple Twitter
 * Tags:Hash Table Math
 * 解这个平面几何题有3个要点：
 * 1. 如何判断共线?
 * 两点成一直线，所以两点没有共线不共线之说。对于点p1(x1, y1)，p2(x2, y2)，p3(x3, y3)来说，
 * 共线的条件是p1-p2连线的斜率与p1-p3连线的斜率相同，即
 * (y2-y1)/(x2-x1) = (y3-y1)/(x3-x1)
 * 所以对共线的n点，其中任意两点连线的斜率相同。
 * 2. 如何判断最多的共线点?
 * 对于每个点p出发，计算该点到所有其他点qi的斜率，对每个斜率统计有多少个点符合。其中最多的个数加1（出发点本身）即为最多的共线点。
 * 3. 特殊情况
 * 当x1 = x2，y1!=y2时，为垂直连线。计算斜率时分母为0会出错。
 * 当x1 = x2，y1 = y2时，两点重合。则(x2, y2)和所有(x1, y1)的连线共线。
 */
class MaxPointsonALine {
    public static void main(String[] args) {

    }

    public int maxPoints(Point[] points) {
        if (points == null)
            return 0;
        if (points.length <= 2)
            return points.length;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int overlap = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int gcd = generateGCD(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                if (map.containsKey(x)) {
                    if (map.get(x).containsKey(y)) {
                        map.get(x).put(y, map.get(x).get(y) + 1);
                    } else {
                        map.get(x).put(y, 1);
                    }
                } else {
                    Map<Integer, Integer> m = new HashMap<Integer, Integer>();
                    m.put(y, 1);
                    map.put(x, m);
                }
                max = Math.max(max, map.get(x).get(y));
            }
            result = Math.max(result, max + overlap + 1);
        }
        return result;
    }

    private int generateGCD(int a, int b) {
        if (b == 0)
            return a;
        else
            return generateGCD(b, a % b);
    }

    //http://blog.csdn.net/linhuanmars/article/details/21060933
    public static int maxPointsb(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        if (allSamePoints(points))
            return points.length;
        int max = 0;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[j].y == points[i].y && points[j].x == points[i].x)
                    continue;
                int cur = 2;
                for (int k = 0; k < points.length; k++) {
                    if (k != i && k != j
                            && (points[k].y - points[i].y) * (points[j].x - points[i].x)
                            == (points[j].y - points[i].y) * (points[k].x - points[i].x))
                        cur++;
                }
                max = Math.max(max, cur);
            }
        }
        return max;
    }

    private static boolean allSamePoints(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[0].y != points[i].y || points[0].x != points[i].x)
                return false;
        }
        return true;
    }

    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

}
