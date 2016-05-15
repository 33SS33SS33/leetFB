package aFB;

/**
 * Created by shanshan on 16/5/15.
 */
public class failExpe3 {
/**
 Round 1: cultural, project deep dive, things that can be done better
 Round 2: Calculator, implement a function to output the current display based on the inputs that is already typed.
 Round 3: Word Ladder II in Leetcode
 Round 4: Design facebook event notification system (push on mobile app, and pull on web), discuss storage/api, how to generate events, prefer to use periodical job to scan incoming events and generate messages accordingly
 Round 5: Culture fit, strength, how to deal with disputes among colleagues,
 reverse linked list in pairs. */

/**
 店面问的是稀疏数组的inner product
 上周一昂噻
 1. 美国冷面哥 简历+behavior+类似于sort colors但是扩展到k个color 写一点代码，主要说，几种方法每种复杂度。楼主behavior question是准备的不好。这轮表现一般。

 2. 貌似欧洲微笑大哥 minimum window containsing a string (LC 原题) + n th Fibonacci number mod 10.
 不出意外的话应该挂在这轮了，不过楼主真是觉得有点冤枉。那个大哥人很好，不过英语不是很好。minimum window那题只让返回长度，
 楼主详细问完各种edge case大哥期望返回什么东西之后就给秒了（本来想多BB一会来着，太紧张了）。后来大哥如果空字符的时候应该返回0更有道理。
 楼主说大哥啊，我刚才问过你，你说返回-1就行。大哥说啊啊，好吧。然后大哥说那如果pattern里面有重复的字符呢，我说这个已经考虑这种情况了（其实心里后悔没之前问问他）。
 大哥好像原来打算把这个作为扩展来着，结果也没扩展成，而且时间还有很多，想了想，来了那道坑爹的第n个fibonacci数字的个位数这道题。楼主没见过，不过直觉就说肯定有规律。
 然后大哥说那你手列一下能不能找到。我就开始写。结果写了20个没什么规律。大哥说*这个没规律*。我说那好吧，那就直接DP O(n). 大哥没让写，说更快的。楼主一下当时绞尽脑汁疯狂想，最
 后过了5分钟吧，想到那个矩阵运算的，O(logN)。大哥说不用写代码，要更快的。卧槽楼主SB了。实在想不起来了。大哥给提示说a+b有几种可能，我说100 （10*10）种，
 后来大哥说(a,b)这个pair肯定会重复，所有那个数列是有规律。我特么怒吼10000次啊，你不说没规律嘛！！！！我根本没往有规律上想啊！！！你就和我说有规律我证明就完了呗！！！！
 不过楼主很淡定，最后气氛也还可以，目测挂在这轮上了。.

 3. nice国人 找出平面上离原点最近的k nearest points。楼主说可以priorityqueue。大哥说不错，挺满意。结果楼主一紧张说也可以select k。更快。结果大哥说那你写一个吧，写哪个都行。我说那就写select k吧。我特么记得select k挺短的啊，结果写了满满一墙。大哥看了半天。后来又问问如果内存装不下怎么办。说了几句就开始聊天了。

 4. 白人美女 打印树的所有根到叶子的所有路径 结果楼主有一点点小bug，手动check的时候check出来改掉。然后美女说再来个count islands吧。我说DFS BFS disjoint set都OK. 她说你给我讲一下disjoint set。我就一顿扯。然后实现个DFS就好。然后各种聊。

 楼主1周后得到拒信的。要feedback不给。哎，这个面试我准备的最多，刷了n遍LC。哎，Move on吧。*/

/**被HR忽悠来面production engineer
 1. 纯系统讨论, 涉及到troubleshooting等等, 感觉问题主要集中在linux administrator 这一块..
 2. 和manager聊天, 聊项目..
 3. find all anagram, 这个写的太慢了, 第一次onsite特别紧张. 结果感觉时间不是很够, 问了几个简单的follow up和怎样找两个文件里面一样的string就结束了.
 4.1 find battleship, 给一个matrix, 里面有一个battleship, 以特定的形状出现, 怎么找到它的坐标.(没有干扰物).
 4.2 写一个shell script去检查一万台remote server上的某些进程是否正常.

 一周后悲剧, 没有feedback*/

/**
 都能在 leetcode 上找到原题。.
 1. one edit distance
 2. move 0 to right.
 3. max square in matrix
 4. convert binary tree to double linked list.
 5. remove miss match paretheis in a string
 6. find a number in a rotated sorted array.

 结果：fail

 至此：FLG 均已面完，统统挂掉，在此求大侠们内推*/

/**
 Phone: find alibaba
 On Site:
 Read N char using read4.
 Regular expression matching*/

/**
 * a.Binary tree print all pathes from root to leaf
 * Computation complexity (space/time)
 Follow up: Print the based on column

 --A, _B, D
 __A,_B,__E

 Space and time complexity (worst case)
 b.1 , 2,  5, 8, 9 , "+" "-" "" target 50
 Print all possible way to reach 50
 How to Prune.
 2.
 Behavior, why FB, which facebook feature do you  like best, how to improve it
 Your work, challenges, bugs.
 10 mins left Stock1

 3
 Status update and search system design:
 Status example: I eat a pie, I bouth a car
 Search eat and pie  return I eat a pie
 Search a or bought return  only search in  last N day
 Start with the architecture, based on the given number of users, number of status each day
 Estimate QPS, the storage, DB schema
 Invert Index data structure for search How to implement invert index, data structure.
 Index update function how to update retire the expired data
 Data is too large to fit into memory, how to handle,
 DHT, How to hash the data( hash the words or id) , pro, con

 4.
 Read4 multi times
 Print a Binary Tree in Vertical Order*/

}
