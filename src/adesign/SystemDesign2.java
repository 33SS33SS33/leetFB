package adesign;

/**
 * Created by GAOSHANSHAN835 on 2016/4/14.
 */
public class SystemDesign2 {

    /*1. Singly, ListNode convertBT(TreeNode node);
     我觉得你问得几个问题都非常好。我跟面试官的交流过程是这样的
     1. 首先我跟面试官确认的是requirement,至于web service来讲就是API:
     (a) Url shortenUrl(Url longUrl), which is write request
     (b) Url getLongUrl(Url shortUrl), which is read request

     2. 接着我就跟面试官问capacity流量问题，也就是类似于你问得qps，我就问是有多大的traffic，面试官说要很大的流量，比如能够支持twitter上面tiny url的流量。
     从系统设计的角度要求就是要production ready：it needs to be scalable , high available, and high performance.

     3. 接下来我就分析你所提到的read/write heavy问题，通过分析以及我们的经验可以推理，tiny url service的read/write ratio肯定很高，也就是它是一个read heavy system。从用户的角度来分析需求，对于read request，你肯定希望反应越快越好，而且以下场景是设计者需要考虑到的，某些hot url的qps肯定非常高,你一定希望read reqeust to be very scalalbe for large qps. 对于write request来讲，首先我们可以想象的是它的qps不会高到哪里去，用户在产生tiny url时多等一秒半秒的并不会太影响用户体验，但是对于read request就不一样了，你肯定希望它反应越快越好，但是write request对于consistency的要求却是非常高的，也就是你不希望不同的long url generate the same short url，race condition or short url collisions should be avoided。从系统设计的角度就可以得到这样的具体需求
     （a）read request: highly scalable, high performance, high availability.
     (b)  write request: consistency

     经过以上的交流基本上这个系统的具体需求就出来了，然后就是着手design，如何从架构的角度来满足以上的需求。你说提到的架构基本跟我一样，但是后台database,我建议用key-value nosql database， key is tiny url, value is long url.原因很简单，SQL database is not designed for high scalable, high available purpose.而且它的强项ACID, Transactional,以及处理join relational table，除了consistency/transactional，我们是用不上的，反而带来performance等问题。key-value store is a perfect match for us.
     所以我的系统架构基本是这样子的： load balancer -> web server -> distributed cache -> key-value store.
     现在我们来考虑以上架构是否能够满足提到的需求。
     (1) read request

     Scalability: 由于我们的service是stateless的，web server之间share nothing，所以通过load balancer，web server这一块是非常容易horizontal scalable的，然后继续往后看，由于cache的帮忙，很大程度上会解决高QPS的问题，但是我们仍然希望他能够尽量大的take read request，并且可以解决hot spot 的问题，如果所有的请求都由一个data store 来处理，这个明显不scalable.这个时候能我们其实就可以考虑data paritition了，也就是用多个data store 实现负载均衡。很容易想到consistent hashing，我们可以基于short url再说一次hashing， 将read request distribute 到data store on the hashing ring.这样子数据库也解决了scalability的问题了。
     Availability: 这个的重点仍然实在数据库上，web server 的可用性比较简单，down掉一两台机子没啥事儿，考虑到area data center outage，也就是多在几个数据中心部署就可以了。data store的高可用性就是replication，有两种方式： master-slave和 peer to peer，我们的应用场景master slave特别合适，为什么，因为前面说了，这个系统是read heavy, master 和slave both can take read request，but only master can take write request, 而且对于写操作，master 必须保证所有的slave都完成了同样的写操作才算是一次成功的write request.这个就可以避免刚产生的tiny url，不会出现read reqeust 返回not found. p2p也可以实现同样的功能，但是复杂度大一些，这里没有这个必要。
     Performance: 这个其实经过以上的设计问题就不大了，得利于cache的帮忙，这个应该问题不大。至于move data closer to the user这种方案好像队tiny url这种场景用处不大，因为毕竟数据量小，如果是video/image这时候就可以考虑cdn这类的了。

     （2）Write reqeust
     前面提到了consistency，也就是说我们怎么保证写操作的atomicity。这里有两种方式-google 1point3acres
     （a）pessimistic locking: 就是所有的写操作都要先acquire a lock, and then perform write, at last release the lock，可以看到每一次的写操作都要先拿到所，所有的操作都serialized，明显性能是不太好的。
     (b) optimistic locking：这种方式是数据库里的每一条记录都有一个etag,你可以想象成timestamp，在update request需要attach一个etag,系统会检查这个etag跟现在的是否一样，一样的话就会perform update，否则就会失败，你重新读数据然后再更新。基本原理就是atomic compare and set，write request的etag你可以就是认为是空。这种方式再有高并发的update操作时性能比较差。

     比较以上两种方式，我倾向与第二种，因为前面提到，写操作本设就不是heavy的，另一个主要原因是long url hash to tiny url的算法应该会保证产生collision的概率非常低，但是我们还不得不处理collision的情况，所以第二种方式完全perfect.
     鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�.
     经过以上几步基本上这个系统就可以满足需求了。由于一开始的时候我还提到过analytcs的问题，所以follow up还有问我如何做analytics,这基本就是如何做data driven web service的问题了，是big data的领域，让我实现 top n hot url. 今天太晚了，明天看看有时间可以稍微介绍一下这个。*/
}
