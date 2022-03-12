package aMaz;

import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18. HARD
 */

public class BasicCalculator {

    //https://discuss.leetcode.com/topic/15816/iterative-java-solution-with-stack/2
    /**
     * Implement a basic calculator to evaluate a simple expression string.
     * The expression string may contain open ( and closing parentheses ),
     * the plus + or minus sign -, non-negative integers and empty spaces .
     * Input: "1 + 1"  Output: 2
     * Input: " 2-1 + 2 " Output: 3
     * Input: "(1+(4+5+2)-3)+(6+8)"  Output: 23
     * Note: You may assume that the given expression is always valid.
     * Do not use the eval built-in library function.
     * 可以把所有都看成加法只是加正数还是加负数 所以有一个sign来记录标志位
     * 如果碰见括号 就先把之前的结果还有sign保存进栈 然后括号结束在拿出来计算
     * 记得num sign 还有res的清零
     * 这道题的整体逻辑其实是先拼出来一个数字 然后如果碰见了加减号 就先把之前的那个带加减号的运算算了
     * 如果碰见了括号 之前的res sign 入栈 然后res sign清零 开始计算括号里面的
     * 所以返回的时候要把最后一个数字给算了
     * Some examples: "1 + 1" = 2, "(1)" = 1, "(1-(4-5))" = 2
     */
    public static int basicCalculator(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += num * sign;
            } else if (s.charAt(i) == '+')
                sign = 1;
            else if (s.charAt(i) == '-')
                sign = -1;
            else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }

}
