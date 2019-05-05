package aMaz;


class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"absss", "abddrre", "abs"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
    }

    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     * Input: ["flower","flow","flight"]Output: "fl"
     * Input: ["dog","racecar","car"] Output: ""
     * Explanation: There is no common prefix among the input strings.
     * Note:All given inputs are in lowercase letters a-z.
     * Tags: String
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }

}
