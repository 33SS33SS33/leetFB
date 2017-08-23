package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 * Tags: String
 */
class CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSaya(5));
    }

    /**
     * Build from n - 1 to n 最好的
     * Traverse and get count of each char and append to result
     */
    public static String countAndSay(int n) {
        String res = "1";
        while (--n > 0) {
            StringBuilder sb = new StringBuilder();
            char[] prev = res.toCharArray();
            for (int i = 0; i < prev.length; i++) {
                int count = 1; // initialize current count as 1
                while (i + 1 < prev.length && prev[i] == prev[i + 1]) {
                    count++; // searchinRotatedSortedArrayb for same char
                    i++;
                }
                sb.append(count).append(prev[i]);
            }
            res = sb.toString();
        }
        return res;
    }

    //????
    public static String countAndSaya(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        for (int i = 1; i < n; i++) {
            prev = curr;
            curr = new StringBuilder();
            count = 1;
            say = prev.charAt(0);
            for (int j = 1, len = prev.length(); j < len; j++) {
                if (prev.charAt(j) != say) {
                    curr.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else count++;
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }

}