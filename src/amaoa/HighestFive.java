package amaoa;

import java.util.*;

/**
 * Created by shanshan on 2/24/19.
 * 选每个产品五个评分最高的求平均值。给一个list，每个element是<productId, productRating>，求每个product最高的5个评分的Average
 * 参考解法：TreeMap<productId, Heap(5)> 然后遍历
 */
public class HighestFive {
    public static List<List<Integer>> highestFive(List<Product> pList) {
        List<List<Integer>> res = new ArrayList<>();
        if (pList == null || pList.size() == 0)
            return res;
        TreeMap<Integer, PriorityQueue<Integer>> treeMap = new TreeMap<>();
        for (Product p : pList) {
            if (treeMap.containsKey(p.getProductId())) {
                treeMap.get(p.getProductId()).offer(p.getProductRating());
                if (treeMap.get(p.getProductId()).size() > 5)
                    treeMap.get(p.getProductId()).poll();
            } else {
                PriorityQueue<Integer> pq = new PriorityQueue<>(5, (a, b) -> (b - a));
                treeMap.put(p.getProductId(), pq);
            }
        }

        Iterator<Map.Entry<Integer, PriorityQueue<Integer>>> ite = treeMap.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<Integer, PriorityQueue<Integer>> ent = ite.next();
            List<Integer> a = new ArrayList<>();
            a.add(ent.getKey());
            PriorityQueue<Integer> p = ent.getValue();
            int sum = 0;
            while (!p.isEmpty()) {
                sum += p.poll();
            }
            a.add(Math.floorDiv(sum, 5));
            res.add(a);
        }

        return res;
    }

    class Product {
        int productId;
        int productRating;

        public Product(int productId, int productRating) {
            this.productId = productId;
            this.productRating = productRating;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getProductRating() {
            return productRating;
        }

        public void setProductRating(int productRating) {
            this.productRating = productRating;
        }
    }
}
