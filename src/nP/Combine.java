package nP;

import java.util.ArrayList;

/**
 * Created by GAOSHANSHAN835 on 2016/1/5.
 */
public class Combine {
    public static void main(String[] args) {
        int m=6;
        int n=5;
        Combine a=new Combine();
        System.out.println(a.combine(m,n).toString());
    }
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(n<=0 || n<k)
            return res;
        helper(n,k,1,new ArrayList<Integer>(), res);
        return res;
    }
    private void helper(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res) {
        if(item.size()==k) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=start;i<=n;i++) {
            item.add(i);
            helper(n,k,i+1,item,res);
            item.remove(item.size()-1);
        }
    }
}
