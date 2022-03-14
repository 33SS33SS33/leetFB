package aaMSsssssss;

public class MinimumNumberofTapstoOpentoWateraGarden {
    public int minTaps(int n, int[] ranges) {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++){
            int start = Math.max(0, i - ranges[i]);
            int end = i + ranges[i];
            dp[start] = Math.max(dp[start], end);
        }
        int count = 0;
        int next = 0;
        int farthest = 0;
        for(int i = 0; i <= n; i++){
            next = Math.max(next, dp[i]);
            if(i == farthest){
                count++;
                farthest = next;
                if(farthest >= n) return count;
            }
        }
        return farthest >= n? count: -1;
    }
}
