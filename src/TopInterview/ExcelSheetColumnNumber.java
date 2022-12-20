package TopInterview;


class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        System.out.println(ExcelSheetColNum("AA"));
    }

    /**
     * Go through the title
     * AA -> 27
     * AB -> 28
     * Map A ~ Z to 1 ~ 26
     * next result = current res * 26 + number of current letter
     * Related to question Excel Sheet Column Title
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     * A -> 1
     * B -> 2
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28  Tags: Math
     * 还是一样 从后倒着往前走 但是有一个count要记录现在是几次方了
     */
    public static int ExcelSheetColNum(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }
}