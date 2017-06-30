package medium;

import java.util.*;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * Tags: Stack
 * <p>
 * 用堆栈处理  碰见数字就入栈 碰见符号就把栈里的元素弹出计算 然后结果入栈
 * 用字典表示了各种符号的操作 很巧妙
 * 对于除法的计算 要注意python会对1/-100这种除法返回-1 只有用1/int(float(-100))这样才行
 */
class EvaluateReversePolish {
    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        // String[] tokens = {"4", "13", "5", "/", "+"};
        //        String[] tokens = { "3", "-4", "+" };
        System.out.println(evalRPNa(tokens));
        System.out.println(evalRPN(tokens));
        System.out.println(evalRPNA(tokens));
        System.out.println(evalRPNB(tokens));
    }

    /**
     * 最好的
     */
    public static int evalRPNa(String[] tokens) {
        int a, b;
        Stack<Integer> S = new Stack<Integer>();
        for (String s : tokens) {
            if (s.equals("+")) {
                S.add(S.pop() + S.pop());
            } else if (s.equals("/")) {
                b = S.pop();
                a = S.pop();
                S.add(a / b);
            } else if (s.equals("*")) {
                S.add(S.pop() * S.pop());
            } else if (s.equals("-")) {
                b = S.pop();
                a = S.pop();
                S.add(a - b);
            } else {
                S.add(Integer.parseInt(s));
            }
        }
        return S.pop();
    }

    public static int evalRPNA(String[] tokens) {
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
     * assign a priority for each operators
     * use a stack to store them
     * note the numbers can be negative
     * We evaluate the expression left-to-right and push operands onto the
     * stack until we encounter an operator, which we pop the top two values
     * from the stack. We then evaluate the operator, with the values as
     * arguments and push the result back onto the stack.
     */
    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;
        Stack<String> s = new Stack<String>();
        int len = tokens.length;
        for (int i = 0; i < len; i++) {
            String cur = tokens[i];
            if (isOperator(cur)) {
                int t2 = Integer.valueOf(s.pop());
                int t1 = Integer.valueOf(s.pop());
                int res = calculate(t1, t2, cur);
                s.push(res + "");
            } else
                s.push(cur);
        }
        return Integer.valueOf(s.peek());
    }

    /**
     * Helper function to check whether a token is operator or not
     */
    private static boolean isOperator(String c) {
        if (c.equalsIgnoreCase("+"))
            return true;
        if (c.equalsIgnoreCase("-"))
            return true;
        if (c.equalsIgnoreCase("*"))
            return true;
        if (c.equalsIgnoreCase("/"))
            return true;
        return false;
    }

    private static int calculate(int t1, int t2, String operator) {
        int res = 0;
        if (operator.equalsIgnoreCase("+"))
            res = t1 + t2;
        else if (operator.equalsIgnoreCase("-"))
            res = t1 - t2;
        else if (operator.equalsIgnoreCase("*"))
            res = t1 * t2;
        else if (operator.equalsIgnoreCase("/"))
            res = t1 / t2;
        return res;
    }

    /**
     * use switch statement
     */
    public static int evalRPNB(String[] tokens) {
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
