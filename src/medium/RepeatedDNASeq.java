package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/8.
 */

import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and
 * T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 * For example,
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 * Tags: Bit Manipulation
 */

/**
 * 这个题首先想到的是用字典解决 从头到尾扫描每个子串 如果字典没有就添加子串进字典 如果有了就说明出现了重复 但是这样会memory超限
 * 为了结局memory超限的问题
 * 可以用位运算 A:00 C:01 G:10 T:11
 * 把字典的key转化位数字 就可以节省空间
 * 就是每次左移两位 然后取出当前位置的数字 然后再去和字典比较即可
 * 记住要用mask来只取20位的数字
 */
class RepeatedDNASeq {
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> res = findRepeatedDnaSequences(s);

        /**错的？？？？？*/
        List<String> res2 = findRepeatedDnaSequencesB(s);
        List<String> res3 = findRepeatedDnaSequencesC(s);
        System.out.println(res);
        System.out.println(res2);
        System.out.println(res3);
    }

    /**
     * To optimize space usage, map string to other key that won't collide
     * Design a hash function according to observation
     * A: 0x41, C: 0x43, G: 0x47, T: 0x54, last 3 bits are different
     * 10 chars, each 3 bits, 10 x 3 = 30 bits < 32
     * <p/>
     * Key: an int to record the bit mask of current substring,
     * Value: a boolean, true means showed up before, false means already added
     * Update the map
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() < 10)
            return res;
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for (int t = 0, i = 0; i < s.length(); i++) {
            t = (t << 3 & 0x3FFFFFFF) | (s.charAt(i) & 7);
            if (map.containsKey(t)) {
                if (map.get(t)) {
                    res.add(s.substring(i - 9, i + 1));
                    map.put(t, false);
                }
            } else {
                map.put(t, true);
            }
        }
        return res;
    }

    /**
     * HashSet with previous appeared results
     * O(n) Space
     */
    public static List<String> findRepeatedDnaSequencesB(String s) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() < 10)
            return res;
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (set.contains(sub)) {
                res.add(s);
            }
            set.add(s);
        }
        return res;
    }

    /**
     * creek
     * For example, given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", return: ["AAAAACCCCC", "CCCCCAAAAA"].
     * The key to solve this problem is that each of the 4 nucleotides can be stored in 2 bits.
     * So the 10-letter-long sequence can be converted to 20-bits-long integer.
     */

    public static List<String> findRepeatedDnaSequencesC(String s) {
        List<String> result = new ArrayList<String>();
        int len = s.length();
        if (len < 10) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Set<Integer> temp = new HashSet<Integer>();
        Set<Integer> added = new HashSet<Integer>();
        int hash = 0;
        for (int i = 0; i < len; i++) {
            if (i < 9) {
                //each ACGT fit 2 bits, so left shift 2
                hash = (hash << 2) + map.get(s.charAt(i));
            } else {
                hash = (hash << 2) + map.get(s.charAt(i));
                //make length of hash to be 20
                hash = hash & (1 << 20) - 1;
                if (temp.contains(hash) && !added.contains(hash)) {
                    result.add(s.substring(i - 9, i + 1));
                    added.add(hash); //track added
                } else {
                    temp.add(hash);
                }
            }
        }
        return result;
    }
}
