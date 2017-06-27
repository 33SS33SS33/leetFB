package aFB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanshan on 16/5/9.
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 * 自己写的用eval的超时了
 * 难点在于乘法的处理  要用到变量存储之前的结果
 * 注意last的使用  回溯法
 * This problem has a lot of edge cases to be considered:
 * overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
 * 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
 * a little trick is that we should save the value that is to be multiplied in the next recursion.
 */
public class ExpressionAddOperators {
    public static void main(String[] args) {
        String num = "123";
        int target = 6;
        System.out.println(new ExpressionAddOperators().addOperators(num, target));
    }

    //最好的
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if (num == null || num.length() == 0)
            return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }

    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
            if (target == eval)
                rst.add(path);
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0')
                break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            } else {
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur, cur);
                helper(rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur);//????
            }
        }
    }

}
