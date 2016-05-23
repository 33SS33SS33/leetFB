package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * given "abcbbbbcccbdddadacb",
 * the longest substring that contains 2 unique character is "bcbbbbcccb".
 */

/**
 * 答题思路就是用滑动窗口 然后用哈希表来记录经过的元素的坐标  当窗口碰到了第三个不同的元素  就先计算一下当前的长度
 * 然后就开始移动start 每次移动start就把这个start对应的位置从哈希表里删除
 */
class LongestSubstringWithTwo {
    public static void main(String[] args) {
        System.out.println(LongestSubstringWithatMostTwoDistinctCharacters("abcbbbbcccbdddadacb"));
        System.out.println(LongestSubstringWithatMostTwoDistinctCharacters("bbbbb"));
        System.out.println(LongestSubstringWithatMostTwoDistinctCharacters("fdjskajfhh"));
    }

    public static int LongestSubstringWithatMostTwoDistinctCharacters(String s) {
        char[] S = s.toCharArray();
        if (S.length == 0)
            return 0;
        int max = 1;
        Context context = new Context();
        for (int i = 0; i < S.length; i++) {
            Character c = S[i];
            context.add(c);
            context.end = i;
            // need trim to 2
            for (int j = context.start; context.counts.size() > 2; j++) {
                Character _c = S[j];
                context.remove(_c);
                context.start = j + 1;
            }
            max = Math.max(context.len(), max);
        }
        return max;
    }

    static class Context {
        int                     start  = 0;
        int                     end    = 0;
        Map<Character, Integer> counts = new HashMap<Character, Integer>();

        int len() {
            return end - start + 1;
        }

        void add(Character c) {
            Integer i = counts.get(c);
            if (i == null) {
                i = 0;
            }
            i++;
            counts.put(c, i);
        }

        void remove(Character c) {
            Integer i = counts.remove(c);
            if (i == null) {
                return;
            }
            i--;
            if (i > 0) {
                counts.put(c, i);
            }
        }
    }

}
