package amaoa;

import java.util.*;

public class baseball {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> line = new ArrayList<String>();
        line = Arrays.asList("5", "Z", "X", "1", "X", "+", "1", "1");
        System.out.println(helper(line));
    }

    private static int helper(List<String> input) {
        if (input == null || input.size() == 0) {
            return 0;
        }
        int sum = 0;
        Stack<Integer> helpStack = new Stack<Integer>();
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
            if (helpStack.size() < 2) {
                helpStack.push(0);
            }
        }
        return sum;
    }

}
