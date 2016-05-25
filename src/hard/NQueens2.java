package hard;

import java.util.HashSet;
import java.util.Set;

/**
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of
 * distinct solutions.
 * 
 * Tags: Backtracking
 */
class NQueens2 {
    public static void main(String[] args) {
        System.out.println(totalNQueens(2));
        System.out.println(totalNQueens(3));
        System.out.println(totalNQueens(4));
        System.out.println(totalNQueens(5));
    }
    
    /**
     * backtrace program using bit-wise operation to speed up calculation.
     * 'limit'is all '1's.
     * 'h'    is the bits all the queens vertically projected on a row. If
     *        h==limit, then it's done, answer++.
     * 'r'    is the bits all the queens anti-diagonally projected on a row.
     * 'l'    is the bits all the queens diagonally projected on a row.
     * h|r|l  is all the occupied bits. Then pos = limit & (~(h|r|l)) is all
     *        the free positions.
     * p = pos & (-pos)
     *        gives the right most '1'. pos -= p means we will place
     *        a queen on this bit represented by p.
     * 'h+p'  means one more queue vertically projected on next row.
     * '(r+p) << 1'
     *        means one more queue anti-diagonally projected on next row.
     *        Because we are moving to next row and the projection is skew from
     *        right to left, we have to shift left one position after moved to
     *        next row.
     * '(l+p) >> 1'
     *        means one more queue diagonally projected on next row. Because we
     *        are moving to next row and the projection is skew from left to
     *        right, we have to shift right one position after moved to next
     *        row.
     * https://oj.leetcode.com/discuss/743/whats-your-solution
     */
    public static int totalNQueens(int n) {
        ans = 0;
        limit = (1 << n) - 1; // note that parentheses can't be ignored
        dfs(0, 0, 0);
        return ans;
    }
    static int ans, limit;
    /**Backtracking*/
    public static void dfs(int h, int r, int l) {
        if (h == limit) { 
            ans++;
            return;
        }
        int pos = limit & (~(h|r|l));
        while (pos != 0) { // has position
            int p = pos & (-pos); // right most 1
            pos -= p; // place a queen, 1 -> 0 on that position
            dfs(h + p, (r + p) << 1, (l + p) >> 1);
        }
    }

    /**
     * don't need to actually place the queen,
     * instead, for each row, try to place without violation on
     * col/ diagonal1/ diagnol2.
     * trick: to detect whether 2 positions sit on the same diagnol:
     * if delta(col, row) equals, same diagnol1;
     * if sum(col, row) equals, same diagnal2.
     */
    private final Set<Integer> occupiedCols   = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag1s = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag2s = new HashSet<Integer>();
    public int totalNQueensB(int n) {
        return totalNQueensHelper(0, 0, n);
    }
    private int totalNQueensHelper(int row, int count, int n) {
        for (int col = 0; col < n; col++) {
            if (occupiedCols.contains(col))
                continue;
            int diag1 = row - col;
            if (occupiedDiag1s.contains(diag1))
                continue;
            int diag2 = row + col;
            if (occupiedDiag2s.contains(diag2))
                continue;
            // we can now place a queen here
            if (row == n-1)
                count++;
            else {
                occupiedCols.add(col);
                occupiedDiag1s.add(diag1);
                occupiedDiag2s.add(diag2);
                count = totalNQueensHelper(row+1, count, n);
                // recover
                occupiedCols.remove(col);
                occupiedDiag1s.remove(diag1);
                occupiedDiag2s.remove(diag2);
            }
        }
        return count;
    }
}
