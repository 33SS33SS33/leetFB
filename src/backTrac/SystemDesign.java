package backTrac;

/**
 * Created by GAOSHANSHAN835 on 2016/3/9.
 */
public class SystemDesign {
    /**
     * 首先你要明白，所谓的system design是要做一个能够在现实世界中运行的系统的设计，所以说system design可以cover任何topic，从建大桥到做手机到做网站，而局限到软件，尤其是面试的scenario，大体局限于以下几种：

     1. Web application，典型例子：设计一个e-commerce商品的detail page(参考Amazon商品detail page)。设计一个online小游戏。设计一个论坛。
     2. 简单的Web Service，典型例子：设计一个short url service。
     3. 实时／半实时的消息update，典型例子：messenger，news feed。
     4. 数据系统，典型例子：top url hits, unique url hits
     5. 内容分发系统，典型例子：设计Netflix CDN
     6. 专业知识，典型例子：推荐系统，分布式系统基础架构，搜索。。。

     个人认为，要想在System Design上面达到像刷题那样的熟练程度，对于刚刚入行一两年甚至三四年的朋友来说，是不可能的，因为这些问题要想准确答出，已经远远超出了对junior engineer的要求，甚至已经是senior engineer的水准。

     但是即使面试官并没有期望你达到senior engineer的水准，至少他还是想要通过这个问题来摸清你的工程经验，如果所答完全非所问，那么给面试官留下的印象将是非常糟糕的。你的方案可以不是最优的，甚至可能离最优有一段距离，但是你的方案一定不能是ridiculous的。起码在小的scale上要能有可行性，可以展现你的一些基本design sense。

     基础知识的准备：
     1. 数据库 —— 了解relational数据库(Oracle/postgres)的基本知识，了解数据库的partition，了解查询，了解数据库的replication；了解现在流行的NoSQL databases: key-value database (Riak/DynamoDB)，document based(mongodb)，graph based, big table(or column based - HBase)...这里DynamoDB有个paper，关于eventually consistence需要明白CAP定理。
     2. 队列服务—— 了解Kafka或者Kinesis，明白队列服务的应用场景
     3. Web层，了解MVC，具体技术可以了解Spring，Nodejs
     4. 前端，了解Javascript，Html
     5. 了解SOAP和RESTful
     6. 理解cache——如何以及在何种情况下运用cache降低latency
     7. 理解现代分布式系统需要大量monitor以及log analysis
     8. 理解系统中不能有single point of failure，从failure的角度出发设计系统，运用Write Ahead Log进行故障恢复，充分replicate你的service所以任何一个机器、集群、机房的灾难都不会对你的整体服务造成不可挽回的影响
     9. 处理高并发，明白资源共享是影响并发的主要原因之一（另一个原因是进程间通信）——如何decouple共享资源，提高并发效率
     10. 明白基本的效率评定标准，如TPS。。。
     11. 理解一些分布式系统的基本概念，如上面提到的CAP，以及Consistent Hashing，Vector Clock
     12. 读一些paper，如Akamai的CDN，Amazon的Dynamo，Google的Map-reduce（很老嗯）等等
     13. 亲手实现一个简单的网站，从前端到数据库都接触一些
     14. 了解Hadoop的基本功能，如HDFS，Map-Reduce。
     15. 了解Apache Storm的基本功能。
     16. 结合所学的基础知识，考虑你所设计的系统的Availability，Scalability和Performance.

     基本想到的就这些。有需要的话我再补充。*/


    /*
F家第一轮电面可以去公司当面谈，lz有点好奇F家的办公地点，听说很高大上，就选择面谈

第一题. 1point3acres.com/bbs
设计一个SparseVector （就是一个超长的vector，大部分elements都是0）的class，实现dot product的操作。follow-up1:如果一个vector很长（millionsof non-zeros）， 另一个vector很短（hundredsof  non-zeros），如何优化。follow-up2:如何利用index之间的关系（比如设计class的时候规定按照递增的原则存non-zeroelements的index）进一步优化。
lz这里犯了错误，上来直接直奔followup2最优解，傻x老印好像有点不忿，擦，这题目lz之前见过，貌似应该先扯扯暴力解法，哎。
第二题
trie数build和查找，结果被老印指出来一个bug，郁闷。

总结一下：
其实大家如果自己总结面经的话就发现了。。他们出题都是这个套路。。我在面经里面看到几乎都是random 那道题和window这道题搭配。  reverse linkedlist 和mutex搭配。每次必问复杂度。对bug free要求很高。
中东小哥的口头禅就是I think there might be a bug here
Mutex可以用atomic test and set/compare and set实现

先出了一个题是convert binary tree to doubly linked list.我说第一轮做过了非常类似的题。
然后就直接写了他的Backup problem.leetcode 211 原题， add and search word.

对于有工作经验的candidate, FB只一轮专门聊project deep dive和culture fit，这个现在几乎所有的公司都会问吧。你需要把你最喜欢的项目了解的非常清楚，architecture, end-to-end working scenario,
自己负责的部分，以及做的contribution，技术细节都要非常非常清楚。

System Design：设计一个K recent contact 的service，就是当用户把鼠标点到chat对话框的时候，自动弹出K个最近的联系人。
follow-up是如果要弹出K个最熟悉的人怎么设计，以及资源估计（需要多少台机器来做数据存储，多少个处理request等等）。

设计一个news feed的排名算法，用用户的implicit反馈做label：share，clickthrough，like等。
*/


    /*3.Uber
    设计Facebook photo的功能，问得很细，从前端的不同设备的处理，到后端架构(打算用什么语言，platform)，到数据库设计和选择(就差把数据库的表写出来了)，
    cache策略，CDN，socket，disaster control，备份策略都问倒了。

    题目很常见，就是有 Key ,Val, TimeStamp 的hashmap的add ,get ,remove 的实现。*/
}
