package nP;

import java.util.*;

/**
 * Created by GAOSHANSHAN835 on 2016/1/5.
 */
public class WordBreak2 {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if(s==null || s.length()==0)
            return res;
        helper(s,dict,0,"",res);
        return res;
    }
    private void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res) {
        if(start>=s.length()) {
            res.add(item);
            return;
        }
        StringBuilder str = new StringBuilder();
        for(int i=start;i<s.length();i++) {
            str.append(s.charAt(i));
            if(dict.contains(str.toString())) {
                String newItem = item.length()>0?(item+" "+str.toString()):str.toString();
                helper(s,dict,i+1,newItem,res);
            }
        }
    }

    class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start; this.end = end;
        }
        public Interval(Interval i) {
            start = i.start;
            end = i.end;
        }
    }
    ArrayList<ArrayList<Interval>> deepCopy(ArrayList<ArrayList<Interval>> paths) {
        if (paths==null) return null;
        ArrayList<ArrayList<Interval>> res = new ArrayList<ArrayList<Interval>>(paths.size());
        for (int i=0; i<paths.size(); i++) {
            ArrayList<Interval> path = paths.get(i);
            ArrayList<Interval> copy = new ArrayList<Interval>(path.size());
            for (int j=0; j<path.size(); j++) {
                copy.add(new Interval(path.get(j)));
            }
            res.add(copy);
        }
        return res;
    }

    /*DP 用动态规划的代码复杂度要远远高于brute force的解法，而且本质来说并没有很大的提高*/
    public ArrayList<String> wordBreak2(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if (s==null || s.length()==0) return res;
        Map<Integer, ArrayList<ArrayList<Interval>>> dp = new HashMap<Integer, ArrayList<ArrayList<Interval>>>();
        dp.put(0, new ArrayList<ArrayList<Interval>>());
        dp.get(0).add(new ArrayList<Interval>());
        for (int i=1; i<=s.length(); i++) {
            for (int j=0; j<i; j++) {
                String cur = s.substring(j, i);
                ArrayList<ArrayList<Interval>> paths = null;
                if (dp.containsKey(j) && dict.contains(cur)) {
                    paths = deepCopy(dp.get(j));
                    Interval interval = new Interval(j, i);
                    for (ArrayList<Interval> path:paths) {
                        path.add(interval);
                    }
                }
                if (paths != null) {
                    if (!dp.containsKey(i)) {
                        dp.put(i, paths);
                    }
                    else {
                        dp.get(i).addAll(paths);
                    }
                }
            }
        }
        if (!dp.containsKey(s.length())) {
            return res;
        }
        ArrayList<ArrayList<Interval>> paths = dp.get(s.length());
        for (int j=0; j<paths.size(); j++) {
            ArrayList<Interval> path = paths.get(j);
            StringBuilder str = new StringBuilder();
            for (int i=0; i<path.size(); i++) {
                if (i!=0) {str.append(" ");}
                int start = path.get(i).start;
                int end = path.get(i).end;
                str.append(s.substring(start, end));
            }
            res.add(str.toString());
        }
        return res;
    }

    /**creek-----*/
    public static List<String> wordBreak3(String s, Set<String> dict) {
        //create an array of ArrayList<String>
        List<String> dp[] = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<String>();

        for(int i=0; i<s.length(); i++){
            if( dp[i] == null )
                continue;

            for(String word:dict){
                int len = word.length();
                int end = i+len;
                if(end > s.length())
                    continue;

                if(s.substring(i,end).equals(word)){
                    if(dp[end] == null){
                        dp[end] = new ArrayList<String>();
                    }
                    dp[end].add(word);
                }
            }
        }

        List<String> result = new LinkedList<String>();
        if(dp[s.length()] == null)
            return result;

        ArrayList<String> temp = new ArrayList<String>();
        dfs(dp, s.length(), result, temp);

        return result;
    }

    public static void dfs(List<String> dp[],int end,List<String> result, ArrayList<String> tmp){
        if(end <= 0){
            String path = tmp.get(tmp.size()-1);
            for(int i=tmp.size()-2; i>=0; i--){
                path += " " + tmp.get(i) ;
            }

            result.add(path);
            return;
        }

        for(String str : dp[end]){
            tmp.add(str);
            dfs(dp, end-str.length(), result, tmp);
            tmp.remove(tmp.size()-1);
        }
    }
}
