package hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shanshan on 16/5/9.
 */
public class BestMeetingPoint {
    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        new BestMeetingPoint().bestMeetingPoint(grid);

        System.out.println(new BestMeetingPoint().bestMeetingPoint(grid));
    }

    /**
     * "A group of two or more people wants to meet and minimize the total travel distance.
     * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
     * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
     * For example, given three people living at (0,0), (0,4), and (2,2):
     * 1 - 0 - 0 - 0 - 1
     * |   |   |   |   |
     * 0 - 0 - 0 - 0 - 0
     * |   |   |   |   |
     * 0 - 0 - 1 - 0 - 0
     * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
     * Hint: Try to solve it in one dimension first. How can this solution apply to the two dimension case?"
     * "这道题首先要想出来一维的解法 其实最关键的地方还是在于 在一维的时候
     * 最合适的点就是所有这些人的坐标的中位数  因为在中位数的时候 才能让距离最小
     * As long as you have different numbers of people on your left and on your right,
     * moving a little to the side with more people decreases the sum of distances. So to minimize it,
     * you must have equally many people on your left and on your right. Same with above/below.
     * 比如如果你找个点 这个点左边有1个人 右边有2个人 那么你只要向右边移动一个 那么总得距离就会缩小
     * 因为左边加1 右边减2 所以平衡点就是中间的店 中位数
     * 如果是偶数个人 在两个中间点中得任意一个点都行 所以算法很简单 只需要先计算x的中位数 再y的中位数即可"
     */
    public int bestMeetingPoint(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> I = new ArrayList<>(m);
        List<Integer> J = new ArrayList<>(n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                    J.add(j);
                }
            }
        }
        return getMin(I) + getMin(J);
    }

    private int getMin(List<Integer> list) {
        int ret = 0;
        Collections.sort(list);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            ret += list.get(j--) - list.get(i++);
        }
        return ret;
    }
}
