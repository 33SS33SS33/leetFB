package amaoa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 说的是小明要帮公司买水果，给了一个codeList， 里面装的是他买的水果，给了一个shoppingCart里面装的是target水果，
 * 目标是检查codelist里的水果顺序是否和shoppingCart里的顺序匹配。
 * 注意的是只有codelist中的所有链表的item之后加起来小于等于shoppingcart里item之和才可能返回1。
 * 另外在codelist中有可能出现item时 "anything"， 它可以和任意的水果匹配。
 * codelist:
 * [apple, apple],
 * [orange, banana, orange]
 * shoppingCart: [orange, apple, apple, orange, banana, orange].
 * return 1, 因为codelist里的顺序和shoppingcart里除了第一个orange之后的水果顺序匹配.
 * codelist:
 * [orange, banana, orange]，
 * [apple, apple]
 * shoppingCart: [orange, apple, apple, orange, banana, orange]
 * return 0, 因为codeList里的顺序和shoppingcart没法匹配。
 * 第一题直接用two pointer扫描就行了，因为是顺序匹配的，codeList里面匹配上的就不再管了，没有匹配到的就从这一行的起点重新开始。
 * shoppingCart那边先记录初始位置，然后和另一边同步移动，匹配不到的时候就从初始位置的后面一位重新开始，能匹配codeList的完整一行就更新起始位置。
 * 解题思路参考：
 * 题目好长，具体细节可能不一样。通常用two pointers可以解决，配合滑动窗口可以O(n)时间复杂度。
 * 坑：可能抽到的题目会有附加一个条件就是两个codelist集合直接可以任意匹配shoppingcart里面的东西，那么就需要另一个pointer。
 * 参考答案没有考虑这个附加条件。
 */
public class BuyFruits {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        List<List<String>> fruitlist = new ArrayList<>();
        List<String> shoppingCart = new ArrayList<>();
        fruitlist.add(Arrays.asList("a", "a"));
        fruitlist.add(Arrays.asList("o", "b", "o"));

        //    fruitlist.add(Arrays.asList("p", "o","g"));
        shoppingCart = Arrays.asList("o", "a", "a", "o", "b", "o", "p", "g");
        System.out.println(checkWinner(fruitlist, shoppingCart));
    }

    public static int checkWinner(List<List<String>> codeList, List<String> shoppingCart) {
        int x = 0;
        int y;
        int index = 0;
        while (x < codeList.size()) {
            y = 0;
            while (y < codeList.get(x).size() && index < shoppingCart.size()) {
                if (Objects.equals(codeList.get(x).get(y), shoppingCart.get(index)) || Objects.equals(codeList.get(x).get(y), "*")) {
                    y++;
                    index++;
                } else {
                    index++;
                }
            }
            if (index == shoppingCart.size() && (x != codeList.size() - 1 || y != codeList.get(x).size() - 1)) {
                return 0;
            }
            x++;
        }
        return 1;
    }

}
