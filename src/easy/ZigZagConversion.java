package easy;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for
 * better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a
 * number of rows:
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * Tags: String
 */
class ZigZagConversion {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int nRows = 3;
        System.out.println(new ZigZagConversion().convert(s, nRows));
        System.out.println(new ZigZagConversion().convertB(s, nRows));
    }

    /**
     * Build an array of StringBuffers 最好的
     * Traverse the given string and append characters in correct StringBuffer
     * Append all other rows to first row to get output
     */
    public String convert(String s, int nRows) {
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++)
            sb[i] = new StringBuffer();
        int len = s.length();
        int i = 0;
        while (i < len) {
            for (int j = 0; j < nRows && i < len; j++) { // from 0 to nRows - 1 / vertically down
                sb[j].append(s.charAt(i++));
            }
            for (int j = nRows - 2; j > 0 && i < len; j--) { // nRows - 2 to 1  obliquely up
                sb[j].append(s.charAt(i++));
            }
        }
        for (int k = 1; k < nRows; k++)
            sb[0].append(sb[k]);
        return sb[0].toString();
    }

    public String convertB(String s, int numRows) {
        if (numRows == 1)
            return s;
        StringBuilder sb = new StringBuilder();
        int step = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            //first & last rows
            if (i == 0 || i == numRows - 1) {
                for (int j = i; j < s.length(); j = j + step) {
                    sb.append(s.charAt(j));
                }
                //middle rows
            } else {
                int j = i;
                boolean flag = true;
                int step1 = 2 * (numRows - 1 - i);
                int step2 = step - step1;
                while (j < s.length()) {
                    sb.append(s.charAt(j));
                    if (flag)
                        j = j + step1;
                    else
                        j = j + step2;
                    flag = !flag;
                }
            }
        }
        return sb.toString();
    }
}
