package aMaz;

import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * 这道题 是先把符号存下来 然后检测符号的时候 指针已经指向了符号后面的那个数字(所以最开始默认是加号为了把第一个数字入栈)
 * 然后如果碰见加号 就把之前拼出来的数字入栈 减号的话就把那个数字取反入栈
 * 如果是乘号就要把栈里之前的数字弹出来做乘法之后再入栈 除法也一样
 * 然后返回结果把栈里的数字累加起来即可
 */
public class BasicCalculatorII {
    /**
     * the basic idea is scan the string from left to right, when we meet a * or /,
     * means we need to calculate the number between this sign and put the result back to stack. After the iteration.
     * We have finished the * and / calculation, what left to do is the + and - operation. Just add all number in the stack.
     */
    //170824 microsoft
    public int basicCalculatorII(String s) {
        int len;
        if (s == null || (len = s.length()) == 0)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';//重要
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';//重要
            }
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }

}
