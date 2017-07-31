package easy;

/**
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding
 * column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * Tags: Math
 * 还是一样 从后倒着往前走 但是有一个count要记录现在是几次方了
 */
class ExcelSheetColNum {
    public static void main(String[] args) {
        System.out.println(titleToNumber("AAA"));
        System.out.println(titleToNumberB("AAA"));
    }

    /**
     * Go through the title
     * Map A ~ Z to 1 ~ 26
     * next result = current res * 26 + number of current letter
     */
    public static int titleToNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + (s.charAt(i) - '@');
        }
        return res;
    }

    /**
     * creek
     */
    public static int titleToNumberB(String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Input is not valid!");
        }
        int result = 0;
        int i = s.length() - 1;
        int t = 0;
        while (i >= 0) {
            char curr = s.charAt(i);
            result = result + (int) Math.pow(26, t) * (curr - 'A' + 1);
            t++;
            i--;
        }
        return result;
    }

}