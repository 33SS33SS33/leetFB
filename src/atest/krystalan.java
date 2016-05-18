package atest;

/**
 * Created by GAOSHANSHAN835 on 2016/5/15.
 */
public class krystalan {
    /**周五刚做了一个60 mins phone interview，发面经回馈论坛。 废话不多说直接上题:
     hashtable, dictionary class
     hashtable stores key-value pair in Map.Entry. Any non-null object can be used as a key or value, hashtable works on hashing algorithm and uses hashCode() and equals() method in put and get methods.

     When we call put method by passing key-value pair, hashtable uses Key hashCode() with hashing to find out the index to store the key-value pair. The Entry is stored in the LinkedList, so if there are already existing entry, it uses equals() method to check if the passed key already exists, if yes it overwrites the value else it creates a new entry and store this key-value Entry.

     When we call get method by passing Key, again it uses the hashCode() to find the index in the array and then use equals() method to find the correct Entry and return it’s value.

     优点：time complexity of O (1). Dynamic variable length data storage
     超级快速的查询速度，如果有人问你什么数据结构可以达到O(1)的时间复杂度，没错是HashMap;   动态的可变长存储数据（和数组相比较而言）

     缺点：need extra computing a hash value.
     If not handled properly will take up extra space
     需要额外计算一次hash值,如果处理不当会占用额外的空间

     hashtable time complexity  O(1)
     hash collision
     in java, if collision happens it will simply discard the old one put the new one in the position.
     how to avoid hash collision.
     There are two ways in such situation. One way is to create a list for each hash bucket,the list will store values with the same hash value.
     Another way is to increase the number of bucket, and recalculate all the hash value of each element.

     Increases the capacity of and internally reorganizes this
     * hashtable, in order to accommodate and access its entries more
     * efficiently.  This method is called automatically when the
     * number of keys in the hashtable exceeds this hashtable's capacity
     * and load factor.
     DFS, BFS 定义区别，一些实例
     BFS的思想：
     从一个图的某一个顶点V0出发，首先访问和V0相邻的且未被访问过的顶点V1、V2、……Vn，然后依次访问与V1、V2……Vn相邻且未被访问的顶点。如此继续，找到所要找的顶点或者遍历完整个图。
     Breadth First Search :you start from a node of a graph  N0,  then you visit the nodes which adjacent to N0 and have not been visited N1,N2,...,. access and V1, V2,....vn adjacent and haven't been visited node , layer by layer. So go on, traverse the whole graph. usually use a queue to do BFS.
     mainly iterative implementation;  Level traversal  of tree is using BFS

     DFS的思想：
     顾名思义，深度优先搜索所遵循的策略就是尽可能“深”的在图中进行搜索，对于图中某一个顶点V，如果它还有相邻的顶点（在有向图中就是还有以V为起点的边）且未被访问，则访问此顶点。如果找不到，则返回到上一个顶点。这一过程一直进行直到所有的顶点都被访问为止。 DFS可以搜索出从某一个顶点到另外的一个顶点的所有路径。 由于要进行返回的操作，我们采用的是递归的方法。
     depth first search : you start from a node of a graph  N0, if it still has adjacent node have not been visited, access the node, same goes to this node, visit its adjacent and have not been visited node. If you cannot find one, it returns to a node.  go on until you visited the whole graph. usually use a stack to do DFS.
     mainly is the recursive implementation; preorder traversal of tree is using DFS

     do recursion if there is no base case -> would result in an infinite recursion,stack overflow;

     一个用bst编辑的字典，上面有各种product name and category. 求找到一种具体产品的时间复杂度

     there is a search method called binary search method, it makes full use of the ordered relationship between elements,
     n elements can be divided into two roughly equal parts, compare the middle element with the one want to find,
     decide to do binary search in left part or right part,  in the worst case with O (log n)

     The most challenging work you've done.
     Just entered the new company, because more and more product line, the function is becoming more and more bloated, difficult to maintain . It needs to reconstruct. but there is no corresponding document to record these functions. I have to read through the code to confirm the function of each product.
     So I complete the task by broken it down into many small cycle, each iteration I can consign a version, and test it then do the next cycle.

     the result is I improved the scalability of the program.
     How you are going to improve the code.
     Reading open source, and we do code review every week, improve the quality of code, it helps identify potential bugs, reduce accident cost. improve the overall level of the team.

     The feedback given by your manager or colleague. Tell me about a time where you had to deal with conflict on the job.
     The feedback given by my manager and colleague is positive. I can't think of an example  they give me negative feedback.
     if I have to I would say when we discuss a specific implementation scheme sometimes we had different opinions, but it's common in the workplace, it's not personal.
     When we have debates, I have to deal with conflict, not passive-aggressive I would:

     I will get both sides of the argument, discuss pros and cons, show empathy, let them know I understand what they mean., eventually we have a agreement.

     if I have to deal with conflict, not passive-aggressive I would:
     o Getting both sides of the argument
     o Suggesting and agreeing compromise
     o Showing tactfulness
     o Showing empathy

     大家可以特别关注下Hash table, BST。

     How did you handle a challenge?
     * During a difficult financial period, I was able to satisfactorily negotiate repayment schedules with multiple vendors.
     * When the software development of our new product stalled, I coordinated the team which managed to get the schedule back on track. We were able to successfully troubleshoot the issues and solve the problems, within a very short period of time.

     How HashMap works in Java?
     The other important things to know about HashMap are capacity, load factor, threshold resizing. HashMap initial default capacity is 32 and load factor is 0.75. Threshold is capacity multiplied by load factor and whenever we try to add an entry, if map size is greater than threshold, HashMap rehashes the contents of map into a new array with a larger capacity. The capacity is always power of 2, so if you know that you need to store a large number of key-value pairs, for example in caching data from database, it’s good idea to initialize the HashMap with correct capacity and load factor.
     HashMap不是线程安全的


     */
}
