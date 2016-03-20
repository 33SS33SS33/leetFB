package backTrac;

import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ
 * in only one bit.
 *
 * Given a non-negative integer n representing the total number of bits in the
 * code, print the sequence of gray code. A gray code sequence must begin with
 * 0.
 *
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 *
 * For example, [0,2,3,1] is also a valid gray code sequence according to the
 * above definition.
 * For now, the judge is able to judge based on one instance of gray code
 * sequence. Sorry about that.
 * 
 * Tags: Backtracking
 */
class Graycode {
    public static void main(String[] args) {
        System.out.println(new Graycode().grayCode(3));
        System.out.println(new Graycode().grayCode2(3));
    }

    /**
     * generate 0, 1 then add 10 from back to get 11, 10
     * same goes for 00, 01, 11, 10, add 100 to get 110, 111, 101, 100
     */
    public List<Integer> grayCode(int n) {
        List<Integer> results = new ArrayList<Integer>();
        results.add(0); // starts from 0
        for (int i = 0; i < n; i++) {
            int inc = 1 << i; // move 1 i times
            for (int j = results.size() - 1; j >= 0; j--) { // backtracking
                results.add(results.get(j) + inc);
            }
        }
        return results;
    }
    public ArrayList<Integer> grayCode2(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(n<0)
            return res;
        if(n==0) {
            res.add(0);
            return res;
        }
        res.add(0);
        res.add(1);
        for(int i=2;i<=n;i++) {
            int size = res.size();
            for(int j=size-1;j>=0;j--) {
                res.add(res.get(j)+(1<<(i-1)));
            }
        }
        return res;
    }
}