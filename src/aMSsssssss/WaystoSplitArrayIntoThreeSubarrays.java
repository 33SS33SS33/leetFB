package aMSsssssss;

public class WaystoSplitArrayIntoThreeSubarrays {
    public int waysToSplit(int[] nums) {
        int n=nums.length;
        int j=-1,k=-1,si=0,sj=0,sk=0,ans=0,mod=(int)1e9+7;
        int sum=0;
        for (int num:nums) sum+=num;
        for (int i=0;i<n-2;i++){
            si+=nums[i];
            while (j<=i || (j<n-1 && sj-si<si))
                sj+=nums[++j];
            while (k<j || (k<n-1 && sum-sk>=sk-si))
                sk+=nums[++k];
            ans=(ans+k-j)%mod;
        }
        return ans;
    }
}
