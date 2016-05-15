package aFB;

/**
 * Created by shanshan on 16/5/15.
 */
public class failExpe2 {

/**
 * 2016-3-26 因为工作不到一年，所以仍然参加University Day的面试，一共四轮：1，原题，一维vector相乘，然后扩展到sparse vector，每个vector就是一个list而已，或者想象成array；
 2，leetcode原题，move zeroes，一维数组，非0移到右边，0移到左边，相对位置不变；还有一题有点忘了，onsite了太多家混乱了，貌似是binary tree，找最大path sum之类的，backtracking的方法；
 3，leetcode原题：Populating Next Right Pointers in Each Node II， LZ忘了标准方法了，想了半天最后用level order traversal的方法来做的，估计面试官自己也没想过这种方法，之后仍然要求用别的方法（就是所谓标准答案。。）最后想到了不过没写完，面试官说ok；
 4，culture fit面试，一个说是早于90%员工的senior胖白人面试官，事后想想可能因为有了几个offer了所以并没有太把FB当回事，没能表现出那人想要的感觉吧。。（所以还是应该跪舔一下FB，不能太屌。。）另外要表现出你是真心想去FB才去面试的，而不是因为别的原因。。总而言之，这轮被刷了，只能说注定跟FB不合。。
 总结：FB真的跟别人家就是不一样，全套leetcode原题。。不过大公司里面他家culture最好，值得好好面一把，大家加油。*/

/**每一轮都很简单
 第一轮: 三哥，输入一个array，元素都不是负数 和一个 int: target   输出是否存在有没有subarray sum = target, follow up 有负数怎么办
 第二轮: 亚裔小哥，常见题， 给一个string，删掉不正确的括号，  比如 输入 ()()(()  输出 ()()()   只要一个解就行,      讨论了几种方法， 顺带写了一下 Leetcode Remove Element
 第三轮: 三哥，Jedi， 都是常见问题，聊简历＋why facebook ＋怎么handle conflict
 聊完简历后没剩几分钟了, 写了一道 https://leetcode.com/problems/lo ... r-of-a-binary-tree/
 面完感觉超级良好， 每道题都刚好面前准备过， 没被指出来过有bug，  不过感觉第一轮的sliding windows 写得不太干净吧，被要求改了一下， 减少了两行
 面完过后收到拒信   不过冷冻期不是一年， 叫我过3-6个月联系他们*/

/**朋友帮忙推荐给recruiter的，两轮电面之后拿到onsite。
 第一轮： 老印，上来一道题，讲了半天我才弄明白。类似手机按键，比如手机按键上2对应‘abc’, 然后根据‘abc’的顺序，打出a要按一下键，b要按两下键，c要按三下键。给你两个数组: keySize[] 每个element代表能存放的最多character，frequency[]每个element代表每个character出现的频率。要算出最少的按键次数。 Follow up 1: 怎么能提高效率。 Follup up 2: 如果要求character放在按键上的顺序是order的，类似于手机shang的字母按键，这样最少按键次数是多少。
 第二轮：还是个烙印： 第一题：rotated sorted array search. 让后要求cut branch。
 第二题： sort an array contains only 3 element，类似leetcode的sort colors。 follow up: what if there are N element? 没想出来， hint是可以使用extra memery
 第三轮： 简历问题为主，问了一道code： check the first bad version.
 结果还是跪了。问题应该出在第一轮面试上，code写了好久才写出来，follow up也没答上。其实题目也不算很难，大家好运吧。
 补充内容 (2015-7-28 05:16):
 好吧，可能是我表达不好，第一题不画个图真不大好说。
 例子就是我们的手机，几乎每个键都对应字母： key2 -> 'abc', key3 -> 'def', key4 -> 'ghi'....老式的手机打字的原理是，如果你要打出a，你需要按1下key2....*/


/**1. tell me about yourself.
 2. what do you learn from your internship. Waral
 我就说我学到很多啊，比如ownership啊，怎么把自己coding style fit in the team啊，怎么快速学习问题啊等等。
 3. why facebook
 我就说两个，一个是facebook很牛逼啊make impact啊，之前那个facebook app在2012年之前还是很慢的因为是web base的
 跟着后来就变得很快啊说明facebook一直都在进步啊，这时候jedi就说“哦！我当时也在那个组里面，我做的是那个阅览图
 片那个模块。” 跟着我说第二个就是facebook的open culture很适合我，我之前的那个实习公司也很open，员工卡上没有title
 大家的idea都能够交流。.

 总体来说我觉得behavior基本是秒他的，因为我觉得我准备了他可以问的所有问题了哈哈。跟着coding问题
 字母和数字的转换 A = 1 B = 2 AA = 27 基本是26进制的转换， 他要我写了两个边的转换。 我写出来了不过最后我用的是
 (char)('a'-1+i) 的方式来转换字母的，不过我用的是i%26，也就是z的时候会变成(char)(-1)。这个bug被他看出来了，跟着
 他一个箭步上来帮我改了！！！加了个if。。。。跟着就说好然后走了。。。.

 第二轮ninja
 一上来直接code，找小偷问题，有n个房间，其中一个房间有小偷。早上我们可以打开一个房间的门看小偷在不在里面，晚
 上小偷会向左边或者右边的房间走。现在给你一个开门的sequence，你输出这个sequence能不能保证找到小偷。
 比如：如果只有三个房间那么如果打开房间的sequence是{1，1}那么一定会找到小偷。因为如果小偷在中间那么第一天就会被找到，
 如果小偷在两边那么第二天一定回来到中间也会被找到。房间数为n，sequence长度为k
 跟着我开始brute force假设小偷在某个房间然后dfs所有路径，大概是O（n*n^k）。 考官说好，如果考虑cut branch呢？跟着我就说可以
 拿一个n*k的matrix跟着根据sequence来cut branch，reduce到O（n*n*k）。他说有没有可能同时从所有房间开始呢？我说可以跟着直接
 在那个n*kmatrix上做一个类似dp的东西。跟着reduce 到 O（n*k）。他说有没有可能把space reduce呢？我说可以我只要O（n）的space
 跟着他就让我再写一个叫nextRow的function来实现O（n）space。 我觉得这题我基本是答得非常漂亮的而且思路很清晰，考官也很开心。

 第三轮ninja
 word ladder变型，叫我随便找一个可以的path出来，基本我写的每一步她都要我说这样写的理由，跟着做笔记。我用dfs+hashset写完之后，
 被她发现了一个bug，就是在找到path之后我没有完全return导致答案没有了最后一个word，跟着我马上改了。之后她问我能不能cut branch
 我看不出来。。。。她提示其实放进hashset的可以不再remove，因为如果走过一个word发现这个word不行那么以后就没有必要再走这个word了。
 跟着问我如果word可以从abc变道abcd 就是变一个或者加一个letter我应该怎么改。我就说加点东西就好，跟着就写出来了。跟着这轮就大概没了。

 一周后收到拒信。.
 看来应该是第一轮没有bug free和最后一轮答的不大prefect和有bug。挺伤心的. */
}
