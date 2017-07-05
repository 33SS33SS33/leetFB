package hard;

import java.util.Map;
import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * LinkedIn Apple Twitter
 * Tags:Hash Table Math
 * 解这个平面几何题有3个要点：
 * 1. 如何判断共线?
 * 两点成一直线，所以两点没有共线不共线之说。对于点p1(x1, y1)，p2(x2, y2)，p3(x3, y3)来说，共线的条件是p1-p2连线的斜率与p1-p3连线的斜率相同，即
 * (y2-y1)/(x2-x1) = (y3-y1)/(x3-x1)
 * 所以对共线的n点，其中任意两点连线的斜率相同。
 * 2. 如何判断最多的共线点?
 * 对于每个点p出发，计算该点到所有其他点qi的斜率，对每个斜率统计有多少个点符合。其中最多的个数加1（出发点本身）即为最多的共线点。
 * 3. 特殊情况
 * 当x1 = x2，y1!=y2时，为垂直连线。计算斜率时分母为0会出错。
 * 当x1 = x2，y1 = y2时，两点重合。则(x2, y2)和所有(x1, y1)的连线共线。
 */
class MaxPoints {
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

    /**
     * 每次迭代以某一个点为基准， 看后面每一个点跟它构成的直线， 维护一个HashMap， key是跟这个点构成直线的斜率的值，
     * 而value就是该斜率对应的点的数量， 计算它的斜率， 如果已经存在， 那么就多添加一个点， 否则创建新的key。
     * 时间复杂度是O（n^2), 空间复杂度是哈希表的大小， 也就是O（n),
     */
    public int maxPointsA1(Point[] points) {
        if (points == null || points.length == 0)
            return 0;
        int max = 1;
        double ratio = 0.0;
        for (int i = 0; i < points.length - 1; i++) {
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
            int numofSame = 0;
            int localMax = 1;
            for (int j = i + 1; j < points.length; j++) {
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    numofSame++;
                    continue;
                } else if (points[j].x == points[i].x) {
                    ratio = (double) Integer.MAX_VALUE;
                } else if (points[j].y == points[i].y) {
                    ratio = 0.0;
                } else {
                    ratio = (double) (points[j].y - points[i].y) / (double) (points[j].x
                            - points[i].x);
                }
                int num;
                if (map.containsKey(ratio)) {
                    num = map.get(ratio) + 1;

                } else {
                    num = 2;
                }
                map.put(ratio, num);
            }
            for (Integer value : map.values()) {
                localMax = Math.max(localMax, value);
            }
            localMax += numofSame;
            max = Math.max(max, localMax);
        }
        return max;
    }

    /**
     * creek
     * This problem can be solve by counting points that have the same slope for each point.
     * When counting, we need to be careful about the duplicate points and points on the vertical lines.
     */
    public int maxPointsB(Point[] points) {
        if (points == null || points.length == 0)
            return 0;
        HashMap<Double, Integer> result = new HashMap<Double, Integer>();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int duplicate = 1;//
            int vertical = 0;
            for (int j = i + 1; j < points.length; j++) {
                //handle duplicates and vertical
                if (points[i].x == points[j].x) {
                    if (points[i].y == points[j].y) {
                        duplicate++;
                    } else {
                        vertical++;
                    }
                } else {
                    double slope = points[j].y == points[i].y ?
                            0.0 :
                            (1.0 * (points[j].y - points[i].y)) / (points[j].x - points[i].x);

                    if (result.get(slope) != null) {
                        result.put(slope, result.get(slope) + 1);
                    } else {
                        result.put(slope, 1);
                    }
                }
            }
            for (Integer count : result.values()) {
                if (count + duplicate > max) {
                    max = count + duplicate;
                }
            }
            max = Math.max(vertical + duplicate, max);
            result.clear();
        }
        return max;
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
