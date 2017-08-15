package medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways
 * to group numbers and operators. The valid operators are +, - and *.
 * Example 1
 * Input: "2-1-1".
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 * 很重要的题目 可以用动归 DP 备忘录 未实现
 * 主要思路就是首先将输入的input分割 变成数字或者加减乘除的形式
 * Basic idea is using each operator to divide the whole string into three part:
 * sub_str1, operator, sub_str2
 * then recursively apply "compute" function on each sub_str.
 * Take "2*3-4*5" for example:
 * compute("2*3-4*5")
 * -->compute("2") * compute("3-4*5")
 * -->compute("2*3") - compute("4*5")
 * -->compute("2*3-4") * compute("5")
 * 这道题最重要的地方就是分割循环计算字符串 需要多看代码 并且记住!!
 * for i, v in enumerate(input[start:end]):
 * if str(v) in '+-*':
 * for left in self.diffWays(input, start, start+i, op):
 * for right in self.diffWays(input, start+i+1, end, op):
 * res.append(op[v](left, right))
 * 这种for的形式十分重要也十分方便
 */
public class DifferentWaystoAddParentheses {
    public static void main(String[] args) {
        String s = "2-1-1";
        System.out.println(new DifferentWaystoAddParentheses().diffWaysToComputea(s).toString());
    }

    public List<Integer> diffWaysToComputea(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> part1Ret = diffWaysToComputea(part1);
                List<Integer> part2Ret = diffWaysToComputea(part2);
                for (Integer p1 : part1Ret) {
                    for (Integer p2 : part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+':
                                c = p1 + p2;
                                break;
                            case '-':
                                c = p1 - p2;
                                break;
                            case '*':
                                c = p1 * p2;
                                break;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input)); //重要
        }
        return ret;
    }

}
