package hard;

/**
 * Created by shanshan on 16/5/9.
 */

/**Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []*/

/**
 * 自己写的用eval的超时了
 * 难点在于乘法的处理  要用到变量存储之前的结果
 * 注意last的使用
 */
public class ExpressionAddOperators {
}
