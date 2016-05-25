package medium;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * 这道题 是先把符号存下来 然后检测符号的时候 指针已经指向了符号后面的那个数字(所以最开始默认是加号 为了把第一个数字入栈)
 * 然后如果碰见加号 就把之前拼出来的数字入栈 减号的话就把那个数字取反入栈
 * 如果是乘号就要把栈里之前的数字弹出来做乘法之后再入栈 除法也一样
 * 然后返回结果把栈里的数字累加起来即可
 */
public class BasicCalculator2 {
    enum TokenType {DIGIT, OP}

    static class Token {
        TokenType type;
        int       val;

        public Token(int val, TokenType type) {
            this.val = val;
            this.type = type;
        }
    }

    static final Token EOL = new Token(0, TokenType.OP);

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
}
