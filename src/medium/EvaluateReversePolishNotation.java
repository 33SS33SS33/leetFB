package medium;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) throws IOException {
        String[] tokens = new String[] { "2", "1", "+", "3", "*" };
        System.out.println(evalRPN(tokens));
        System.out.println(evalRPN2(tokens));
    }

    public static int evalRPN(String[] tokens) {
        final Deque<Integer> stack = new LinkedList<Integer>();
        for (String t : tokens) {
            if ("+".equals(t)) {
                Integer v2 = stack.pop();
                Integer v1 = stack.pop();

                stack.push(v1 + v2);
            } else if ("-".equals(t)) {
                Integer v2 = stack.pop();
                Integer v1 = stack.pop();

                stack.push(v1 - v2);
            } else if ("*".equals(t)) {
                Integer v2 = stack.pop();
                Integer v1 = stack.pop();

                stack.push(v1 * v2);
            } else if ("/".equals(t)) {
                Integer v2 = stack.pop();
                Integer v1 = stack.pop();

                stack.push(v1 / v2);
            } else {
                stack.push(Integer.valueOf(t));
            }
        }
        return stack.pop();
    }

    /**
     * use switch statement
     */
    public static int evalRPN2(String[] tokens) {
        int returnValue = 0;
        String operators = "+-*/";
        Stack<String> stack = new Stack<String>();
        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack.push(t);
            } else {
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int index = operators.indexOf(t);
                switch (index) {
                case 0:
                    stack.push(String.valueOf(a + b));
                    break;
                case 1:
                    stack.push(String.valueOf(b - a));
                    break;
                case 2:
                    stack.push(String.valueOf(a * b));
                    break;
                case 3:
                    stack.push(String.valueOf(b / a));
                    break;
                }
            }
        }
        returnValue = Integer.valueOf(stack.pop());
        return returnValue;
    }

}
