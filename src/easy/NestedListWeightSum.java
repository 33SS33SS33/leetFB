package easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by shanshan on 16/6/17.
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1:
 * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 * Example 2:
 * Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
 */
public class NestedListWeightSum {

  /*  public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null){
            return 0;
        }

        int sum = 0;
        int level = 1;

        Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
        while(queue.size() > 0){
            int size = queue.size();

            for(int i = 0; i < size; i++){
                NestedInteger ni = queue.poll();

                if(ni.isInteger()){
                    sum += ni.getInteger() * level;
                }else{
                    queue.addAll(ni.getList());
                }
            }

            level++;
        }

        return sum;
    }*/

/*    public int depthSum(List<NestedInteger> nestedList) {
       return helper(nestedList, 1);
    }*/

    /*    private int helper(List<NestedInteger> list, int depth)
        {
            int ret = 0;
            for (NestedInteger e: list)
            {
                ret += e.isInteger()? e.getInteger() * depth: helper(e.getList(), depth + 1);
            }
            return ret;
        }*/
    public static class NestedInteger {

    }
}
