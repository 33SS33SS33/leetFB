package sum;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/1/12.
 */
public class FourSum {
    public static void main(String[] args) {
        int[] num= {2,5,3,1,6};
        System.out.println(new FourSum().fourSum(num,8).toString());

    }
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num==null||num.length==0)
            return res;
        Arrays.sort(num);
        for(int i=num.length-1;i>2;i--) {
            if(i==num.length-1 || num[i]!=num[i+1]) {
                ArrayList<ArrayList<Integer>> curRes = threeSum(num,i-1,target-num[i]);
                for(int j=0;j<curRes.size();j++) {
                    curRes.get(j).add(num[i]);
                }
                res.addAll(curRes);
            }
        }
        return res;
    }
    private ArrayList<ArrayList<Integer>> threeSum(int[] num, int end, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for(int i=end;i>1;i--) {
            if(i==end || num[i]!=num[i+1]) {
                ArrayList<ArrayList<Integer>> curRes = twoSum(num,i-1,target-num[i]);
                for(int j=0;j<curRes.size();j++) {
                    curRes.get(j).add(num[i]);
                }
                res.addAll(curRes);
            }
        }
        return res;
    }
    private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int l=0;
        int r=end;
        while(l<r){
            if(num[l]+num[r]==target) {
                ArrayList<Integer> item = new ArrayList<Integer>();
                item.add(num[l]);
                item.add(num[r]);
                res.add(item);
                l++;
                r--;
                while(l<r&&num[l]==num[l-1])
                    l++;
                while(l<r&&num[r]==num[r+1])
                    r--;
            }
            else if(num[l]+num[r]>target){
                r--;
            } else {
                l++;
            }
        }
        return res;
    }
   /* private ArrayList<ArrayList<Integer>> twoSum(ArrayList<Pair> pairs, int target){
        HashSet<Tuple> hashSet = new HashSet<Tuple>();
        int l = 0;
        int r = pairs.size()-1;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        while(l<r){
            if(pairs.get(l).getSum()+pairs.get(r).getSum()==target)
            {
                int lEnd = l;
                int rEnd = r;
                while(lEnd<rEnd && pairs.get(lEnd).getSum()==pairs.get(lEnd+1).getSum())
                {
                    lEnd++;
                }
                while(lEnd<rEnd && pairs.get(rEnd).getSum()==pairs.get(rEnd-1).getSum())
                {
                    rEnd--;
                }
                for(int i=l;i<=lEnd;i++)
                {
                    for(int j=r;j>=rEnd;j--)
                    {
                        if(check(pairs.get(i),pairs.get(j)))
                        {
                            ArrayList<Integer> item = new ArrayList<Integer>();
                            item.add(pairs.get(i).nodes[0].value);
                            item.add(pairs.get(i).nodes[1].value);
                            item.add(pairs.get(j).nodes[0].value);
                            item.add(pairs.get(j).nodes[1].value);
                            //Collections.sort(item);
                            Tuple tuple = new Tuple(item);
                            if(!hashSet.contains(tuple))
                            {
                                hashSet.add(tuple);
                                res.add(tuple.num);
                            }
                        }
                    }
                }
                l = lEnd+1;
                r = rEnd-1;
            }
            else if(pairs.get(l).getSum()+pairs.get(r).getSum()>target)
            {
                r--;
            }
            else{
                l++;
            }
        }
        return res;
    }
    private boolean check(Pair p1, Pair p2)
    {
        if(p1.nodes[0].index == p2.nodes[0].index || p1.nodes[0].index == p2.nodes[1].index)
            return false;
        if(p1.nodes[1].index == p2.nodes[0].index || p1.nodes[1].index == p2.nodes[1].index)
            return false;
        return true;
    }*/

    static class Pair {
        int a;
        int ai;
        int b;
        int bi;

        public Pair(int a, int ai, int b, int bi) {
            this.a = a;
            this.ai = ai;
            this.b = b;
            this.bi = bi;
        }
        boolean same(Pair p) {
            return p != null && p.a == a && p.b == b;
        }
    }
}
