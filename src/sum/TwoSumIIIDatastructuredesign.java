package sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */

/**
 * 还是哈希表就行
 */
public class TwoSumIIIDatastructuredesign {
    class TwoSum {
        Map<Integer, Integer> nums = new HashMap<Integer, Integer>();

        public void add(int number) {
            Integer c = nums.get(number);
            if (c == null)
                c = 0;
            c++;
            nums.put(number, c);
        }

        public boolean find(int value) {
            for (Integer n : nums.keySet()) {
                Integer c = nums.get(value - n);
                if (c == null)
                    continue;
                if (value - n == n) {
                    if (c > 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
            return false;
        }
    }

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
