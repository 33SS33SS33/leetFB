package sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.

 * 还是哈希表就行
 */
public class TwoSumIIIDatastructuredesign {
    //
/*    public class TwoSum {
        private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        public void add(int number) {
            map.put(number, map.containsKey(number) ? map.get(number) + 1 : 1);
        }
        public boolean find(int value) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int i = entry.getKey();
                int j = value - i;
                if ((i == j && entry.getValue() > 1) || (i != j && map.containsKey(j)
                return true; }
        }
        return false;
    }
}
}*/

    /**
     * ---creek----!
     */
    class TwoSumB {
        private HashMap<Integer, Integer> elements = new HashMap<Integer, Integer>();

        public void add(int number) {
            if (elements.containsKey(number)) {
                elements.put(number, elements.get(number) + 1);
            } else {
                elements.put(number, 1);
            }
        }

        public boolean find(int value) {
            for (Integer i : elements.keySet()) {
                int target = value - i;
                if (elements.containsKey(target)) {
                    if (i == target && elements.get(target) < 2) {
                        continue;
                    }
                    return true;
                }
            }
            return false;
        }
    }
}
