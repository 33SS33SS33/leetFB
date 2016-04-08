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
