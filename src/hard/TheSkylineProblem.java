package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
/**分别将每个线段的左边节点与右边节点存到新的vector height中，根据x坐标值排序，然后遍历求拐点。求拐点的时候用一个最大化heap来保存当前的楼顶高度，遇到左边节点，就在heap中插入高度信息，遇到右边节点就从heap中删除高度。分别用pre与cur来表示之前的高度与当前的高度，当cur != pre的时候说明出现了拐点。在从heap中删除元素时要注意，我使用priority_queue来实现，priority_queue并不提供删除的操作，所以又用了别外一个unordered_map来标记要删除的元素。在从heap中pop的时候先看有没有被标记过，如果标记过，就一直pop直到空或都找到没被标记过的值。别外在排序的时候要注意，如果两个节点的x坐标相同，我们就要考虑节点的其它属性来排序以避免出现冗余的答案。且体的规则就是如果都是左节点，就按y坐标从大到小排，如果都是右节点，按y坐标从小到大排，一个左节点一个右节点，就让左节点在前。
 第一个while就是先把左边坐标比当前坐标小的楼都加入堆里  第二个while是如果最高的楼已经要被当前位置pass了  就踢出去*/
public class TheSkylineProblem {
    /*static int li(int[] building){
        return building[0];
    }

    static int ri(int[] building){
        return building[1];
    }

    static int hi(int[] building){
        return building[2];
    }

    static class SortedBuilds {
        int[][] buildings;
        int p = 0;

        PriorityQueue<int[]> inserted = new PriorityQueue<>((a, b) -> li(a) - li(b));

        SortedBuilds(int[][] buildings) {
            this.buildings = buildings;
        }

        boolean hasNext(){
            return p < buildings.length || !inserted.isEmpty();
        }


        int[] next(){

            if(p < buildings.length && !inserted.isEmpty()){

                if(li(buildings[p]) < li(inserted.peek())){
                    return buildings[p++];
                }else{
                    return inserted.poll();
                }

            } else if(p < buildings.length ){
                return buildings[p++];
            } else { // !inserted.isEmpty())
                return inserted.poll();
            }

        }

        void insert(int[] building){
            inserted.add(building);
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> all = new ArrayList<>();
        if(buildings.length == 0) return all;

        SortedBuilds sortedBuilds = new SortedBuilds(buildings);

        int[] a = sortedBuilds.next();

        while (sortedBuilds.hasNext()){
            int[] b = sortedBuilds.next();

            if(ri(a) == li(b) && hi(a) == hi(b)){
                a = new int[]{li(a), ri(b), hi(a)};
                continue;
            }

            // a.r b.l
            if(ri(a) <= li(b)){
                all.add(new int[]{li(a), hi(a)});

                if(ri(a) < li(b)){
                    all.add(new int[]{ri(a), 0});
                }

                a = b;
                continue;
            }

            // a.l b.l
            if(li(a) == li(b)){

                // make a higher than b
                if(hi(a) < hi(b)){
                    sortedBuilds.insert(a);
                    a = b;
                    continue;
                }

                if(ri(a) < ri(b)){
                    sortedBuilds.insert(new int[]{ri(a), ri(b), hi(b)});
                }
                // else drop b (b inside a)
                continue;
            }

            //
            if(hi(a) < hi(b)){

                all.add(new int[]{li(a), hi(a)});

                if(ri(a) > ri(b)){
                    sortedBuilds.insert(new int[]{ri(b), ri(a), hi(a)});
                }

                a = b;
                continue;
            }

            // a.h >= b.h

            if(ri(a) < ri(b)){
                sortedBuilds.insert(new int[]{ri(a), ri(b), hi(b)});
            }
            // else drop b (b inside a)
        }

        all.add(new int[]{li(a), hi(a)});
        all.add(new int[]{ri(a), 0});

        return all;
    }*/
}
