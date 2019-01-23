package aMaz;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two
 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most
 * water.
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Note: You may not slant the container.
 * Tags: Array, Two pointers
 */
class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = { 2, 4, 3, 2, 7 };
        int[] height2 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(new ContainerWithMostWater().maxAreaA(height));
        System.out.println(new ContainerWithMostWater().maxAreaA(height2));
    }

    /**
     * creek 2 pointers, low and high
     * curArea = (high - low) * min(height[high], height[low])
     * maxArea = max(maxArea, curArea)
     * Move lower pointer towards center for the next loop
     * Stop when two pointers meet, cause one line can form a container
     * Different from block
     */
    public int maxAreaA(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return max;
    }

}
