package amaoa;

import java.util.*;

/**
 * Given a string array representing a throw ball blocks, each string is either a number,
 * +, Z, X. Calculate total. If number, just add to total. If +, add last 2 scores to total.
 * If Z, remove last score from total. If X, double last score and add to toal.
 * Use 0 for any missing last score. 有些 corner cases 要考虑。
 * 打棒球得分，给了一个String[] input，求最终score
 * 如果是 integer， 就加给score（有负值）
 * 如果是“x”, 将上一个值double ，加给score； 若没有上一个值，上一个值按0 计算
 * 如果是“z”, 上一个成绩作废， score 剪掉上一值
 * 如果是“+”，将上两个值相加，然后加给score
 * 解题思路参考：
 * 考察stack的使用和边界条件的处理。坑：+号的处理，处理Z和X要先看是否是空集。
 */
public class Baseball {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        List<String> line = Arrays.asList("5", "Z", "X", "1", "X", "+", "1", "1");
        String[] score={"5", "Z", "X", "1", "X", "+", "1", "1"};
        System.out.println(helper(line));
    }

    private static int helper(List<String> input) {
        if (input == null || input.size() == 0) {
            return 0;
        }
        int sum = 0;
        Stack<Integer> helpStack = new Stack<>();
        helpStack.push(0);
        helpStack.push(0);
        for (int i = 0; i < input.size(); i++) {
            int current = 0;
            int size = helpStack.size();
            String buff = input.get(i);
            if (buff == "Z") {
                current = helpStack.pop();
                sum -= current;
            } else if (buff == "+") {
                current = helpStack.get(size - 1) + helpStack.get(size - 2);
                sum += current;
                helpStack.push(current);
            } else if (buff == "X") {
                current = helpStack.get(size - 1) * 2;
                sum += current;
                helpStack.push(current);
            } else {
                current = Integer.parseInt(buff);
                helpStack.push(current);
                sum += current;
            }
            if (helpStack.size() < 2) {//important!
                helpStack.push(0);
            }
        }
        return sum;
    }

}
