package aMaz;

import java.util.ArrayList;
import java.util.List;

/**
 * Tags: Backtracking, String
 * 用DFS
 * 递归的将数字串分成四个部分，每个部分满足0<=p<=255。要注意一些边界case，
 * 比如010是没有意思的，“0.10.010.1”。
 */
class RestoreIPAddresses {
    public static void main(String[] args) {
        List<String> r11 = new RestoreIPAddresses().restoreIpAddresses("25525511135");
        List<String> r12 = restoreIPAddressesA("25525511135");
        List<String> r21 = new RestoreIPAddresses().restoreIpAddresses("010010");
        List<String> r22 = restoreIPAddressesA("010010");

        System.out.println(r11.toString());
        System.out.println(r12.toString());
        System.out.println(r21.toString());
        System.out.println(r22.toString());
    }


    /**
     * Given a string containing only digits, restore it by returning all possible (medium)
     * valid IP address combinations.
     * Given "25525511135",
     * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
     * Figure out what is a valid IP address
     * Use backtracking to insert dots into string
     */
    public static List<String> restoreIPAddressesA(String s) {
        List<String> res = new ArrayList<>();
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

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        for (int i = 1; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k, len);
                    if (isValid1(s1) && isValid1(s2) && isValid1(s3) && isValid1(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }

    public boolean isValid1(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255)
            return false;
        return true;
    }
}
