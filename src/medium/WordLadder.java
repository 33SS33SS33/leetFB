package medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/8.
 */
/**
 * Given:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]
 As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.*/
public class WordLadder {

    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
        String[] arr = {"hot","dot","dog","lot","log"};
        HashSet<String> dict = new HashSet<String>(Arrays.asList(arr));
        System.out.println(new WordLadder().ladderLength(start, end, dict));
        System.out.println(new WordLadder().ladderLengthB(start, end, dict));
    }

    public int ladderLength(String start, String end, HashSet<String> dict) {
        if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())
            return 0;
        LinkedList<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        int level= 1;
        int lastNum = 1;
        int curNum = 0;
        queue.offer(start);
        visited.add(start);
        while(!queue.isEmpty()) {
            String cur = queue.poll();
            lastNum--;
            for(int i=0;i<cur.length();i++) {
                char[] charCur = cur.toCharArray();
                for(char c='a';c<='z';c++) {
                    charCur[i] = c;
                    String temp = new String(charCur);
                    if(temp.equals(end))
                        return level+1;
                    if(dict.contains(temp) && !visited.contains(temp)){
                        curNum++;
                        queue.offer(temp);
                        visited.add(temp);
                    }
                }
            }
            if(lastNum==0) {
                lastNum = curNum;
                curNum = 0;
                level++;
            }
        }
        return 0;
    }


    class WordNode{
        String word;
        int numSteps;

        public WordNode(String word, int numSteps){
            this.word = word;
            this.numSteps = numSteps;
        }
    }
    /**creek*/
    public int ladderLengthB(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));

        wordDict.add(endWord);

        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
            if(word.equals(endWord)){
                return top.numSteps;
            }

            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<='z'; c++){
                    char temp = arr[i];
                    if(arr[i]!=c){
                        arr[i]=c;
                    }
                    String newWord = new String(arr);
                    if(wordDict.contains(newWord)){
                        queue.add(new WordNode(newWord, top.numSteps+1));
                        wordDict.remove(newWord);
                    }
                    arr[i]=temp;
                }
            }
        }
        return 0;
    }
}
