package hard;

/**
 * Created by GAOSHANSHAN835 on 2015/12/29.
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 * 这道题其实就是让你找以index 0开始的最长的回文子串  找到之后就把缺的补齐即可
 * 即补充完成之后的回文串中心必定在原字符串中，所以原字符串以第一个字符为起点必然存在至少一个回文串（长度可以为1），
 * 那么任务就变为找到原字符串中以第一个字符为起点最长的回文串，
 * 找到之后剩下的工作就是把剩余部分的翻转补充到原字符串头部即可。
 * KMP中 你所知道的就只有当前这个匹配位置的相等的前缀和后缀的长度 当碰到失配的情况下
 * 就是要把数组最前面的那个公共前缀 移动到当前的位置 所以需要用当前位置 减去最长的长度
 * 在计算table的时候 如果碰到了两个字符不等的情况  因为table找的是公共的前缀还有后缀
 * 如果这两个字符不等  那么如果还有别的前后缀的话  那么只能存在于当前的前缀里  而且只要在递归的时候 查到了两个字符相等的情况 那么就说明这个是公共的前缀后缀
 * 其实 当在前缀里递归的时候  找到的是 当前递归层的共同的前缀后缀   只是这个后缀肯定和我们要计算的这个当前位置的后缀相等
 * 这道题的思路就是 如果一个字符串是回文的话  那么他的特性就是正序和倒序是一样的 所以我把这个字符串的正序和倒序连接起来
 * 就可以利用kmp的找公共前缀后缀的办法找到当前这个字符串最长的回文串
 * 加上'*'是为了防止
 * However, the Solution is apparently failed in the case "aabba".
 * The call on shortestPalindrome function will return "aabba" while the correct answer should be "abbaabba".
 */
public class ShortestPalindrome {
    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(shortestPalindromea(s));
        System.out.println(shortestPalindromea ("aabba"));
    }

    /**
     * 最好的
     */
    public static String shortestPalindromea(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) {
                j += 1;
            }
        }
        if (j == s.length()) {
            return s;
        }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindromea(
                s.substring(0, j)) + suffix;
    }

}
