package hard;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following
 * requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * Tags: Greedy
 * <p/>
 * 当评价的数列是递增的时候 糖果数就是1 2 3 4 5这种等差数列  然后碰见了递减数列的话
 * 糖果数就是 4 3 2 1这样 递减数列最小的那个值是1 递减数列如果从右往左看也是递增的
 * 所以可以从左往右扫描一遍 然后记录递增数列的长度 然后再从右向左扫描一遍  也记录递增数列的长度
 */
class Candy {
    public static void main(String[] args) {
        int[] ratings = { 1, 3, 4, 6, 2 };

        System.out.println(new Candy().candyA(ratings));
        System.out.println(new Candy().candyB(ratings));
        System.out.println(new Candy().candyC(ratings));
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

    /**
     * O(n) Time, O(1) Space
     * Use a var to store decreasing sequence length
     * Use a var to store previous candies
     */
    public int candyB(int[] ratings) {
        if (ratings.length < 2)
            return ratings.length;
        int res = 1;
        int dec = 0;
        int gap = 0;
        int curCandy = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] < ratings[i - 1]) {
                if (dec == 0)
                    gap = curCandy;
                dec++;
                curCandy = 1;
                res += dec + curCandy;
                if (gap > 1) {
                    gap--;
                    res--;
                }
            } else {
                dec = 0;
                if (ratings[i] > ratings[i - 1])
                    curCandy++;
                else
                    curCandy = 1;
                res += curCandy;
            }
        }
        return res;
    }

    public int candyC(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        //from let to right
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                // if not ascending, assign 1
                candies[i] = 1;
            }
        }
        int result = candies[ratings.length - 1];
        //from right to left
        for (int i = ratings.length - 2; i >= 0; i--) {
            int cur = 1;
            if (ratings[i] > ratings[i + 1]) {
                cur = candies[i + 1] + 1;
            }
            result += Math.max(cur, candies[i]);
            candies[i] = cur;
        }
        return result;
    }

}
