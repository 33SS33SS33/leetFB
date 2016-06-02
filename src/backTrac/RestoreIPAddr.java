package backTrac;

import java.util.*;
/**
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * Tags: Backtracking, String
 *
 * 用DFS
 * 递归的将数字串分成四个部分，每个部分满足0<=p<=255。要注意一些边界case，
 * 比如010是没有意思的，“0.10.010.1”。
 */
class RestoreIPAddr {
    public static void main(String[] args) {
        List<String> r1 = restoreIPAddressesA("25525511135");
        List<String> r2 = restoreIPAddressesA("010010");
        System.out.println(r1.toString());
        System.out.println(r2.toString());

        List<String> r3 = restoreIPAddressesB("25525511135");
        List<String> r4 = restoreIPAddressesB("010010");
        System.out.println(r3.toString());
        System.out.println(r4.toString());
    }

    /**
     * 最好的
     * Figure out what is a valid IP address
     * Use backtracking to insert dots into string
     */
    public static List<String> restoreIPAddressesA(String s) {
        List<String> res = new ArrayList<String>();
        if (s.length() < 4 && s.length() > 12)
            return res;
        backtrack(s, 3, res, "");
        return res;
    }

    /**
     * @param s   current string for this backtrack
     * @param dot how many dots left, when equals 0, add last value to result
     * @param res result list of strings
     * @param ip  current ip for this backtrack
     */
    public static void backtrack(String s, int dot, List<String> res, String ip) {
        if (dot == 0) {
            if (isValid(s)) {
                ip += s;
                res.add(ip);
            }
            return;
        }
        for (int i = 1; i < 4 && i < s.length(); i++) {
            String pre = s.substring(0, i);
            if (isValid(pre))
                backtrack(s.substring(i), dot - 1, res, ip + pre + ".");
        }
    }

    /**
     * A valid IP address, each block should be from 0 to 255
     * Should not be 0X or 0XX
     */
    static boolean isValid(String s) {
        if (s.startsWith("0") && s.length() > 1 || Integer.valueOf(s) > 255)
            return false;
        return true;
    }

    /**
     * creek This is a typical search problem and it can be solved by using DFS.
     */
    public static List<String> restoreIPAddressesB(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> t = new ArrayList<String>();
        dfs(result, s, 0, t);
        ArrayList<String> finalResult = new ArrayList<String>();
        for (ArrayList<String> l : result) {
            StringBuilder sb = new StringBuilder();
            for (String str : l) {
                sb.append(str + ".");
            }
            sb.setLength(sb.length() - 1);
            finalResult.add(sb.toString());
        }
        return finalResult;
    }

    public static void dfs(ArrayList<ArrayList<String>> result, String s, int start,
            ArrayList<String> t) {
        //if already get 4 numbers, but s is not consumed, return
        if (t.size() >= 4 && start != s.length())
            return;
        //make sure t's size + remaining string's length >=4
        if ((t.size() + s.length() - start + 1) < 4)
            return;
        //t's size is 4 and no remaining part that is not consumed.
        if (t.size() == 4 && start == s.length()) {
            ArrayList<String> temp = new ArrayList<String>(t);
            result.add(temp);
            return;
        }
        for (int i = 1; i <= 3; i++) {
            //make sure the index is within the boundary
            if (start + i > s.length())
                break;
            String sub = s.substring(start, start + i);
            //handle case like 001. i.e., if length > 1 and first char is 0, ignore the case.
            if (i > 1 && s.charAt(start) == '0') {
                break;
            }
            //make sure each number <= 255
            if (Integer.valueOf(sub) > 255)
                break;
            t.add(sub);
            dfs(result, s, start + i, t);
            t.remove(t.size() - 1);
        }
    }
}
