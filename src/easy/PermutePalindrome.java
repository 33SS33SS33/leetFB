package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class PermutePalindrome {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> m = new HashMap<Character, Integer>();

        for(char c : s.toCharArray()){
            Integer i = m.get(c);

            if(i == null){
                m.put(c, 1);
            } else {
                m.put(c, i + 1);
            }
        }

        int single = 0;

        for(int v : m.values()){
            if(v % 2 == 1){
                single++;
            }

            if(single > 1){
                return false;
            }
        }

        return true;
    }
}
