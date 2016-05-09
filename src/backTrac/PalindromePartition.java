package backTrac;

import java.util.*;
/**
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 * Tags: Backtracking
 */
/**用的DFS
 把当前的字符串从头开始切割 如果切出来的是回文 就把剩下的字符串继续切 然后继续判断 直到切完了整个字符串
 回文的特点是正反都一样 s == s[::-1] 后面的切片就是把字符串逆序输出
 还要注意切片的循环的范围选择for i in xrange(1,len(s)): 这里用的是从1到len(s) 这样可以保证不会切出来空的list
 但是也会导致并没有对s整体进行判断(因为最大切到倒数第二位)
 所以在下面又加了if对整体的s以及长度等于1的s进行判断*/
class PalindromePartition {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
        System.out.println(new PalindromePartition().partitionB("aab"));
        System.out.println(new PalindromePartition().partitionC("aab"));
        System.out.println(new PalindromePartition().palindromePartitioning("aab"));
        System.out.println(new PalindromePartition().partitionD("aab"));

        System.out.println(partition("aa"));
        System.out.println(new PalindromePartition().partitionB("aa"));
        System.out.println(new PalindromePartition().partitionC("aa"));
        System.out.println(new PalindromePartition().partitionD("aa"));
        System.out.println(new PalindromePartition().palindromePartitioning("aa"));
    }
    
    /**
     * Backtracking
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) return res;
        partition(s, 0, res, new ArrayList<String>());
        return res;
    }
    public static void partition(String s, int pos, List<List<String>> res, List<String> cut) {
        if (pos == s.length()) { // note the stop condition
            res.add(new ArrayList<String>(cut)); // dereference
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {
            String prefix = s.substring(pos, i);
            if (isPalindrome(prefix)) {
                cut.add(prefix);
                partition(s, i, res, cut); // update pos with i
                cut.remove(cut.size() - 1);
            }
        }
    }
    
    private static boolean isPalindrome(String str) {
        int s = 0;
        int e = str.length() - 1;
        while (s < e) {
            if (str.charAt(s) != str.charAt(e)) return false;
            s++;
            e--;
        }
        return true;
    }

    public ArrayList<ArrayList<String>> partitionB(String s) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if(s==null || s.length()==0)
            return res;
        helper(s, getDict(s),0,new ArrayList<String>(), res);
        return res;
    }
    private void helper(String s, boolean[][] dict, int start, ArrayList<String> item, ArrayList<ArrayList<String>> res) {
        if(start==s.length()) {
            res.add(new ArrayList<String>(item));
            return;
        }
        for(int i=start;i<s.length();i++) {
            if(dict[start][i]) {
                item.add(s.substring(start,i+1));
                helper(s,dict,i+1,item,res);
                item.remove(item.size()-1);
            }
        }
    }
    private boolean[][] getDict(String s) {
        boolean[][] dict = new boolean[s.length()][s.length()];
        for(int i=s.length()-1;i>=0;i--) {
            for(int j=i;j<s.length();j++) {
                if(s.charAt(i)==s.charAt(j) && ((j-i<2)||dict[i+1][j-1])) {
                    dict[i][j] = true;
                }
            }
        }
        return dict;
    }

    public List<List<String>> partitionC(String s) {
        List<List<String>> rt = new ArrayList<List<String>>();
        if("".equals(s)) return rt;
        if(s.length() == 1) return Arrays.asList(Arrays.asList(s));
        for(int i = 0; i < s.length(); i++){
            String x = s.substring(0, i + 1);
            if(isPal(x)){
                List<List<String>> sub = partition(s.substring(i + 1));
                if(sub.isEmpty()){
                    rt.add(Arrays.asList(x));
                } else {
                    for(List<String> l : sub){
                        ArrayList<String> _l = new ArrayList<String>();
                        _l.add(x);
                        _l.addAll(l);
                        rt.add(_l);
                    }
                }
            }
        }
        return rt;
    }
    boolean isPal(String s){
        char[] S = s.toCharArray();
        for(int i = 0; i < S.length / 2; i++){
            if(S[i] != S[S.length - i - 1])
                return false;
        }
        return true;
    }
    /**
     * creek DP
     */
    public static List<String> palindromePartitioning(String s) {
        List<String> result = new ArrayList<String>();
        if (s == null)
            return result;
        if (s.length() <= 1) {
            result.add(s);
            return result;
        }
        int length = s.length();
        int[][] table = new int[length][length];
        // l is length, i is index of left boundary, j is index of right boundary
        for (int l = 1; l <= length; l++) {
            for (int i = 0; i <= length - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (l == 1 || l == 2) {
                        table[i][j] = 1;
                    } else {
                        table[i][j] = table[i + 1][j - 1];
                    }
                    if (table[i][j] == 1) {
                        result.add(s.substring(i, j + 1));
                    }
                } else {
                    table[i][j] = 0;
                }
            }
        }
        return result;
    }


    /**creek----Depth-first Search*/
    public ArrayList<ArrayList<String>> partitionD(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (s == null || s.length() == 0) {
            return result;
        }
        ArrayList<String> partition = new ArrayList<String>();//track each possible partition
        addPalindrome(s, 0, partition, result);
        return result;
    }
    private void addPalindrome(String s, int start, ArrayList<String> partition,
            ArrayList<ArrayList<String>> result) {
        //stop condition
        if (start == s.length()) {
            ArrayList<String> temp = new ArrayList<String>(partition);
            result.add(temp);
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (isPalindrome(str)) {
                partition.add(str);
                addPalindrome(s, i, partition, result);
                partition.remove(partition.size() - 1);
            }
        }
    }

}
