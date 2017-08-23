package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * Tags: Math
 * 涉及了进制转换 挺重要的题
 * 进制转换的时候 取余就是当前位值
 * 然后剩下的位数要除以26
 * 注意一下减一
 */
class ExcelSheetColTitle {
    public static void main(String[] args) {
        System.out.println(excelSheetColTitle(28));
    }

    /**
     * 最好的
     * creek 1 -> A  28 -> AB
     * The key is n--. The minimum in 26-bit number is mapped to 1, not 0.
     */
    public static String excelSheetColTitle(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is not valid!");
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;  //the key????
            char ch = (char) (n % 26 + 'A');
            n /= 26;
            sb.append(ch);
        }
        sb.reverse();
        return sb.toString();
    }

}
