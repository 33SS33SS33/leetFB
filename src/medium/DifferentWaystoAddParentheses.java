package medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
/**Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 Example 1
 Input: "2-1-1".

 ((2-1)-1) = 0
 (2-(1-1)) = 2
 Output: [0, 2]*/
/**很重要的题目 可以用动归 DP 备忘录 未实现
 主要思路就是首先将输入的input分割 变成数字或者加减乘除的形式
 Basic idea is using each operator to divide the whole string into three part:
 sub_str1, operator, sub_str2
 then recursively apply "compute" function on each sub_str.
 Take "2*3-4*5" for example:
 compute("2*3-4*5")
 -->compute("2") * compute("3-4*5")
 -->compute("2*3") - compute("4*5")
 -->compute("2*3-4") * compute("5")
 这道题最重要的地方就是分割循环计算字符串 需要多看代码 并且记住!!
 for i, v in enumerate(input[start:end]):
 if str(v) in '+-*':
 for left in self.diffWays(input, start, start+i, op):
 for right in self.diffWays(input, start+i+1, end, op):
 res.append(op[v](left, right))
 这种for的形式十分重要也十分方便*/
public class DifferentWaystoAddParentheses {
    public static void main(String[] args) {
        String s="2-1-1";
        System.out.println(new DifferentWaystoAddParentheses().diffWaysToCompute(s).toString());
    }

    public List<Integer> diffWaysToCompute(String input) {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("");
        List<Integer> nums = new ArrayList<Integer>();
        List<Character> ops = new ArrayList<Character>();
        while (scanner.hasNext()) {
            boolean num = false;
            int buf = 0;
            while (scanner.hasNextInt()) {
                num = true;
                buf = buf * 10 + scanner.nextInt();
            }
            if (num) {
                nums.add(buf);
                continue;
            }
            Character op = scanner.next().charAt(0);
            ops.add(op);
        }
        Integer[] _nums = nums.toArray(new Integer[0]);
        Character[] _ops = ops.toArray(new Character[0]);
        return diffWaysToCompute(_nums, 0, _nums.length, _ops);
    }

    List<Integer> diffWaysToCompute(Integer[] nums, int nst, int ned, Character[] ops) {
        List<Integer> rt = new ArrayList<Integer>();
        if (nst + 1 == ned) {
            rt.add(nums[nst]);
            return rt;
        }
        for (int i = nst; i < ned - 1; i++) {
            char op = ops[i];
            List<Integer> left = diffWaysToCompute(nums, nst, i + 1, ops);
            List<Integer> right = diffWaysToCompute(nums, i + 1, ned, ops);
            rt.addAll(merge(left, right, op));
        }
        return rt;
    }

    List<Integer> merge(List<Integer> left, List<Integer> right, char op) {
        if (left.isEmpty())
            return right;
        if (right.isEmpty())
            return left;
        List<Integer> rt = new ArrayList<Integer>();
        for (int l : left) {
            for (int r : right) {
                rt.add(calc(l, r, op));
            }
        }
        return rt;
    }

    int calc(int l, int r, char op) {
        switch (op) {
        case '+':
            return l + r;
        case '-':
            return l - r;
        case '*':
            return l * r;
        }
        // unreachable
        throw new RuntimeException();
    }

}
