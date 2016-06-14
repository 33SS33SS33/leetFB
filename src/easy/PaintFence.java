package easy;

/**
 * Created by shanshan on 16/5/9.
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 * Note:
 * n and k are non-negative integers.
 */

/**
 * DP的题 注意这里是两个临近的篱笆有可能是同色的 但是同色的不超过两个
 */
public class PaintFence {

    public int numWays(int n, int k) {
        if (n == 0) return 0;
        else if (n == 1) return k;
        int diffColorCounts = k * (k - 1);
        int sameColorCounts = k;
        for (int i = 2; i < n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }
}
