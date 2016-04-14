package nP;

import java.util.ArrayList;

/**
 * Created by GAOSHANSHAN835 on 2016/1/5.
 */
public class Permute {
    public static void main(String[] args) {
        int[] num = { 1, 3, 5, 6 };
        Permute a = new Permute();
        System.out.println(a.permute(num).toString());
        System.out.println(a.permuteB(num).toString());
    }

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0)
            return res;
        helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(int[] num, boolean[] used, ArrayList<Integer> item,
            ArrayList<ArrayList<Integer>> res) {
        if (item.size() == num.length) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (!used[i]) {
                used[i] = true;
                item.add(num[i]);
                helper(num, used, item, res);
                item.remove(item.size() - 1);
                used[i] = false;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> permuteB(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0)
            return res;
        ArrayList<Integer> first = new ArrayList<Integer>();
        first.add(num[0]);
        res.add(first);
        for (int i = 1; i < num.length; i++) {
            ArrayList<ArrayList<Integer>> newRes = new ArrayList<ArrayList<Integer>>();
            for (int j = 0; j < res.size(); j++) {
                ArrayList<Integer> cur = res.get(j);
                for (int k = 0; k < cur.size() + 1; k++) {
                    ArrayList<Integer> item = new ArrayList<Integer>(cur);
                    item.add(k, num[i]);
                    newRes.add(item);
                }
            }
            res = newRes;
        }
        return res;
    }
}
