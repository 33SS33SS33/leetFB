package aMaz;

/**
 * Created by shanshan on 2/12/19. TODO
 * Solve a given equation and return the value of x in the form of string "x=#value".
 * The equation contains only '+', '-' operation, the variable x and its coefficient.
 * If there is no solution for the equation, return "No solution".
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 * Input: "x+5-3+x=6+x-2" Output: "x=2"
 * Input: "x=x" Output: "Infinite solutions"
 * Input: "2x=x" Output: "x=0"
 */
public class SolvetheEquation {
    public String solveEquation(String e) {
        int num = 0, sign = 1, dsign = 1;
        int x = 0, y = 0;
        boolean hasNum = false;
        for (char c : e.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
                hasNum = true;
            } else if (c == 'x') {
                if (!hasNum) num = 1;
                x += sign * dsign * num;
                num = 0;
                hasNum = false;
                sign = 1;
            } else if (c == '+' || c == '-') {
                y += sign * dsign * num;
                num = 0;
                hasNum = false;
                sign = c == '+' ? 1 : -1;
            } else if (c == '=') {
                y += sign * dsign * num;
                num = 0;
                hasNum = false;
                sign = 1;
                dsign = -1;
            }
        }
        y += sign * dsign * num;
        if (x == 0) {
            if (y == 0) return "Infinite solutions";
            return "No solution";
        }
        return "x=" + (-y / x);
    }
}
