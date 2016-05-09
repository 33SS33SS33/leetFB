package hard;

import java.util.Map;
import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 *
 * Tags: Hashtable, math
 */
/**解这个平面几何题有3个要点：
 1. 如何判断共线?
 两点成一直线，所以两点没有共线不共线之说。对于点p1(x1, y1)，p2(x2, y2)，p3(x3, y3)来说，共线的条件是p1-p2连线的斜率与p1-p3连线的斜率相同，即
 (y2-y1)/(x2-x1) = (y3-y1)/(x3-x1)
 所以对共线的n点，其中任意两点连线的斜率相同。
 2. 如何判断最多的共线点?
 对于每个点p出发，计算该点到所有其他点qi的斜率，对每个斜率统计有多少个点符合。其中最多的个数加1（出发点本身）即为最多的共线点。
 3. 特殊情况
 当x1 = x2，y1!=y2时，为垂直连线。计算斜率时分母为0会出错。
 当x1 = x2，y1 = y2时，两点重合。则(x2, y2)和所有(x1, y1)的连线共线。*/
class MaxPoints {
    public static void main(String[] args) {

    }

    /*， 每次迭代以某一个点为基准， 看后面每一个点跟它构成的直线， 维护一个HashMap， key是跟这个点构成直线的斜率的值，
     而value就是该斜率对应的点的数量， 计算它的斜率， 如果已经存在， 那么就多添加一个点， 否则创建新的key。
      时间复杂度是O（n^2), 空间复杂度是哈希表的大小， 也就是O（n),*/
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
     * Set a point and go through the rest
     * Use max to record the max points of current loop
     * Use countSame to track the number of points
     * Use result to track the max points
     * Use a map to store lines, key is the feature of the line, value is the
     * number of points
     * @param points points generated, can be same
     * @return number of max points share a same line
     */
    private static int maxPointsA(Point[] points) {
        if (points.length < 3) return points.length; // 0/1/2 points
        int res = 1; // at least 1 point
        Map<String, Integer> map = new HashMap<String, Integer>(); // line,count
        for (int i = 0; i < points.length; i++) {
            int max = 0;
            int countSame = 0; // # of same points
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y ) countSame++; // same point
                else {
                    String key = normalize(points[i], points[j]); // a|b|c
                    if (map.containsKey(key)) { // on the line
                        int count = map.get(key) + 1;
                        map.put(key, count); // update count
                        if (count > max) max = count; // update max
                    } else { // not on any line
                        map.put(key, 1);
                        if (max == 0) max++; // update max
                    }
                }
            }
            res = Math.max(res, max + countSame + 1); // +1 for the start point
            map.clear(); // clear map for next point
        }
        return res;
    }
    /**
     * use ax + by = c to represent a line and a|b|c as a key for that line
     * a, b, c should be normalized, how?
     * special case, vertical, horizontal
     */
    private static String normalize(Point p1, Point p2) {
        int a, b;
        float c;
        if (p1.x == p2.x) { // vertical
            b = 0;
            a = 1;
            c = p1.x;
        } else if (p1.y == p2.y) { // horizontal
            a = 0;
            b = 1;
            c = p2.y;
        } else { // ax + by = c
            int dx = p1.x - p2.x;
            int dy = p1.y - p2.y;
            /*reduce to simplest*/
            int gcd = gcd(Math.abs(dx), Math.abs(dy));
            a = dy / gcd;
            b = dx / gcd;
            if (a * b < 0) { // force a to be negative
                a = -1 * Math.abs(a);
                b = Math.abs(b);
            } else { // force both positive
                a = Math.abs(a);
                b =  Math.abs(b);
            }
            c = a * p1.x + b * p1.y; // c = ax + by
        }
        return a + "|" + b + "|" + c; // key format
    }
    /**recursively calculate the greateset common divisor of two numbers
     */
    private static int gcd(int a, int b) {
        if (b == 0) return a; // stop when b == 0, a is the gcd
        return gcd(b, a % b); // a <- b, b <- a % b
    }

    /**creek
     * This problem can be solve by counting points that have the same slope for each point.
     * When counting, we need to be careful about the duplicate points and points on the vertical lines.*/
    public int maxPointsB(Point[] points) {
        if(points == null || points.length == 0) return 0;
        HashMap<Double, Integer> result = new HashMap<Double, Integer>();
        int max=0;
        for(int i=0; i<points.length; i++){
            int duplicate = 1;//
            int vertical = 0;
            for(int j=i+1; j<points.length; j++){
                //handle duplicates and vertical
                if(points[i].x == points[j].x){
                    if(points[i].y == points[j].y){
                        duplicate++;
                    }else{
                        vertical++;
                    }
                }else{
                    double slope = points[j].y == points[i].y ? 0.0
                            : (1.0 * (points[j].y - points[i].y))
                            / (points[j].x - points[i].x);

                    if(result.get(slope) != null){
                        result.put(slope, result.get(slope) + 1);
                    }else{
                        result.put(slope, 1);
                    }
                }
            }
            for(Integer count: result.values()){
                if(count+duplicate > max){
                    max = count+duplicate;
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
        Point() { x = 0; y = 0;}
        Point(int a, int b) { x = a; y = b; }
    }



}
