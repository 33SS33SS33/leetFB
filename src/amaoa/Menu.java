package amaoa;

import java.util.*;

/**
 * 大概意思是给人推荐菜谱，input是两个二维String 数组，第一个用来存菜名和对应的菜系，第二个用来存人名和这个人喜欢的菜系，要去返回一个二维String数组，存的值是人名对应可能喜欢的菜名(如果人名后面对应的是“*”，那么就表示所有菜系都要)。
 * input: String[][] Menu, String[][] personPreferences.
 * output: String[][] recommendation
 * example 1:
 * input: {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}}, {{"Peter", "Italian"}, {"Adam", "American"}}
 * Expected output: {{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Adam", "Burger"}}
 * example 2:
 * input: {{"Pizza", "Italian"}, {"Pasta", "Italian"}, {"Burger", "American"}}, {{"Peter", "*"}}.
 * Expected output: {{"Peter", "Pizza"}, {"Peter", "Pasta"}, {"Peter", "Burger"}}
 * 解题思路参考：
 * 几乎没坑，就是考察基本数据结构HashMap。把菜谱存入HashMap后，对person进行循环匹配，注意*号的处理。
 */
public class Menu {
    public static void main(String[] args) {
        List<List<String>> list1 = new ArrayList<>();
        List<List<String>> list2 = new ArrayList<>();
        list1.add(Arrays.asList("Zhang", "Chinese"));
        list1.add(Arrays.asList("Li", "American"));
        list1.add(Arrays.asList("Wang", "Japanese"));
        list1.add(Arrays.asList("Ming", "*"));
        list2.add(Arrays.asList("Chinese", "Pork"));
        list2.add(Arrays.asList("Chinese", "fish"));
        list2.add(Arrays.asList("American", "beef"));
        System.out.println(menu(list1, list2));
        String[][] menu = {{"fish", "Chinese"}, {"Pork", "Chinese"}, {"beef", "American"}};
        String[][] person = {{"Zhang", "Chinese"}, {"Li", "American"}, {"Wang", "Japanese"}, {"Ming", "*"}};
        String[][] res = recommend(menu, person);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i][0]);
            System.out.print(res[i][1]);
            System.out.println();
        }
    }

    //两种解法输入不一样
    public static List<List<String>> menu(List<List<String>> person, List<List<String>> menu) {
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> row : menu) {
            if (!map.containsKey(row.get(0))) {
                map.put(row.get(0), new HashSet<>(Collections.singletonList(row.get(1))));
            } else {
                map.get(row.get(0)).add(row.get(1));
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < person.size(); i++) {
            if (map.containsKey(person.get(i).get(1))) {
                for (String dish : map.get(person.get(i).get(1))) {
                    result.add(Arrays.asList(person.get(i).get(0), dish));
                }
            }
            if (Objects.equals(person.get(i).get(1), "*")) {
                for (Set<String> dishes : map.values()) {
                    for (String dish : dishes) {
                        result.add(Arrays.asList(person.get(i).get(0), dish));
                    }
                }
            }
        }
        return result;
    }

    public static String[][] recommend(String[][] menu, String[][] person) {
        Map<String, Set<String>> menuHash = new HashMap<>();
        for (int i = 0; i < menu.length; i++) {
            if (!menuHash.containsKey(menu[i][1])) {
                Set<String> set = new HashSet<>();
                set.add(menu[i][0]);
                menuHash.put(menu[i][1], set);
            } else {
                menuHash.get(menu[i][1]).add(menu[i][0]);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < person.length; i++) {
            if (person[i][1].equals("*")) {
                for (Set<String> set : menuHash.values()) {
                    for (String each : set) {
                        List<String> elem = new ArrayList<>();
                        elem.add(person[i][0]);
                        elem.add(each);
                        res.add(elem);
                    }
                }
                continue;
            }
            if (!menuHash.containsKey(person[i][1]))
                continue;
            Set<String> set = menuHash.get(person[i][1]);
            for (String each : set) {
                List<String> elem = new ArrayList<>();
                elem.add(person[i][0]);
                elem.add(each);
                res.add(elem);
            }
        }

        int n = res.size();
        String[][] ret = new String[n][2];
        for (int i = 0; i < n; i++) {
            ret[i][0] = res.get(i).get(0);
            ret[i][1] = res.get(i).get(1);
        }

        return ret;
    }
}
