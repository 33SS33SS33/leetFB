package aFB;

/**
 * Created by shanshan on 16/5/15.
 */
public class failExpe {
/**第一轮 culture fit， 闲聊自我介绍下，问问你做过什么，有没有什么有意思的项目可以介绍下之类的，然后一道题，给一个数组，move所有非零的数到左边，
 * 无所谓右边剩下的数是什么，要求尽量少的write
 *
 第二轮 设计题 设计一个网络爬虫
 第三轮 leetcode原题，regax match的那题，面试前一周刚做过，但是面试那会有点不是很清楚怎么dp了，模模糊糊的想起来了点思路刚讲一半，面试官说dp不够好。。
 让我想别的方法，最后无果，dp也想不起来咋写了，最后写了个垃圾递归，跪就跪在这一轮了。顺便吐个糟，第二轮面完带我吃饭的人没有来。。。过了20多分钟说那个人不来了。。。
 换了一个烙印带我去吃饭。。。于是乎午饭时间爆短。。。随便吃了点紧赶慢赶还是迟到了几分钟，于是乎第三轮的面试官怎么看我怎么不爽。。应该是觉得我居然面试还迟到。。。
 真是倒霉。。关我个毛事啊。。一起吃饭的烙印磨磨唧唧。。催了几次才走。。。.
 第四轮 请允许我无耻的发个广告，这题我做过。。http://www.lostscroll.com/%E5%A4%9A%E4%BB%BB%E5%8A%A1%E8%BF%90%E8%A1%8C%E6%97%B6%E9%97%B4/，
 follow up是给你一个task队列， 要求你自己排列要求最后的总时间最小，可以用一个哈希表统计下出现的次数，然后按从多到少排列下，结果当做input丢到第一题里面就可以出结果了
 第五轮 半轮culture fit聊天然后一道leetcode原题，read4 ii那个，不过这边是read4k, 毛油做过花钱的题，好后悔，不过最后居然做出来了

 这次面试跪估计就是跪在第三轮了。。。比较无念。。move on again。。。希望对大家有所帮助*/
///-----------
/**算上recruiter闲聊一共7轮。
 1）早上recruiter从大堂带去面试的会议室，一开始还以为是第一轮的面试官。15分钟介绍一下流程，1轮聊天+2轮coding+1轮设计+1轮不计入分的coding+吃饭。
 2）聊天没什么可说的，注意culture fit
 3.1）longest palindrome，dp解的.
 3.2）两个词的最短距离变体，follow up词很多怎么省空间。.
 4）吃饭
 5）Web crawler
 6.1） 忘了.
 6.2）LC原题股票I
 7）这轮不计入分，就是一个考官考察另一个新手考官的面试能力，我被当成小白鼠用。
 7.1）一个矩阵斜着走的list例子如下：
 123
 456.
 789
 输出：{1}{42}{753}{86}{9}
 7.2）max sum subarray，我用了sum = nums[i] + (sum > 0 ? sum : 0)，考官好像不知道这个做法，让我解释了半天，还给了两个明显他觉得是corner case的test才放过。 这些小白都不刷题吗？
 8）严重吐嘈FB的recuiter，过了一周半我发邮件问了才告诉我挂了，追问feedback过了一周多才回复一个法律原因不能说。*/
///-----------
/**1. 欧洲某国小哥，口音有点重，3sum，还有一个忘了，不过都是LeetCode经典题。比较注重速度和bug free。

 2. 白人manager聊论文，完了后问了一个简单题，给定一堆interval，找overlap最多的时间点。也是经典题了。

 3. 南亚某国面试官聊system design，挂就挂在这一轮上了。问题是：设计一个系统，对于一个facebook上的照片，增加一个点赞和评论功能，并且要求对于所有正在看这个照片的其它用户，实时推送点赞人数（名单）以及所有评论。过程中讨论了很多push vs. pull，内部系统如何处理scaling等的问题。个人觉得聊的还不错，但还是挂了。后来面试完后，FB的recuiter仔细问了这道题，貌似比较意外的样子。他比较在意讨论过程中我是不是在lead discussion，看来这一点是他们比较在意的地方。我和面试官基本是一半一半的情况吧，在介绍完框架后，很多时候是他提问题我回答。有可能这方面有欠缺，大家引以为戒。

 4. 国人小哥，一道题是在BST中增加right pointer连接每一层；另一道题是在递增数组中搜索给定数字的起点和终点，如果没有，就返回空。两道题也都是LeetCode原题。怒赞国人小哥，感觉特别照顾。

 总的来说，感觉FB的题问的不难，不过细节要求比较多。我还问recruiter说是不是因为PHD所以放水，不过被否认。一周后说HC没有通过，并且主动offer提供更多细节。电话讨论后，结论就是，code部分反馈很好，design部分反馈不好，说交流不是很好（？？？！！！本人一向以能说会侃著称）。回头想了想，面试官的口音很重，我的确有的时候没听明白，让他重复了几次，不知道是不是这个原因。

 再次怒赞FB的recruiter。体验非常好，目前所有面过的公司中（FLGM）最好的一个，没有之一。而且所有别的公司都表示不能提供面试反馈，只有他主动offer。哎~

 个人感想：以后面试，不知道可不可以跟HR说：我听力不是很好，对口音很敏感，能不能安排口音清的？当然我是中国人，所以中国口音OK啦。。。。要是有同学试验了这一招，建议到地里汇报一下:)

 祝大家好运！*/
///-----------
/**
 都答出来 也是拒 难道就因为给了hint？
 哎 最想去的大公司。。。罢了
 1. ninja: paint house大变种. n houses, k colors. neighboring houses cannot be painted with the same color.
 NOTICE: neighboring relationship is given by adjacent list which means a house may have multiple neighbors.

 2. ninja: 放水。 reverse Linkedlist (both recursive and iterative). buy and sell stock I, II (output transaction)
 3. Jedi. behavior questions/research. coding: clone graph. give a class Graph, return 一个clone的

 4. Pirate. Design Wikipedia crawler.
 followup 1: No global status.
 followup 2: deal with machine failure
 followup 3: make the wiki site unaware of this crawler.*/

    ///-----------  博士
/**(1) coding
 point coverd by most intervals
 2D （covered by the most rectangles）
 (2) system design
 privacy design
 (3) system design

 machine learning system design.
 (3) PhD research

 talk about research and intern experience
 gate and wall, find the minimum distance between position from any gate
 (4) coding

 search in rotated array
 BST to doubly linked list

 应该是挂在了第二和第三轮的sysmte design. 鐗涗*/
}
