package backTrac;

import java.util.*;

/**
 * Given two words (start and end), and a dictionary, find all shortest
 * transformation sequence(s) from start to end, such that:
 *
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 *
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * Return
 *   [
 *     ["hit","hot","dot","dog","cog"],
 *     ["hit","hot","lot","log","cog"]
 *   ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 *
 * Tags: Array, Backtracking, BFS, String
 */
class WordLadder2 {
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] arr = {"hot","dot","dog","lot","log"};
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
    void bfs(Map<String, List<String>> map, Map<String, Integer> dist,
                String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        dict.add(start); // make sure start and end in dictionary
        dict.add(end);
        dist.put(start, 0);
        for (String s : dict) map.put(s, new ArrayList<String>());

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
                    if (dict.contains(next)) res.add(next);
                }
            }
        }
        return res;
    }
    
    /**
     * Add current word to first position
     * Add path to result if word is start
     */
    void dfs(List<List<String>> res, List<String> path, String word, String start, Map<String, Integer> dist, Map<String, List<String>> map) {
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

    class WordNode{
        String word;
        int numSteps;
        WordNode pre;

        public WordNode(String word, int numSteps, WordNode pre){
            this.word = word;
            this.numSteps = numSteps;
            this.pre = pre;
        }
    }
}
