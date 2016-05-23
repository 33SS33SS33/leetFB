package dP;

/**
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 * For example,
 * Given [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1], return 6.
 *
 * Tags: Array, Stack, Two pointers
 */
/**对任意位置i，在i上的积水，不包括i本身, 由左右两边最高的bar：A[left] = max{A[j], j<i}, A[right] = max{A[j], j>i}决定。定义Hmin = min(A[left], A[right])，则积水量Si为：
Hmin <= A[i]时，Si = 0
Hmin > A[i]时，Si = Hmin - A[i]
所以第一遍扫描  记录的是当前索引i最左边的最高的bar 然后第二遍从后往前  首先先得到当前i最右边最高的bar 然后这两个最高的最小值减去当前的高度就是水量*/
class TrappingRainWater {
    public static void main(String[] args) {
        int[] A = { 10, 2, 20 };
        int[] B = { 5, 4, 3, 2, 8 };
        TrappingRainWater m = new TrappingRainWater();
        System.out.println(m.TrappingRainWaterA(A));
        System.out.println(m.TrappingRainWaterB(A));
        System.out.println(m.TrappingRainWaterA(B));
        System.out.println(m.TrappingRainWaterB(B));
    }
    
    /**
     * Calculate the area of all and blocks ，Then return all - block to get the trapped water
     * Start from two sides
     * If min of A[l] and A[r] is larger than current level
     * Update all and curLevel
     * Then move lower pointer towards center
     */
    public int TrappingRainWaterA(int[] A) {
        if (A == null || A.length == 0) return 0;
        int l = 0;
        int r = A.length - 1;
        int level = 0;
        int all = 0;
        int block = 0;
        while (l <= r) { // note l <= r to include the last block
            // update area
            int curLevel = Math.min(A[l], A[r]);
            if (curLevel > level) {
                all += (curLevel - level) * (r - l + 1);
                level = curLevel;
            }
            // move index and update block
            if (A[l] < A[r]) block += A[l++];
            else block += A[r--];
        }
        return all - block;
    }
    /**creek--*/
    public int TrappingRainWaterB(int[] height) {
        int result = 0;
        if(height==null || height.length<=2)
            return result;
        int left[] = new int[height.length];
        int right[]= new int[height.length];
        //scan from left to right
        int max = height[0];
        left[0] = height[0];
        for(int i=1; i<height.length; i++){
            if(height[i]<max){
                left[i]=max;
            }else{
                left[i]=height[i];
                max = height[i];
            }
        }
        //scan from right to left
        max = height[height.length-1];
        right[height.length-1]=height[height.length-1];
        for(int i=height.length-2; i>=0; i--){
            if(height[i]<max){
                right[i]=max;
            }else{
                right[i]=height[i];
                max = height[i];
            }
        }
        //calculate totoal
        for(int i=0; i<height.length; i++){
            result+= Math.min(left[i],right[i])-height[i];
        }
        return result;
    }
}