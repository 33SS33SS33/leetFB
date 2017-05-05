package aFB;

/**
 * Created by krystal on 5/5/17.
 */
public class interviewtips {
    /**
     * Find sets of values in array whose sum is equal to some number.

     Find the subarray within an array (containing at least TWO number) which has the largest sum.
     For example, given the array [-2,-1,-3,-4,-1],
     the contiguous subarray [-2,-1] has the largest sum = -3.
     try to do it in O(n) time
     Followup, if input is stream, how to solve it
     public int maxSubArray(int[] nums) {}

     A "derangement" of a sequence is a permutation where no element appears in its original position. For example ECABD is a derangement of ABCDE, given a string, may contain duplicate char, please out put all the derangement
     public List<char[]> getDerangement(char[]){}


     Three sum with duplicate, pirnt all indexes, for example:
     0 2 -2 -2
     (0)(1)(2)(3)
     print (0, 1, 2) (0, 1, 3) . 1point 3acres 璁哄潧
     can you do it use n^2 (or less) time complexity with as less space as possible?
     public List<List<Integer>> threeSum(int[] nums) {}
     . 1point3acres.com/bbs
     Given an array of integers:
     1. rearrange the array such that all non-zero members appear on the left of the array (order is not important)
     2. return the number of non-zero members
     e.g. [1,2,0,5,3,0,4,0] => [1,2,5,3,4,0,0,0] and return 5. The non-zero array members can be in any order.

     . 鍥磋鎴戜滑@1point 3 acres
     Given an array of task and k wait time for which a repeated task
     needs to wait k time to execute again. please rearrange the task
     sequences to minimize the total time to finish all the tasks.
     Example
     Tasks = 111222, k = 2,
     One possible task sequence is
     12_12_12,
     another possible task sequence is 21_21_21
     thus you shoud return 8
     public int getMiniTime(int[] nums, int k){
     }
     follow up, output one of the sequence 12_12_12, or 21_21_21. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴


     Given an unsorted array, sort it in such a way that the first
     element is the largest value, the second element is the smallest,
     the third element is the second largest element and so on.
     [2, 4, 3, 5, 1] -> [5, 1, 4, 2, 3]
     can you do it without using extra space . Waral 鍗氬鏈夋洿澶氭枃绔�,
     public void sortAlternate(int[] nums){}
     . Waral 鍗氬鏈夋洿澶氭枃绔�,

     Given a number of tasks (T) and servers (S), find out if the tasks can be accommodated on the servers. Each Task has a number of Units and each server has a number of Slots on which Units can run.
     The only condition is that two Units of the same Task "cannot" run on the same Server.
     Servers
     S[0] = "SS1", "SS2", "SS3", SS4 //Slots // 4 -> 3 -> 2 -> 1 .鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
     S[1] = "SS1", "SS2" // 2 -> 1 -> 0 -> false . visit 1point3acres.com for more.
     S[2] = "SS1", "SS2", SS3, SS4, SS5 // 5 -> 4 -> 3 -> 2
     S[3] = "SS1", "SS2", SS3, SS4, SS5 // 5 -> 4 -> 3 -> 2 . 1point3acres.com/bbs
     Example:
     S[0] = 4 . visit 1point3acres.com for more.
     S[1] = 3
     S[2] = 5
     S[3] = 5
     ...
     Tasks
     T[0] = U0, U1, U2, U3 //Tasks . more info on 1point3acres.com
     T[1] = U0, U1
     T[2] = U0, U1, U2 . 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
     ...
     . from: 1point3acres.com/bbs
     Example:
     T[0] = 4
     T[1] = 2 . more info on 1point3acres.com
     T[2] = 3
     . From 1point 3acres bbs
     implement . 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
     boolean boolean CanRunTasks(S[], T[]){
     }. 鍥磋鎴戜滑@1point 3 acres

     . Waral 鍗氬鏈夋洿澶氭枃绔�,
     Given some email ids, and a similarity function which says whether two email ids are similar, determine all the sets of email ids that are similar to each other.. 1point 3acres 璁哄潧


     You have a string consisting of open and closed parentheses, but parentheses may be imbalanced.
     Make the parentheses balanced and return the new string.

     . from: 1point3acres.com/bbs
     Given an array of integers greater than zero, find if it is possible to split it in two (without reordering the elements), such that the sum of the two resulting arrays is the same. Print the resulting arrays.


     There is a bunch of tasks, each have different time to complete, task is independent, and then there are some workers, . From 1point 3acres bbs
     How to allocate tasks to these workers to minimize the total time to complete all the task. The tasks can be randomly picked from the task list.
     Example .鏈枃鍘熷垱鑷�1point3acres璁哄潧
     Task: 2,2,3,7, 1
     Worker: 2.
     Return 8, because the first worker can work on the first three tasks : 2 + 2 + 3 = 7, and the second worker can work on the last two tasks : 7 + 1 = 8, so the total time to finish all the task is 8.
     public int getMini(int[] tasks, int k)


     We have a List of FlightRoute
     public static class FlightRoute {
     String from;
     String to;
     int time;. Waral 鍗氬鏈夋洿澶氭枃绔�,
     ....
     }
     and write a function to find Shortest Path: findShorestPath(String start, String end, List<FlightRoute>routes). 1point 3acres 璁哄潧
     .鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
     . Waral 鍗氬鏈夋洿澶氭枃绔�,

     Iterate over a singly linked list backwards. Call print on each node.
     Example: The list A->B->C should print as .鏈枃鍘熷垱鑷�1point3acres璁哄潧
     "C B A"
     class Node {
     public Node next;
     public String value;
     }
     There are 4 solutions
     1) recursive . from: 1point3acres.com/bbs
     2) iterative with O(n) memory
     3) iterative with O(1) memory and O(n2) runtime
     4) iterative with O(1) memory and O(n) runtime (for this solution the initial list may be modified) . 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
     Explain all 4 solutions and write the code for solutions 3 and 4


     You are given an array of integers. . visit 1point3acres.com for more.
     Write an algorithm that brings all nonzero elements to the left of the array, and returns the number of nonzero elements.
     The algorithm should operate in place, i.e. shouldn't create a new array.
     The order of the nonzero elements does not matter. The numbers that remain in the right portion of the array can be anything.
     Example:
     given the array [ 1, 0, 2, 0, 0, 3, 4 ],
     a possible answer is [ 4, 1, 3, 2, ?, ?, ? ], 4 non-zero elements, where "?" can be any number.
     Code should have good complexity and minimize the number of writes to the array.-google 1point3acres



     Given:
     a encoded to 1 . 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
     b encoded to 2
     ....
     z encoded to 26
     You can translate a number to a string: . 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
     '123' can be translated to 'abc'
     but also can be translated to 'aw','lc' which gives 3 total translations
     '12' can be translated to 'ab' and 'l' -> 2 translations
     Write a function to get the number of valid combinations from a number like '123123123'

     . 鍥磋鎴戜滑@1point 3 acres
     You have a string of numbers, i.e. 123. You can insert a + or - sign in front of ever number, or you can leave it empty. Find all of the different possibilities, make the calculation and return the sum.
     For example;
     +1+2+3 = 6
     +12+3 = 15
     +123 = 123
     +1+23 = 24
     ...
     -1-2-3 = 6
     ... 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�.
     Return the sum of all the results.


     Given the newest 100 entries of a person's facebook newsfeed. How would you rank the entries. The (for the user) most important ones should be ranked first. Which features would you use and how do you train/improve your model (machine learning)?


     Design a algorithm to initialize the board of Candy Crush Saga. With M x N board, Q types of candies. (Rules: no 3 for run after initialization, must contain at least one valid move at the beginning)

     . visit 1point3acres.com for more.
     Q: Weighted meeting room
     Given a series of meetings, how to schedule them. Cannot attend more than a meeting at the same time. Goal is to find maximum weight subset of mutually non-overlap meetings.
     class Meeting:. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
     def __init__(self):
     self.startTime. 鍥磋鎴戜滑@1point 3 acres
     self.endTime
     self.weight


     Given preorder traversal [5,3,2,4,8,7,9] of a BST, how do we identify the leaf nodes without building the tree ?
     . 1point 3acres 璁哄潧

     Given estimated stock quotes, in an array, print the maximum profit from a buy and sell. i.e [19, 22, 15, 35, 40, 10, 20] would show a profit of 25(40 -15). The sale must come after the buy. Solve this in O(N) time.


     Given many coins of 3 different face values, print the combination sums of the coins up to 1000. Must be printed in order.
     eg: coins(10, 15, 55) -google 1point3acres
     print: . 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
     10
     15 . visit 1point3acres.com for more.
     20
     25 . 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
     30
     .
     .
     . .鏈枃鍘熷垱鑷�1point3acres璁哄潧
     1000

     . Waral 鍗氬鏈夋洿澶氭枃绔�,
     Leetcode 314: Binary tree vertical order traversal. visit 1point3acres.com for more.


     Given an input string "aabbccba", find the shortest substring from the alphabet "abc".
     In the above example, there are these substrings "aabbc", "aabbcc", "ccba" and "cba". However the shortest substring that contains all the characters in the alphabet is "cba", so "cba" must be the output. .鏈枃鍘熷垱鑷�1point3acres璁哄潧
     Output doesnt need to maintain the ordering as in the alphabet.
     Other examples:
     input = "abbcac", alphabet="abc" Output : shortest substring = "bca".


     Generate square of numbers in an array example [1,3,5] should come out as [1,9,25].


     Given an Array of N elements and Integer K, write a function that returns true if the sum of any 2 elements of Array is K, false otherwise.


     Write code to decode strings. For example, String str = "3[a2[bd]g4[ef]h]", the output should be "abdbdgefefefefhabdbdgefefefefhabdbdgefefefefh".


     Given an array of positive integers and a target total of X, find if there exists a contiguous subarray with sum = X
     [1, 3, 5, 18] X = 8 Output: True
     X = 9 Output: True
     X = 10 Output: False
     X = 40 Output :False
     */
}
