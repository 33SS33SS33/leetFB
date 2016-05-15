package aFB;

/**
 * Created by shanshan on 16/5/15.
 */
public class experience {

/**
 实在很感谢论坛里的内推，还有大量分享面经的亲们。背景是国内微电子985，硕士转Computer engineer中途再转软件,40+排名学校gpa3.9。4个月实习经验（中型公司）。
 其中过程也是特别辛酸挂了无数公司 呆在无数冷宫都要放弃了去小公司了，没想到最后在luck和4遍leetcode(最后一遍每天20道)助力下斩获facebook。

 因为签了啥NDA...我就聊聊我做的一些有趣的题目和准备面试，基本来自leetcode，如跟面试雷同，纯属巧合。

 1. 简单5题游string to int、palindrome string、palindrome int、linkedlist int palindrome（iterate & recursion）认真implement并且除bug
 2. 印度哥最喜欢让人弄prefix tree的构建和搜索和优化（带regular expression的“.”
 3. 美国人听说我做题多，总是出一些怪题，find longest subarray 等于0 还要O（n）话leetcode那道find max retangle in boolean 2d array真是有趣啊，面试的话15分钟至少讲清楚演示清楚。.鏈枃鍘熷垱鑷�1point3acres璁哄潧
 4.3sum。以前project和前公司(老板啊前teammates)*/


/**2016-3-11
 首先想说FB现在new grad还在招，大家加油。
 因为签了NDA，题目具体就不说了，只想聊聊Leetcode刷题心得，91 decodeWays，21 Merge Two Sorted Lists，23 Merge k Sorted Lists好像很有趣的样子。
 然后这篇帖子里边的那个算法题也挺有趣的，可以去问问楼主http://www.1point3acres.com/bbs/thread-176584-1-1.html
 英语不好的一定要练好英语啊，楼主托福口语万年18分，果然差点悲剧，还好运气好，勉强过了。
 手里也没什么offer，之后的面试也没啥竞争力，不出意外应该接了。*/


/**一面：
 给定任务AABCB, 冷却时间k（相同任务之间的最短距离时间），任务顺序不能变，问完成任务的总时间。
 例子：AABCB, k=2, A**ABC*B, 时间为8.
 解法：用hashtable保存上次的时间。
 Followup1：如果k很小，怎么优化？
 解法：之前的hashtable的大小是unique task的大小，如果k很小，可以只维护k那么大的hashtable。
 Followup2: 如果可以改变任务的顺序，最短的任务时间是多少？
 例子：AABBC, K=2, AB*ABC, 时间为6.
 解法：根据每个任务出现的频率排序，优先处理频率高的。但是具体细节没有时间讨论。
 感觉前两问回答的还好，就是细节和反应有点慢，最后一问没时间讨论。预感非常强，肯定会被加面，果然。*/


}
