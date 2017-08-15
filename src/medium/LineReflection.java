package medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by shanshan on 16/6/18.
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.
 * Example 1:
 * Given points = [[1,1],[-1,1]], return true.
 * Example 2:
 * Given points = [[1,1],[-1,-1]], return false.
 * Follow up:
 * Could you do better than O(n2)?
 */
public class LineReflection {

    public boolean isReflected(int[][] points) {
        HashSet<Integer> pointSet = new HashSet<>();
        int sum;
        int maxX, minX;
        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        for (int[] point : points) {
            maxX = Math.max(maxX, point[0]);
            minX = Math.min(minX, point[0]);
            pointSet.add(Arrays.hashCode(point));
        }
        sum = maxX + minX;
        for (int[] point : points) {
            if (!pointSet.contains(Arrays.hashCode(new int[]{sum - point[0], point[1]}))) {
                return false;
            }
        }
        return true;
    }

}
