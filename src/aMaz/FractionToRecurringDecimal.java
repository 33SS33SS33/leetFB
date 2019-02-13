package aMaz;

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
//        System.out.println(f.fractionToDecimalb(1, 2));
        System.out.println(f.fractionToDecimal(2, 3));
        System.out.println(f.fractionToDecimal(Integer.MAX_VALUE, Integer.MIN_VALUE));
//        System.out.println(f.fractionToDecimalC(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    /**
     * Valid input, denominator can't be zero 最好的
     * Convert to long to avoid overflow
     * Divide into three parts, sign, before dot and after dot
     * Before dot = numerator / denominator
     * After dot = remainder * 10 / denominator
     * if already showed up, insert parentheses
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

/*    public String fractionToDecimalb(int numerator, int denominator) {
        if (denominator == 0)
            return "";
        if (numerator == 0)
            return "0";
        StringBuilder res = new StringBuilder();
        Long n = new Long(numerator); // zigZagConversion to long
        Long d = new Long(denominator);
        if ((n < 0 && d > 0) || (n > 0 && d < 0))
            res.append("-"); // negative
        n = Math.abs(n); // to abstract value
        d = Math.abs(d);
        res.append(n / d); // before dot 
        if (n % d == 0)
            return res.toString(); // no fraction
        res.append("."); // add dot
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        Long r = n % d; // get first remainder
        while (r > 0) {
            if (map.containsKey(r)) { // remainder appeared before
                res.insert(map.get(r), "("); // insert an open paren
                res.append(")"); // append a close paren
                break;
            }
            map.put(r, res.length()); // save remainder and the length
            r *= 10; // simulate long division
            res.append(r / d);
            r %= d; // get next remainder
        }
        return res.toString();
    }*/


    /**
     * creek---
     */
/*    public String fractionToDecimalC(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        if (denominator == 0)
            return "";
        String result = "";
        // is result is negative
        if ((numerator < 0) ^ (denominator < 0)) {
            result += "-";
        }
        // zigZagConversion int to long
        long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);
        // quotient
        long res = num / den;
        result += String.valueOf(res);
        // if remainder is 0, return result
        long remainder = (num % den) * 10;
        if (remainder == 0)
            return result;
        // right-hand side of decimal point
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        result += ".";
        while (remainder != 0) {
            // if digits repeat
            if (map.containsKey(remainder)) {
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg, result.length());
                result = part1 + "(" + part2 + ")";
                return result;
            }
            // continue
            map.put(remainder, result.length());
            res = remainder / den;
            result += String.valueOf(res);
            remainder = (remainder % den) * 10;
        }
        return result;
    }*/

}
