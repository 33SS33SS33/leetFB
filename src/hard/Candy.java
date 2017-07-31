package hard;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following
 * requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * Tags: Greedy
 * 当评价的数列是递增的时候 糖果数就是1 2 3 4 5这种等差数列  然后碰见了递减数列的话
 * 糖果数就是 4 3 2 1这样 递减数列最小的那个值是1 递减数列如果从右往左看也是递增的
 * 所以可以从左往右扫描一遍 然后记录递增数列的长度 然后再从右向左扫描一遍  也记录递增数列的长度
 */
class Candy {
    public static void main(String[] args) {
        int[] ratings = {1, 3, 4, 6, 2};

        System.out.println(new Candy().candyA(ratings));
    }

    /**
     * O(n) Time, O(n) Space  最好的
     * From left to right, if ratings[i] increase, give one more
     * From right to left, if ratings[i] increase, give one more
     * Answer should be the max of two array
     * Simplify second traversal by calculate and decide max right away
     * And also calculate the sum
     */
    public int candyA(int[] ratings) {
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++)
            candies[i] = ratings[i] > ratings[i - 1] ? candies[i - 1] + 1 : 1;
        int res = candies[candies.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            res += candies[i];
        }
        return res;
    }

}
