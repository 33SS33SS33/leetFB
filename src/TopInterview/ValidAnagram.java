package TopInterview;

public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram r = new ValidAnagram();
        String a = "anagram";
        String b = "nagaram";
        System.out.println(r.validAnagram(a, b));
    }

    /**
     * Given two strings s and t, write a function to determine if t is an anagram of s.
     * s = "anagram", t = "nagaram", return true. s = "rat", t = "car", return false.
     * Note: You may assume the string contains only lowercase alphabets.
     * Follow up: What if the inputs contain unicode characters?
     * How would you adapt your solution to such case?
     * The idea is simple. It creates a size 26 int arrays as buckets for each letter in
     * alphabet. It increments the bucket value with String s and decrement with string t.
     * So if they are anagrams, all buckets should remain with initial value which is zero.
     * So just checking that and return
     * 用哈希表统计或者直接排序就行
     */
    public boolean validAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

/*    public boolean validAnagram(String s, String t) {
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        Arrays.sort(S);
        Arrays.sort(T);
        return Arrays.equals(S, T);
    }*/

}
