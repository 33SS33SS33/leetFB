package easy;

import java.util.*;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * Tags: Array
 */

/**两边一定是1 中间的就是i+i-1*/
class PascalsTriangle {
    public static void main(String[] args) {
        PascalsTriangle p = new PascalsTriangle();
        int k = 3;
        System.out.println(p.generate(k).toString());
        System.out.println(p.generateB(k).toString());
    }

    /**
     * Definition
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if (numRows <= 0)
            return triangle;
        List<Integer> firstRow = new ArrayList<Integer>();
        firstRow.add(1);
        triangle.add(firstRow);
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = triangle.get(i - 1);
            List<Integer> row = new ArrayList<Integer>(i + 1);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
            }
            triangle.add(row);
        }
        return triangle;
    }

    /**
     * creek----
     */
    public ArrayList<ArrayList<Integer>> generateB(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (numRows <= 0)
            return result;
        ArrayList<Integer> pre = new ArrayList<Integer>();
        pre.add(1);
        result.add(pre);
        for (int i = 2; i <= numRows; i++) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            cur.add(1); //first
            for (int j = 0; j < pre.size() - 1; j++) {
                cur.add(pre.get(j) + pre.get(j + 1)); //middle
            }
            cur.add(1);//last

            result.add(cur);
            pre = cur;
        }
        return result;
    }
}