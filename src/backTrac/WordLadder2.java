package backTrac;

import java.util.*;

/**
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) from start to end, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 *
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * Tags: Array, Backtracking, BFS, String
 */
class WordLadder2 {
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] arr = { "hot", "dot", "dog", "lot", "log" };
        Set<String> dict = new HashSet<String>(Arrays.asList(arr));
        System.out.println(new WordLadder2().findLadders(start, end, dict).toString());
        System.out.println("------------------");
        System.out.println(new WordLadder2().findLaddersB(start, end, dict).toString());
    }

    /**
     * BFS then DFS
     */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> dist = new HashMap<String, Integer>();

        bfs(map, dist, start, end, dict);
        dfs(res, new LinkedList<String>(), end, start, dist, map);
        return res;
    }

    /**
     * Create a queue, add start to it and put start in dist map
     * Initialize map with lists
     */
    void bfs(Map<String, List<String>> map, Map<String, Integer> dist, String start, String end,
            Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        dict.add(start); // make sure start and end in dictionary
        dict.add(end);
        dist.put(start, 0);
        for (String s : dict)
            map.put(s, new ArrayList<String>());

        while (!q.isEmpty()) {
            String word = q.poll();
            List<String> expansion = expand(word, dict); // generate all words
            for (String next : expansion) {
                map.get(next).add(word);
                if (!dist.containsKey(next)) { // not in dist map yet
                    dist.put(next, dist.get(word) + 1);
                    q.offer(next);
                }
            }
        }
    }

    /**
     * Generate a list of words the word
     * Skip if it's the same character
     * If word is in dictionary, add to expansion list
     */
    List<String> expand(String word, Set<String> dict) {
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                char[] chs = word.toCharArray();
                if (ch != chs[i]) {
                    chs[i] = ch;
                    String next = new String(chs);
                    if (dict.contains(next))
                        res.add(next);
                }
            }
        }
        return res;
    }

    /**
     * Add current word to first position
     * Add path to result if word is start
     */
    void dfs(List<List<String>> res, List<String> path, String word, String start,
            Map<String, Integer> dist, Map<String, List<String>> map) {
        if (word.equals(start)) {
            path.add(0, word);
            res.add(new ArrayList<String>(path));
            path.remove(0);
            return; // note to return
        }
        for (String next : map.get(word)) {
            if (dist.containsKey(next) && dist.get(word) == dist.get(next) + 1) { // backward, so word = next + 1
                path.add(0, word); // add current word
                dfs(res, path, next, start, dist, map); // dfs next word
                path.remove(0);
            }
        }
    }

    /**
     * creek
     */
    public List<List<String>> findLaddersB(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(start, 1, null));

        dict.add(end);
        int minStep = 0;

        HashSet<String> visited = new HashSet<String>();
        HashSet<String> unvisited = new HashSet<String>();
        unvisited.addAll(dict);
        int preNumSteps = 0;
        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.word;
            int currNumSteps = top.numSteps;
            if (word.equals(end)) {
                if (minStep == 0) {
                    minStep = top.numSteps;
                }
                if (top.numSteps == minStep && minStep != 0) {
                    //nothing
                    ArrayList<String> t = new ArrayList<String>();
                    t.add(top.word);
                    while (top.pre != null) {
                        t.add(0, top.pre.word);
                        top = top.pre;
                    }
                    result.add(t);
                    continue;
                }
            }
            if (preNumSteps < currNumSteps) {
                unvisited.removeAll(visited);
            }
            preNumSteps = currNumSteps;
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }
                    String newWord = new String(arr);
                    if (unvisited.contains(newWord)) {
                        queue.add(new WordNode(newWord, top.numSteps + 1, top));
                        visited.add(newWord);
                    }
                    arr[i] = temp;
                }
            }
        }
        return result;
    }

    /*这道题是LeetCode中AC率最低的题目，确实是比较难。一方面是因为对时间有比较严格的要求（容易超时），
    另一方面是它有很多细节需要实现。思路上和Word Ladder是比较类似的，但是因为是要求出所有路径，
    仅仅保存路径长度是不够的，而且这里还有更多的问题，那就是为了得到所有路径，不是每个结点访问一次就可以标记为visited了，
    因为有些访问过的结点也会是别的路径上的结点，所以访问的集合要进行回溯（也就是标记回未访问）。
    所以时间上不再是一次广度优先搜索的复杂度了，取决于结果路径的数量。同样空间上也是相当高的复杂度，
    因为我们要保存过程中满足的中间路径到某个数据结构中，以便最后可以获取路径，这里我们维护一个HashMap，把一个结点前驱结点都进行保存。
    在LeetCode中用Java实现上述算法非常容易超时。为了提高算法效率，需要注意一下两点：
    1）在替换String的某一位的字符时，先转换成char数组再操作；
    2）如果按照正常的方法从start找end，然后根据这个来构造路径，代价会比较高，因为保存前驱结点容易，而保存后驱结点则比较困难。
    所以我们在广度优先搜索时反过来先从end找start，最后再根据生成的前驱结点映射从start往end构造路径，这样算法效率会有明显提高。*/

    /**这道题目实现中有很多细节和技巧，个人觉得如果在面试中遇到很难做到完整，而且考核的算法思想也不是很精妙，更多的是繁琐的操作。在面试中时间上花费太大，也不是很合适，大家主要还是了解一下思路哈。
*/
    public ArrayList<ArrayList<String>> findLaddersC(String start, String end, HashSet<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        HashSet<String> unvisitedSet = new HashSet<String>();
        unvisitedSet.addAll(dict);
        unvisitedSet.add(start);
        unvisitedSet.remove(end);
        Map<String, List<String>> nextMap = new HashMap<String, List<String>>();
        for (String e : unvisitedSet) {
            nextMap.put(e, new ArrayList<String>());
        }
        LinkedList<StringWithLevel> queue = new LinkedList<StringWithLevel>();
        queue.add(new StringWithLevel(end, 0));
        boolean found = false;
        int finalLevel = Integer.MAX_VALUE;
        int curLevel = 0;
        int preLevel = 0;
        HashSet<String> visitedCurLevel = new HashSet<String>();
        while (!queue.isEmpty()) {
            StringWithLevel cur = queue.poll();
            String curStr = cur.str;
            curLevel = cur.level;
            if (found && curLevel > finalLevel) {
                break;
            }
            if (curLevel > preLevel) {
                unvisitedSet.removeAll(visitedCurLevel);
            }
            preLevel = curLevel;
            char[] curStrCharArray = curStr.toCharArray();
            for (int i = 0; i < curStr.length(); ++i) {
                char originalChar = curStrCharArray[i];
                boolean foundCurCycle = false;
                for (char c = 'a'; c <= 'z'; ++c) {
                    curStrCharArray[i] = c;
                    String newStr = new String(curStrCharArray);
                    if (c != originalChar && unvisitedSet.contains(newStr)) {
                        nextMap.get(newStr).add(curStr);
                        if (newStr.equals(start)) {
                            found = true;
                            finalLevel = curLevel;
                            foundCurCycle = true;
                            break;
                        }
                        if (visitedCurLevel.add(newStr)) {
                            queue.add(new StringWithLevel(newStr, curLevel + 1));
                        }
                    }
                }
                if (foundCurCycle) {
                    break;
                }
                curStrCharArray[i] = originalChar;
            }
        }
        if (found) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(start);
            getPaths(start, end, list, finalLevel + 1, nextMap, res);
        }
        return res;
    }
    private void getPaths(String cur, String end, ArrayList<String> list, int level,
            Map<String, List<String>> nextMap, ArrayList<ArrayList<String>> res) {
        if (cur.equals(end)) {
            res.add(new ArrayList<String>(list));
        } else if (level > 0) {
            List<String> parentsSet = nextMap.get(cur);
            for (String parent : parentsSet) {
                list.add(parent);
                getPaths(parent, end, list, level - 1, nextMap, res);
                list.remove(list.size() - 1);
            }
        }
    }
    class StringWithLevel {
        String str;
        int    level;

        public StringWithLevel(String str, int level) {
            this.str = str;
            this.level = level;
        }
    }

    class WordNode {
        String   word;
        int      numSteps;
        WordNode pre;

        public WordNode(String word, int numSteps, WordNode pre) {
            this.word = word;
            this.numSteps = numSteps;
            this.pre = pre;
        }
    }
}
