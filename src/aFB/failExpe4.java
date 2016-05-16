package aFB;

/**
 * Created by shanshan on 16/5/15.
 */
public class failExpe4 {
    /**Facebook Onsite, 已跪，人生第一跪，还挺伤心的。。。发面经赞赞人品move on.
     之前on campus 的面经http://www.1point3acres.com/bbs/ ... p;page=1#pid1494836
     总共三轮，
     1. behaviour question， 然后coding， strstr 还有.
     直接上代码，然后讨论了两句结束。
     2. 3 sum 还有 Letter Combinations of a Phone Number ，
     写完被要求对代码进行精简。
     3. Edit distance，询问如何判断两个string 的edit distance 是否等于1，当时第三轮脑子有点麻了。。。居然说先用dp算出distance在判断，最后在面试官循循诱导下，想到其实只要扫一遍就行了。。。呵呵，蠢起来我       自己都怕。说完算法，还剩下不到5分钟，叫我赶紧写代码出来，写得太赶被指出了有bug。。。估计就死在这上面了。。。诶，心塞。。。
     估计其他公司面试都会有这么简单的题了。。。曾经有个如此好的机会摆在我面前，我没有珍惜。。。。
     Anyway，就酱了，求加分，求给米，求安慰。。。*/
    ///-----------
    /**
     上周五FB Seattle onsite
     1, 国人，在微软呆了三年，在FB seattle呆了三年;
     聊天，各种聊。
     写了个简单的code, 把阿拉泊数字转换成英文数字; 如16,123  -> Sixteen thousand, one hundry and twenty three
     2, 一个在微软呆了八年的东欧人, 在FB seattle 呆了两年;
     Log<fun_name,timestap,isStart>   给一串fun_name调用的log,返回一个Map,key是fun_name, value是fun实际调用时间。
     如: f1, t1, true;  f2, t2, true; f2, t3, false; f1, t4, false;    返回 f2, t3-t2;  f1, t4-t1-(t3-t2)
     3, 一个在Amazon呆了1年多的美国小哥, 在FB seattle 呆了一年;
     Decode ways;
     返回Valid parentheses;. Wa*/
    ///-----------
    /**phone:
     3sum  上去就用sort + two pointer被烙印怀疑见过原题，要求不sort解决，用hashset解决。要求在线编译通过
     onsite:
     第一轮： 小白，上来瞎聊，问我当manager会怎么样，不明白他想问什么，估计他也不太满意。最后5分钟出了一道算法，find the shallow depth of a binary tree. the shortest distance between root and a leaf node，level traverse解决。
     2.1 remove minimum number of left and right brackets and return the string with valid bracket pairs. given (a))()), should return ()(), given )()()), should return either ()() or (()). 解法很多，stack, two pointer等等都很好解，当时进了死胡同，一定要用dp解min结果没做出来，烙印也一直盘问不给任何提示。
     3.1 Design tinyurl，胡扯一大通，小白估计也明白我就是纸上谈兵，没干过这类活。
     4.1 design scheduler. given string “AABC” representing different task, and int k is the minimum interval for the same task, calculate the minimum steps to execute the sequence.
     [size=14.6667px]AABC 2 should take A[][]ABC total of 6 steps; ABAB 2 should take AB[]AB total of 5 steps, AAAA should take 10 steps.
     [size=14.6667px]很简单，不过当时已晕，被很nice的小白指出错误无数。*/
    ///-----------
    /**
     发个面筋 - （FB Seattle University Day）第一轮：白男 behavior question，问team conflict怎么处理，还问了research；(头一回面试准备得不好)
     第二轮：同胞 Leetcode, Spreadsheet Column, number to name & name to number; LCA in BST;
     第三轮：白男 Leetcode, Word Search;
     白板写的太少了，所以写的有点慢；
     感觉fb需要写的快，而且代码要规范；
     anyway, move on. */
    ///-----------
    /**
     四轮技术面试，每轮四十五分钟。每一轮先聊简历十到十五分钟，接下来做题，最后留下五分钟问问题。
     1st: Given a m * n grid and the coordination of left bottom cell is (1, 1) and the one of right top cell is (m, n). At each cell say (x, y), you have to choice to move, going up or right. If you go up, the destination is (x, x+ y). Otherwise your destination is (x + y, y). Find the minimum number of steps to reach the cell (m, n) from (1, 1).
     2nd: Find k closest number in an array of a given value
     3rd: Leecode minimum window
     4th: Check if a tree is BST */
    ///-----------
    /**single number，数组有序，要log(n)
     行升序矩阵，找kth，分析多种方法
     flatten二叉树.
     bfs遍历二叉树，从左到右返叶子节点
     好好练习，祝好运 */
}
