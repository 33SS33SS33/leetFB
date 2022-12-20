package TopInterview;

import java.util.HashMap;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 * Tags: Hashtable, Math
 * 最关键的还是要知道 循环小数的时候 余数也在循环
 * 模拟除法 需要用字典记录余数的位置
 * 整数部分比较好做 直接divmod然后记录下来商和余数
 * 然后每次把余数乘以10 再除以被除数 并且记录下来
 * 当出现循环的时候 余数也在循环 所以就用字典找出来第一个出现这个余数的位置 然后用括号把中间的括起来就可以
 */
class FractionToRecurringDecimal {
    public static void main(String[] args) {
        FractionToRecurringDecimal f = new FractionToRecurringDecimal();
        System.out.println(f.fractionToDecimal(1, 2));
        System.out.println(f.fractionToDecimal(2, 3));
        System.out.println(f.fractionToDecimal(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    /**
     * Valid input, denominator can't be zero 最好的
     * Convert to long to avoid overflow
     * Divide into three parts, sign, before dot and after dot
     * Before dot = numerator / denominator
     * After dot = remainder * 10 / denominator
     * if already showed up, insert parentheses
     * <p>
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     * Given numerator = 1, denominator = 2, return "0.5".
     * Given numerator = 2, denominator = 1, return "2".
     * Given numerator = 2, denominator = 3, return "0.(6)". Tags: Hashtable, Math
     */
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        String sign = (numerator < 0 == denominator < 0 || numerator == 0) ? "" : "-";
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        result.append(sign);
        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0)
            return result.toString();
        result.append(".");
        HashMap<Long, Integer> hashMap = new HashMap<>();
        while (!hashMap.containsKey(remainder)) {
            hashMap.put(remainder, result.length());
            result.append(10 * remainder / den);
            remainder = 10 * remainder % den;
        }
        int index = hashMap.get(remainder);
        result.insert(index, "(");
        result.append(")");
        return result.toString().replace("(0)", "");
    }
}
