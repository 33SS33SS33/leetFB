package hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * 可以把所有都看成加法只是加正数还是加负数
 * 所以有一个sign来记录标志位
 * 如果碰见括号 就先把之前的结果还有sign保存进栈 然后括号结束在拿出来计算
 * 记得num sign 还有res的清零
 * 这道题的整体逻辑其实是先拼出来一个数字 然后如果碰见了加减号 就先把之前的那个带加减号的运算算了
 * 如果碰见了括号 之前的res sign 入栈 然后res sign清零 开始计算括号里面的
 * 所以返回的时候要把最后一个数字给算了
 * Some examples: "1 + 1" = 2, "(1)" = 1, "(1-(4-5))" = 2
 */
public class BasicCalculator {
    enum TokenType {DIGIT, OP}

    static class Token {
        TokenType type;
        int       val;

        public Token(int val, TokenType type) {
            this.val = val;
            this.type = type;
        }
    }

    static final Token   EOL    = new Token(0, TokenType.OP);
    static final Token[] TOKENS = new Token[256];

    static {
        TOKENS['+'] = new Token('+', TokenType.OP);
        TOKENS['-'] = new Token('-', TokenType.OP);
        TOKENS['*'] = new Token('*', TokenType.OP);
        TOKENS['/'] = new Token('/', TokenType.OP);
        TOKENS['('] = new Token('(', TokenType.OP);
        TOKENS[')'] = new Token(')', TokenType.OP);
    }

    static class Tokenizer {
        Scanner scanner;

        Tokenizer(String s) {
            scanner = new Scanner(s);
            scanner.useDelimiter("");
        }

        Token next() {
            if (!scanner.hasNext()) {
                return EOL;
            }
            boolean num = false;
            int buf = 0;
            while (scanner.hasNextInt()) {
                num = true;
                buf = buf * 10 + scanner.nextInt();
            }
            if (num) {
                return new Token(buf, TokenType.DIGIT);
            }
            char c = scanner.next().charAt(0);
            if (TOKENS[c] != null) {
                return TOKENS[c];
            }
            return next();
        }
    }

    static class RPNCalculator {
        LinkedList<Integer> stack = new LinkedList<Integer>();

        void addToken(Token t) {
            if (t.type == TokenType.OP) {
                int v2 = stack.pop();
                int v1 = stack.pop();
                switch (t.val) {
                case '+':
                    stack.push(v1 + v2);
                    break;
                case '-':
                    stack.push(v1 - v2);
                    break;
                case '*':
                    stack.push(v1 * v2);
                    break;
                case '/':
                    stack.push(v1 / v2);
                    break;
                default:
                    // cant happen
                }
            } else { // DIGIT
                stack.push(t.val);
            }
        }

        int val() {
            return stack.peek();
        }
    }

    public int calculate(String s) {
        RPNCalculator calculator = new RPNCalculator();
        Tokenizer tokenizer = new Tokenizer(s);
        LinkedList<Token> op = new LinkedList<Token>();
        Token t;
        next:
        while ((t = tokenizer.next()) != EOL) {
            // convert to RPN
            if (t.type == TokenType.DIGIT) {
                calculator.addToken(t);
            } else { // type == OP
                retry:
                while (true) {
                    if (op.isEmpty()) {
                        op.push(t);
                        continue next;
                    }
                    Token top = op.peek();
                    switch (t.val) {
                    case '(':
                        op.push(t);
                        break;
                    case '+':
                    case '-':
                        if (top.val == '+' || top.val == '-' || top.val == '*' || top.val == '/') {
                            calculator.addToken(op.pop());
                            continue retry;
                        }
                        op.push(t);
                        break;
                    case '*':
                    case '/':
                        if (top.val == '*' || top.val == '/') {
                            calculator.addToken(op.pop());
                            continue retry;
                        }
                        op.push(t);
                        break;
                    case ')':
                        while (!op.isEmpty()) {
                            top = op.pop();
                            if (top.val == '(')
                                continue next;
                            calculator.addToken(top);
                        }
                    default:
                        // cant happen
                    }
                    continue next;
                }
            }
        }
        while (!op.isEmpty()) {
            calculator.addToken(op.pop());
        }
        return calculator.val();
    }

    /**
     * creek
     */
    public int calculateB(String s) {
        // delte white spaces
        s = s.replaceAll(" ", "");
        Stack<String> stack = new Stack<String>();
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ')
                continue;
            if (arr[i] >= '0' && arr[i] <= '9') {
                sb.append(arr[i]);
                if (i == arr.length - 1) {
                    stack.push(sb.toString());
                }
            } else {
                if (sb.length() > 0) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                if (arr[i] != ')') {
                    stack.push(new String(new char[] { arr[i] }));
                } else {
                    // when meet ')', pop and calculate
                    ArrayList<String> t = new ArrayList<String>();
                    while (!stack.isEmpty()) {
                        String top = stack.pop();
                        if (top.equals("(")) {
                            break;
                        } else {
                            t.add(0, top);
                        }
                    }
                    int temp = 0;
                    if (t.size() == 1) {
                        temp = Integer.valueOf(t.get(0));
                    } else {
                        for (int j = t.size() - 1; j > 0; j = j - 2) {
                            if (t.get(j - 1).equals("-")) {
                                temp += 0 - Integer.valueOf(t.get(j));
                            } else {
                                temp += Integer.valueOf(t.get(j));
                            }
                        }
                        temp += Integer.valueOf(t.get(0));
                    }
                    stack.push(String.valueOf(temp));
                }
            }
        }
        ArrayList<String> t = new ArrayList<String>();
        while (!stack.isEmpty()) {
            String elem = stack.pop();
            t.add(0, elem);
        }
        int temp = 0;
        for (int i = t.size() - 1; i > 0; i = i - 2) {
            if (t.get(i - 1).equals("-")) {
                temp += 0 - Integer.valueOf(t.get(i));
            } else {
                temp += Integer.valueOf(t.get(i));
            }
        }
        temp += Integer.valueOf(t.get(0));
        return temp;
    }
}
