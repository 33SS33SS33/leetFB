package aMaz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations. TODO
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note:
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Input: n = 3, k = 3 Output: "213"
 * Input: n = 4, k = 9 Output: "2314"
 * Tags: Backtracking, Math
 * 使用dfs会超时
 * 需要用到康托展开 可以看下面的帖子
 * http://www.androiddev.net/%E5%BA%B7%E6%89%98%E5%B1%95%E5%BC%80%E5%8F%8A%E5%85%B6%E9%80%86%E8%BF%90%E7%AE%97/
 * 关键的地方就是
 * K=an*(n-1)!+an-1*(n-2)!+…+ai*(i-1)!+…+a2*1!+a1*0!
 * 所以an = K//(n-1)! 而这个除法的余数就是an-1*(n-2)!+…+ai*(i-1)!+…+a2*1!+a1*0!之和 
 * 然后在用这个余数除以(n-2)! 以此类推
 */
class PermutationSequence {
    public static void main(String[] args) {
        System.out.println(permutationSequence(3, 1)); // 123
        System.out.println(permutationSequence(3, 6)); // 321
/*        System.out.println(permutationSequenceb(3, 1)); // 123
        System.out.println(permutationSequenceb(3, 5)); // 312
        System.out.println(permutationSequenceb(3, 6)); // 321
        System.out.println(permutationSequencec(3, 6)); // 321*/
    }

    public static String permutationSequence(int n, int k) {
        List<Integer> num = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            num.add(i);
        int[] fact = new int[n]; // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++)
            fact[i] = i * fact[i - 1];
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int ind = k / fact[i - 1];
            k = k % fact[i - 1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }

    /**
     * Initialize a list of digits to build the result Build from first character
     */
/*    public static String permutationSequenceb(int n, int k) {
        k = k - 1;
        int factor = 1;
        for (int i = 1; i < n; i++)
            factor *= i;
        List<Integer> digits = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            digits.add(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int index = k / factor;
            sb.append(digits.get(index));
            digits.remove(index); // remove used digit
            k = k % factor;
            if (i < n - 1)
                factor = factor / (n - 1 - i);
        }
        return sb.toString();
    }*/

    //creek
/*    public static String permutationSequencec(int n, int k) {
        ArrayList<Integer> numberList = new ArrayList<Integer>(); // initialize all numbers
        for (int i = 1; i <= n; i++) {
            numberList.add(i);
        }
        k--; // change k to be index
        int mod = 1;// set factorial of n
        for (int i = 1; i <= n; i++) {
            mod = mod * i;
        }
        String result = "";
        for (int i = 0; i < n; i++) { // find sequence
            mod = mod / (n - i);
            int curIndex = k / mod; // find the right number(curIndex) of
            k = k % mod; // update k
            result += numberList.get(curIndex); // get number according to curIndex
            numberList.remove(curIndex); // remove from list
        }
        return result.toString();
    }*/

}
