```java
class Solution {
    /**
     * Given nums = [2, 7, 11, 15], target = 9,
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     * */
    public int[] twoSumA(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode pre = new ListNode(0); // set pre head
        ListNode d = pre;
        int sum = 0; // the sum of two nodes
        while (c1 != null || c2 != null) { // traverse longer list
            if (c1 != null) { // add one list 
                sum += c1.val;
                c1 = c1.next; // move on 
            }
            if (c2 != null) { // add another list
                sum += c2.val;
                c2 = c2.next; // move on 
            }
            // build next node
            d.next = new ListNode(sum % 10); // digit for current node
            sum /= 10; // carry
            d = d.next;
        }
        if (sum == 1)
            d.next = new ListNode(1); // note that can have carry at the last digit
        return pre.next;
    }

    /** Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7*/
    public static ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        return list.val == 0 ? list.next : list;
    }

    /**
     * "abcabcbb" is "abc", which the length is 3. 
     * For "bbbbb" the longestsubstring is "b", with the length of 1.
     * */
    public static int LongestSubstringWithoutRepeatingCharacters(String s) {
        if (s.length() == 0)
            return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    /**
     * nums1 = [1, 3]  nums2 = [2]  The median is 2.0
     * nums1 = [1, 2]  nums2 = [3, 4] The median is (2 + 3)/2 = 2.5
     */
    public double medianofTwoSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n > m)
            return medianofTwoSortedArrays(B, A); // shorter array first
        int k = (n + m - 1) / 2; // mid position
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        // find A[l] > B[k - l]
        while (l < r) {
            int midA = l + (r - l) / 2; // A[i], avoid overflow
            int midB = k - midA; // B[j - 1]
            if (A[midA] < B[midB])
                l = midA + 1; // i + 1, r
            else
                r = midA; // l, i
        }
        // A[l-1], A[l], B[k-l], and B[k-l+1] 
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if ((n + m) % 2 == 1)
            return (double) a; // odd
        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0; // even
    }


    public String longestPalindromeC(String s) {
        if (s == null || s.length() == 0)
            return "";
        String longest = s.substring(0, 1);
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            String s1 = expandAroundCenter(s, i, i);
            if (s1.length() > longest.length())
                longest = s1;
            String s2 = expandAroundCenter(s, i, i + 1);
            if (s2.length() > longest.length())
                longest = s2;
        }
        return longest;
    }

    private String expandAroundCenter(String s, int i, int j) {
        int l = i;
        int r = j;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r); // note the range is from l + 1 to r - 1
    }

    /**
     * match("aa","a") → false  
     * match("aa","aa") → true
     * match("aaa","aa") → false
     * match("aa", "a*") → true
     * match("aa", ".*") → true
     * match("ab", ".*") → true
     * match("aab", "c*a*b") → true*/
    public static boolean regularExpressionMatching(String s, String p) {
        if (s == null && p == null)
            return true;
        if (s == null || p == null)
            return false;
        if (p.length() == 0)
            return s.length() == 0;
        //p's length 1 is special case
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
                return false;
            return regularExpressionMatching(s.substring(1), p.substring(1));
        } else {
            int len = s.length();
            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (regularExpressionMatching(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }

    /** 
     * Given n non-negative integers a1, a2, ..., an, where each represents a point
     * at coordinate (i, ai). n vertical lines are drawn such that the two
     * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
     * with x-axis forms a container, such that the container contains the most water.
     * Input: [1,8,6,2,5,4,8,3,7] Output: 49  Note: You may not slant the container.
     * */
    public int containerWithMostWater(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return max;
    }

    /**
     *  Elements in a triplet (a,b,c) must be in <strong>non-descending</strong> order. (ie, a ≤ b ≤ c)
     * The solution set must not contain <strong>duplicate</strong> triplets.
     * For example, given array S = {-1 0 1 2 -1 -4}, solution set is: (-1, 0, 1) (-1, -1, 2)*/
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3)
            return result;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // Skip same results
            int target = 0 - nums[i];
            int j = i + 1, k = len - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j] == nums[j + 1])
                        j++; // Skip same results
                    while (j < k && nums[k] == nums[k - 1])
                        k--; // Skip same results
                    j++;
                    k--;
                } else if (nums[j] + nums[k] < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                //看不懂这里
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    /** 
     * Given: 1->2->3->4->5, and n = 2.
     * After removing the second node from the end, the linked list becomes
     * 1->2->3->5.*/
    public ListNode removeNthNodeFromEndofList(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode p1 = pre;
        ListNode p2 = pre;
        int i = 0;
        while (i < n) {
            p2 = p2.next;
            i++;
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return pre.next;
    }

    /**
     * "{[]}"- true
     * */
    public static boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && validParentheses(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean validParentheses(char a, char b) {
        return (a == '[' && b == ']') ||
                (a == '{' && b == '}') ||
                (a == '(' && b == ')');
    }

    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        // next node should be the result of comparison
        if (l1.val < l2.val) {
            l1.next = mergeTwoSortedLists(l1.next, l2); // notice l1.next
            return l1;
        } else {
            l2.next = mergeTwoSortedLists(l1, l2.next); // notice l2.next
            return l2;
        }
    }
    //迭代
    public ListNode mergeTwoSortedListsb(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        helper.next = l1;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ListNode next = l2.next;
                l2.next = pre.next;
                pre.next = l2;
                l2 = next;
            } else {
                l1 = l1.next;
            }
            pre = pre.next;
        }
        if (l2 != null) {
            pre.next = l2;
        }
        return helper.next;
    }
    
    public static List<String> generateParenthesisa(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public static void backtrack(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }
        if (open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }

    /**
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * Output: 1->1->2->3->4->4->5->6
     * */
    public ListNode mergeKSortedLista(List<ListNode> lists) {
        if (lists == null || lists.size() == 0)
            return null;
        // Build priority queue
        Queue<ListNode> queue = new PriorityQueue<>(lists.size(), Comparator.comparingInt(n1 -> n1.val));
        for (ListNode n : lists)
            if (n != null)
                queue.add(n);
        ListNode dummy = new ListNode(0); // set dummy head
        ListNode tail = dummy;
        while (!queue.isEmpty()) { // build next
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null)   ///?????????
                queue.add(tail.next);
        }
        return dummy.next;
    }
    
    /** Given 1->2->3->4, you should return the list as 2->1->4->3. You may not modify the values
    * in the list, only nodes itself can be changed.*/
    //递归
    public ListNode swapNodesinPairs(ListNode head) {
        if ((head == null) || (head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapNodesinPairs(head.next.next);
        n.next = head;
        return n;
    }
    //
    public ListNode swapNodeinPairsa(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur != null && cur.next != null && cur.next.next != null) {
            cur.next = swap(cur.next, cur.next.next);
            cur = cur.next.next;
        }
        return dummy.next;
    }

    private static ListNode swap(ListNode next1, ListNode next2) {
        next1.next = next2.next;
        next2.next = next1;
        return next2; // return latter node 
    }

    /**
     * Given this linked list: 1->2->3->4->5
     * For k = 2, you should return: 2->1->4->3->5
     * For k = 3, you should return: 3->2->1->4->5
     * Tags: Linked list 尾插法
     * */
    public ListNode reverseNodesInKGroupa(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseNodesInKGroupa(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

    /** 
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * 思路： 如果一个数列是降序排列的（从后往前看就是升序） 那它就是不能再置换了 只能将他从新按升序排列
     * 所以每一遍都从后往前 一共扫描三遍
     * 第一遍先找到那个打破升序排列的数字(从后往前)
     * 第二遍从后往前找到第一个比打破升序的数字大的数字 然后置换二者
     * 第三遍就是把从打破升序的位置之后剩下的数列按升序排列（从前往后看） 这样的数字才最接近之前的数字
     * */
    public static void nextPermutation(int[] num) {
        if (num == null || num.length == 0)
            return;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                int j = num.length - 1;
                for (; j > i; j--)
                    if (num[j] > num[i])
                        break;
                swap(num, i, j);
                reverse(num, i + 1);
                return;
            }
        }
        reverse(num, 0);
        return;
    }

    private static void swap(int[] num, int i, int j) {
        int t = num[i];
        num[i] = num[j];
        num[j] = t;
    }

    private static void reverse(int[] num, int s) {
        int e = num.length - 1;
        while (s < e) {
            swap(num, s, e);
            s++;
            e--;
        }
    }

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    max = stack.isEmpty() ?
                            Math.max(max, i - start + 1) : Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    /** For "(()", the longest valid parentheses substring is "()", which has length  * = 2.
     * Another example is ")()())", where the longest valid parentheses substring
     * is "()()", which has length = 4.
     * Follow up:What if there are curly bracs and brakets as well? {} []?
     * */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        LinkedList<Integer> stack = new LinkedList<>();
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i + 1;
                } else {
                    stack.pop();
                    max = stack.isEmpty() ?
                            Math.max(max, i - start + 1) : Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
    

    public int searchinRotatedSortedArray(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right)
            return -1;
        int mid = left + (right - left) / 2;
        if (target == nums[mid])
            return mid;
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                return binarySearch(nums, left, mid - 1, target);
            }
            else {
                return binarySearch(nums, mid + 1, right, target);
            }
        }
        else {
            if (nums[mid] < target && target <= nums[right]) {
                return binarySearch(nums, mid + 1, right, target);
            }
            else {
                return binarySearch(nums, left, mid - 1, target);
            }
        }
    }

    public static int searchinRotatedSortedArrayb(int[] A, int target) {
        if (A == null || A.length == 0)
            return -1;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target)
                return m;
            if (A[m] >= A[l]) { // left half sorted
                if (target >= A[l] && target < A[m]) {
                    r = m - 1;
                }
                else
                    l = m + 1;
            }
            else { // right half sorted
                if (target > A[m] && target <= A[r]) {
                    l = m + 1;
                }
                else
                    r = m - 1;
            }
        }
        return -1;
    }


/** 
*Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            if (nums[mid] == target) idx = mid;
        }
        return idx;
    }

    private int findLast(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            if (nums[mid] == target) idx = mid;
        }
        return idx;
    }


    /** 
     * Here are few examples.
     * [1,3,5,6], 5 → 2
     * [1,3,5,6], 2 → 1
     * [1,3,5,6], 7 → 4
     * [1,3,5,6], 0 → 0
     */
    public int searchInsertC(int[] A, int target) {
        if (A == null || A.length == 0)
            return 0;
        return searchInsert(A, target, 0, A.length - 1);
    }

    public int searchInsert(int[] A, int target, int start, int end) {
        int mid = (start + end) / 2;
        if (target == A[mid])
            return mid;
        else if (target < A[mid])
            return start < mid ? searchInsert(A, target, start, mid - 1) : start;
        else
            return end > mid ? searchInsert(A, target, mid + 1, end) : (end + 1);
    }

    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0)
            return 0;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target)
                return m;
            else if (A[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return l;
    }

   /**
     * Given a set of candidate numbers (C) and a target number (T), find all
     * unique combinations in C where the candidate numbers sums to T.
     * The same repeated number may be chosen from C <strong>unlimited</strong> number of times.
     * Note: All numbers (including target) will be positive integers.
     * Elements in a combination(a1, a2, … , ak) must be in non-descending order.
     * (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     * For example, given candidate set 2,3,6,7 and target 7,
     * A solution set is:[7] [2, 2, 3]
     * Tags: Backtracking
     */
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList,
                                  int[] nums, int remain, int start) {
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            // not i + 1 because we can reuse same elements
            backtrack(list, tempList, nums, remain - nums[i], i);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * Given an unsorted integer array, find the first missing positive integer. HARD TODO
     * For example,
     * Given [1,2,0] return 3,and [3,4,-1,1] return 2.
     * Your algorithm should run in O(n) time and uses constant space.
     */
    public static int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0)
            return 1;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int num = A[i];
            while (A[i] <= n && A[i] > 0 && A[num - 1] != num) {
                A[i] = A[num - 1];
                A[num - 1] = num;
                num = A[i];
            }
        }
        for (int i = 0; i < n; i++)
            if (A[i] != i + 1)
                return i + 1;
        return n + 1; // nothing in middle losing, return largest
    }
    
    /**
     * HARD
     * Given n non-negative integers representing an elevation map where the width
     * of each bar is 1, compute how much water it is able to trapRainWater after raining.
     * For example,
     * Given [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1], return 6.
     * Tags: Array, Stack, Two pointers
     */
    public int trapRainWater(int[] A) {
        int a = 0;
        int b = A.length - 1;
        int max = 0;
        int leftmax = 0;
        int rightmax = 0;
        while (a <= b) {
            leftmax = Math.max(leftmax, A[a]);
            rightmax = Math.max(rightmax, A[b]);
            if (leftmax < rightmax) {
                max += (leftmax - A[a]); // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            } else {
                max += (rightmax - A[b]);
                b--;
            }
        }
        return max;
    }


    /**
    Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
    *Each element in the array represents your maximum jump length at that position.
    *Your goal is to reach the last index in the minimum number of jumps.
    *You can assume that you can always reach the last index.
    *Input: nums = [2,3,1,1,4] Output: 2
    *Input: nums = [2,3,0,1,4]Output: 2
    */
    public int jumpGameII(int[] nums) {
        int end = 0;
        int mx = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            mx = Math.max(mx, i + nums[i]);
            if (i == end) {
                end = mx;
                ++steps;
            }
        }
        return steps;
    }

    /**
     * [1,2,3] have the following permutations:
     * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     * Tags: Backtracking
     */
    public static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i]))
                continue; // element already exists, skip
            tempList.add(nums[i]);
            backtrack(list, tempList, nums);//!!
            tempList.remove(tempList.size() - 1);
        }
    }


    /**
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * Follow up:
     * Could you do this in-place?
     * Get the length of matrix
     * Do level by level, each level edge by edge
     * In-place solutions overwrites original matrix matrix[i][j] = matrix[n-1-j][i]"
     */
    public void rotateImage(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int tmp = matrix[i][j]; // save in tmp var
                matrix[i][j] = matrix[n - j - 1][i]; // first col
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]; // last row
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]; // last col
                matrix[j][n - i - 1] = tmp;
            }
        }
    }


  /**
     * Given an array of strings, group anagrams together.
     * given: ["eat", "tea", "tan", "ate", "nat", "bat"],Return:
     * [
     * ["ate", "eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * Note:
     * For the return value, each inner list's elements must follow the lexicographic order.
     * All inputs will be in lower-case.
     * The signature of the function had been updated to return list<list<string>> instead of list<string>,
     * as suggested here. If you still see your function signature return a list<string>,
     * please click the reload button  to reset your code definition.
     * 使用哈希表 key是每个字符串排序过的 所以anagrams会有相同的key value就是字符串本身的值
     * 然后遍历字符串数组,把他们都装进字典 这样每个字典key下面就是一个anagrams group,再把每个group排序后返回即可
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }


    /**
     * Find the contiguous subarray within an array (containing at least one（median）
     * number) which has the largest sum.
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     * More practice:
     * If you have figured out the O(n) solution, try coding another solution using
     * the divide and conquer approach, which is more subtle.
     * Tags: Divide and Conquer, Array, DP
     * <p>
     * DP, O(n) Time, O(1) Space
     * If A[i] < 0 && currentMax + A[i] < 0, should recalculate max
     * If A[i] < 0 && currentMax + A[i] >= 0, continue
     * currentMax = max(currentMax + A[i], A[i])
     * maxSubArr = max(currentMax, maxSubArr)
     */
    public static int maximumSubarray(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int curMax = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) { // note that i starts from 1
            curMax = Math.max(curMax + A[i], A[i]);
            max = Math.max(curMax, max);
        }
        return max;
    }

    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jumpGame2 length at that position.
     * Determine if you are able to reach the last index.
     * A = [2,3,1,1,4], return true. A = [3,2,1,0,4], return false.
     * Tags: Array, Greedy, DP
     */
    public boolean jumpGame(int[] A) {
        if (A == null || A.length == 0)
            return false;
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (i > max) {    //?????
                return false;
            }
            max = Math.max(A[i] + i, max);
        }
        return true;
    }

    public boolean jumpGameb(int[] A) {
        if (A == null || A.length == 0)
            return false;
        int reach = 0;
        for (int i = 0; i <= reach && i < A.length; i++) {
            reach = Math.max(A[i] + i, reach);
        }
        if (reach < A.length - 1)
            return false;
        return true;
    }

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     * Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
     * Tags: Array, Sort
     * Sort and merge, O(nlogn)
     * Sort the intervals according to their start value
     * Go through the intervals and update last interval
     * If last interval in result overlap with current interval
     * Remove last interval and add new interval with updated end value
     * Which is the bigger of last.end and i.end
     */
    public List<Interval> MergeIntervals(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0)
            return res;
        Collections.sort(intervals, Comparator.comparingInt(i2 -> i2.start));
        for (Interval i : intervals) {
            if (res.isEmpty())
                res.add(i); // first interval
            else {
                Interval last = res.get(res.size() - 1); // get last interval
                if (last.end >= i.start) { // overlap
                    res.remove(last);
                    res.add(new Interval(last.start, Math.max(last.end, i.end))); // extend end
                } else
                    res.add(i); //no overlap
            }
        }
        return res;
    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot
     * is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there? Note: m and n will be at most 100.
     * Tags: Array, DP
     */
         //complexity O(n*m)
    public static int uniquePaths(int m, int n) {
        Integer[][] map = new Integer[m][n];
        for (int i = 0; i < m; i++) {
            map[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            map[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] = map[i - 1][j] + map[i][j - 1];
            }
        }
        return map[m - 1][n - 1];
    }

    static int[][] paths = new int[101][101];

    public static int uniquePathsDP(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        if (m == 1 || n == 1)
            return 1;
        if (paths[m][n] == 0)
            paths[m][n] = uniquePathsDP(m - 1, n) + uniquePathsDP(m, n - 1);
        return paths[m][n];
    }

    /**
     * Given a m x n grid filled with non-negative numbers,find a path from top left to bottom right
     * which minimizes the sum of all numbers along its path.Note: You can only move either down or right at any point in time.
     * Input:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]   Output: 7
     * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
     * Tags: Array, DP
     */
    public static int minimumPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * Note: Given n will be a positive integer.
     * Input: 2 Output: 2 Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Input: 3 Output: 3 Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     */
    public static int climbStairs(int n) {
        if (n < 0)
            return -1;
        if (n == 0 || n == 1)
            return 1;
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i < cache.length; i++) {
            cache[i] = cache[i - 1] + cache[i - 2]; // only need the last 2
        }
        return cache[n];
    }

    /**
     * Given two words word1 and word2, find the minimum number of steps required
     * to zigZagConversion word1 to word2. (each operation is counted as 1 step.)
     * You have the following 3 operations permitted on a word:
     * a) Insert a character
     * b) Delete a character
     * c) Replace a character
     * Input: word1 = "horse", word2 = "ros" Output: 3
     * Explanation: horse -> rorse (replace 'h' with 'r')
     * rorse -> rose (remove 'r')
     * rose -> ros (remove 'e')
     * Input: word1 = "intention", word2 = "execution" Output: 5
     * Explanation: intention -> inention (remove 't')
     * inention -> enention (replace 'i' with 'e')
     * enention -> exention (replace 'n' with 'x')
     * exention -> exection (replace 'n' with 'c')
     * exection -> execution (insert 'u')
     * Tags: DP, String
     */
    public static int editDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] d = new int[m + 1][n + 1];
        d[0][0] = 0;
        for (int i = 1; i < m + 1; i++)
            d[i][0] = i;
        for (int j = 1; j < n + 1; j++)
            d[0][j] = j;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                d[i][j] = Math.min(Math.min(d[i][j - 1] + 1, d[i - 1][j] + 1),
                        word1.charAt(i - 1) == word2.charAt(j - 1) ? d[i - 1][j - 1] : d[i - 1][j - 1] + 1);
            }
        }
        return d[m][n];
    }

    public static int editDistanceOptimal(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        int m = word1.length();
        int n = word2.length();
        int[] d = new int[n + 1];
        d[0] = 0;
        for (int j = 1; j < n + 1; j++)
            d[j] = j;
        for (int i = 1; i < m + 1; i++) {
            int prev = d[0];
            d[0] += 1;
            for (int j = 1; j < n + 1; j++) {
                int temp = d[j];
                d[j] = Math.min(Math.min(d[j - 1] + 1, d[j] + 1),
                        word1.charAt(i - 1) == word2.charAt(j - 1) ? prev : prev + 1);
                prev = temp;
            }
        }
        return d[n];
    }


    /**
     * * Write an efficient algorithm that searches for a value in an m x n matrix.
     * This matrix has the following properties: Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * Given target = 3, return true.
     */
    public boolean searcha2DMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int l = 0;
        int r = matrix.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[mid][0] == target)
                return true;
            if (matrix[mid][0] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int row = r; //先确定行再确定列
        if (row < 0)
            return false;
        l = 0;
        r = matrix[0].length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[row][mid] == target)
                return true;
            if (matrix[row][mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    /**
     * Given an array with n objects colored red, white or blue, sort them so that
     * objects of the same color are adjacent, with the colors in the order red,white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red,white, and blue respectively.
     * Note:You are not supposed to use the library's sort function for this problem.
     * Follow up:
     * A rather straight forward solution is a two-pass algorithm using counting sort.
     * First, iterate the array counting number of 0's, 1's, and 2's, then
     * overwrite array with total number of 0's, then 1's and followed by 2's.
     * Could you come up with an one-pass algorithm using only constant space?
     * Tags: Array, Two Pointers, Sort
     */
    public void sortColors(int[] A) {
        if (A == null || A.length == 0)
            return;
        int i = -1; // red count, start of white
        int j = -1; // red + white count, start of blue
        for (int k = 0; k < A.length; k++) {
            int v = A[k];
            A[k] = 2; // overwrite as blue
            if (v == 0) {
                A[++j] = 1; // write white first, then red
                A[++i] = 0; // overwrite 1 when there is no white yet
            } else if (v == 1)
                A[++j] = 1;
        }
    }

    /**
     * HARD Given a string S and a string T, find the minimum window in S which will
     * contain all the characters in T in complexity O(n).
     * S = "ADOBECODEBANC"  T = "ABC"  Minimum window is "BANC".
     * Note: If there is no such window in S that covers all characters in T, return the empty string "".
     * If there are multiple such windows,you are guaranteed that there will always be only one unique minimum window in S.
     * Tags: Hashtable, Two Pointers, String
     * 整体思路就是用两个指针 然后首先往前找到第一个window 然后就缩小start指针 然后再往前继续找
     * count是用来计算现在的窗口缺了几种T的字母 所以当一种都不缺的时候 就是找到了一个window 这时候就要开始进入while 移动start
     * 注意这里移动的时候 dic[s[start]]有可能是负数 就是这个字母出现的次数 多余T的次数  比如 AABC ABC 所以这种情况
     * start应该是移动到B才算停 因为这时候count才为1 表示缺少了一种字母
     * 但是移动的过程中每次都要计算一遍 end-start 这样才能的出来ABC
     */
    public static String minimumWindowSubstring(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, 0);
        for (char c : t.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return "";
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map.get(c1) > 0)
                counter--;
            map.put(c1, map.get(c1) - 1);
            end++;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);
                if (map.get(c2) > 0)
                    counter++;
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }


     /**
     * Given a set of distinct integers, S, return all possible subsets.
     * Note:Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     * If S = [1,2,3], a solution is:[[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
     * Remember the start position and do backtracking
     */
    public static List<List<Integer>> subsetsB(int[] s) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(s);
        subsetsB(s, 0, new ArrayList<>(), res);
        return res;
    }

    public static void subsetsB(int[] s, int start, List<Integer> set, List<List<Integer>> result) {
        result.add(new ArrayList<>(set));
        for (int i = start; i < s.length; i++) {
            set.add(s[i]); // with i
            subsetsB(s, i + 1, set, result); // DFS
            set.remove(set.size() - 1); // remove last element
        }
    }

    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring. The
     * same letter cell may not be used more than once.
     * Given board =
     * [
     * ["ABCE"],
     * ["SFCS"],
     * ["ADEE"]
     * ]
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     * Tags: Array, Backtracking
     * Use boolean array to remember whether a word is used Traverse each position and do DFS
     * 总的时间复杂度最坏是O(m^2*n^2)
     * 这道题很容易感觉出来是图的题目，其实本质上还是做深度优先搜索。基本思路就是从某一个元素出发，
     * 往上下左右深度搜索是否有相等于word的字符串。这里注意每次从一个元素出发时要重置访问标记
     * （也就是说虽然单次搜索字符不能重复使用，但是每次从一个新的元素出发，字符还是重新可以用的）。
     * 深度优先搜索的算法就不再重复解释了，不了解的朋友可以看看wiki - 深度优先搜索。我们知道一次搜索的复杂度是O(E+V)，
     * E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n)量级的（因为一个顶点有固定上下左右四条边）。
     * 加上我们对每个顶点都要做一次搜索， 所以总的时间复杂度最坏是O(m^2*n^2)，空间上就是要用一个数组来记录访问情况，所以是O(m*n).
     * http://blog.csdn.net/linhuanmars/article/details/24336987
     */
         public static boolean wordSearcha(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null)
            return false;
        if (word.length() == 0)
            return true;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == word.charAt(0)) { // match the first char
                    if (dfs(board, i, j, word, 0))
                        return true;
                }
        return false;
    }

    /**
     * Remember position in board
     * Remember position in matched word
     */
    public static boolean dfs(char[][] board, int i, int j, String word, int n) {
        if (word.length() == n)
            return true;
        // outside board or doesn't match
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(n))
            return false;
        board[i][j] = '#'; // mark
        // searchinRotatedSortedArrayb 4 connectivity
        boolean res = dfs(board, i - 1, j, word, n + 1) || dfs(board, i + 1, j, word, n + 1)
                || dfs(board, i, j - 1, word, n + 1) || dfs(board, i, j + 1, word, n + 1);
        board[i][j] = word.charAt(n);// reset mark
        return res;
    }

    /**
     * Given n non-negative integers representing the histogram's bar height where HARD
     * the width of each bar is 1, find the area of largest rectangle in the histogram.
     * Above is a histogram where width of each bar is 1, given height =[2,1,5,6,2,3].
     * The largest rectangle is shown in the shaded area, which has area = 10 unit.
     * Given height = [2,1,5,6,2,3],return 10. Tags: Array, Stack
     * 主要思路可以看geeksforgeeks
     * 首先 主体思路就是对于每个bar 我们都去求出以当前的bar为最低的bar的面积 然后所有这些面积的最大值就是结果
     * 在求以当前bar为最低bar的面积的时候 最难的就是要确定这个最低bar的左边界还有右边界
     * stack解法就是巧妙地解决了这个问题
     * 最重要的 stack里存的是索引 不是值
     * stack里存的的都是递增的序列 如果碰到小于栈顶的
     * 那么 就计算栈顶的元素的面积 这个元素的面积  左边界就是它自己  右边界就是这个小于它的元素
     * 然后弹出  然后如果栈顶的还是大 那么继续计算  因为存的是索引  所以宽度计算都是正确的
     * Only height is smaller do update happens
     * Stack for indices
     * add a zero height into the group
     */
    public static int largestRectangleInHistogramb(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        height = Arrays.copyOf(height, height.length + 1); // add a zero
        int max = 0;
        Stack<Integer> s = new Stack<Integer>(); // store indices
        for (int i = 0; i < height.length; i++) {
            while (!s.isEmpty() && height[i] < height[s.peek()]) {//update when current height is smaller
                int h = height[s.pop()];
                int w = (s.isEmpty() ? i : i - s.peek() - 1);
                max = Math.max(max, h * w);
            }
            s.push(i); // push index into stack
        }
        return max;
    }


    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
     * Tags: Array, Hashtable, Stack, DP
     * 不懂啊
     * 假设我们把矩阵沿着某一行切下来，然后把切的行作为底面，将自底面往上的矩阵看成一个直方图（histogram）。
     * 直方图的中每个项的高度就是从底面行开始往上1的数量。根据Largest Rectangle in Histogram
     * 我们就可以求出当前行作为矩阵下边缘的一个最大矩阵。
     * 接下来如果对每一行都做一次Largest Rectangle in Histogram，
     * 从其中选出最大的矩阵，那么它就是整个矩阵中面积最大的子矩阵。
     * 所以完成对一行为底边的矩阵求解复杂度是O(n+n)=O(n)。接下来对每一行都做一次，那么算法总时间复杂度是O(m*n)
     * row by row create a height integer array to bigger than column size
     * set last height to zero(out of bounds)
     * build new height on each row
     * use stack to store indices
     * update area according to largest rectangle in histogram
     */
    public int maximalRectangleA(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n + 1];
        height[n] = 0;
        int max = 0;
        for (int i = 0; i < m; i++) {
            Stack<Integer> s = new Stack<Integer>();
            for (int j = 0; j < n + 1; j++) {
                if (j < n) { // build height
                    height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
                }
                while (!s.isEmpty() && height[j] < height[s.peek()]) {
                    int h = height[s.pop()];
                    int w = (s.isEmpty() ? j : j - s.peek() - 1);
                    max = Math.max(max, h * w);
                }
                s.push(j);
            }
        }
        return max;
    }

    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * Given binary tree {1,#,2,3},
     * 1
     * \
     * 2
     * /
     * 3
     * return [1,3,2]. Note: Recursive solution is trivial, could you do it iteratively?
     * Tags: Tree, HashTable, Stack
     * The recursive solution is trivial. 递归法
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即O(logn)
     */
    public List<Integer> binaryTreeInOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            helper(root, result);
        }
        return result;
    }

    public void helper(TreeNode p, List<Integer> result) {
        if (p.left != null)
            helper(p.left, result);
        result.add(p.val);
        if (p.right != null)
            helper(p.right, result);
    }

    /**
     * 迭代法  时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     * Stack solution, O(n) Space
     * Use a stack to store TreeNodes
     * Go to left most and add each node
     * Pop the node from stack, add its value, and try to go right
     * Stop if stack is empty or node is null
     */
    public static List<Integer> binaryTreeInOrderTraversalb(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        while (!s.isEmpty() || root != null) {
            // check whether current node is null
            if (root != null) { // current node is not null
                s.push(root);
                root = root.left;
            } else { // current node is null, pop and go right
                root = s.pop();
                result.add(root.val); // visit()
                root = root.right;
            }
        }
        return result;
    }


    /**
     * Given n, how many structurally unique BST's (binary search trees) that store
     * values 1...n?
     * Given n = 3, there are a total of 5 unique BST's.
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     * Tags: Tree, DP
     * 和UniqueBST2 思路一样
     * 假设count(i)表示如果当前根是(i)那么一共有多少种BST组合
     * 那么count(i) = count(i-1) + count(n-i) 就是他的左子树的所有组合  加上他的右子树的所有组合
     * 而要求出所有的BST组合  那就是等于 count(1) + count(2) + …. count(n)然后就可以迭代求解
     * 设立一个rec数组来存每一个count(i) 然后大循环遍历1-n 小循环则求出当前的count(i)
     * 不要用迭代会超时
     */
    public static int uniqueBinarySearchTrees(int n) {
        if (n <= 0)
            return 0;
        int[] trees = new int[n + 1];
        trees[0] = 1; // initialize 0, only 1 type of tree
        for (int i = 1; i <= n; i++) // from 1 ~ n
            for (int j = 0; j < i; j++) // from 0 ~ i - 1
                trees[i] += trees[j] * trees[i - j - 1]; // note i-j-1 + j = i - 1
        return trees[n];
    }

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary searchinRotatedSortedArrayb trees.
     * Tags: Tree, DFS
     * 使用先序遍历
     * 如果访问了左节点  则当前点得值是左节点的最大值
     * 如果访问了右节点  则当前点得值是右节点的最小值
     * 然后递归更新最大最小值即可
     */
    public boolean isValidBSTA(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && root.val <= pre.val)
                return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    /**
     * 递归 算法的时间复杂度是树的遍历O(n)，空间复杂度同样与树遍历相同是O(logn)
     * Recursive, pre-order traversal
     * Check two symmetric nodes a time
     */
    private boolean symmetricTreea(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null)
            return n1 == n2;
        return n1.val == n2.val && helper(n1.left, n2.right) && helper(n1.right, n2.left);
    }

    /**
     * 非递归方法 是使用层序遍历来判断对称性质
     * Use a stack to store nodes in order
     * Then pop and compare
     * http://blog.csdn.net/linhuanmars/article/details/23072829
     */
    private boolean symmetricTreeb(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root.left);
        s.push(root.right);
        while (!s.isEmpty()) {
            TreeNode n1 = s.pop();
            TreeNode n2 = s.pop();
            if (n1 == null && n2 == null)
                continue;
            if (n1 == null || n2 == null || n1.val != n2.val)
                return false;
            s.push(n1.left); // add those pairs that should be symmetric
            s.push(n2.right);
            s.push(n1.right);
            s.push(n2.left);
        }
        return true;
    }

    private List<List<Integer>> binaryTreeLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<>();
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode n = queue.poll();
                curLevel.add(n.val);
                if (n.left != null)
                    queue.add(n.left);
                if (n.right != null)
                    queue.add(n.right);
            }
            res.add(curLevel);
        }
        return res;
    }

    private int maximumDepthofBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int left = maximumDepthofBinaryTree(root.left);
        int right = maximumDepthofBinaryTree(root.right);
        return Math.max(left, right) + 1;
    }

    public int maximumDepthofBinaryTreeb(TreeNode root) {
        if (root == null)
            return 0;
        int level = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curNum = 1; //num of nodes left in current level
        int nextNum = 0; //num of nodes in next level
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            curNum--;
            if (n.left != null) {
                queue.add(n.left);
                nextNum++;
            }
            if (n.right != null) {
                queue.add(n.right);
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                level++;
            }
        }
        return level;
    }

    public TreeNode constructBinaryTreefromPreorderandInorderTraversal(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null)
            return null;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR,
                            HashMap<Integer, Integer> map) {
        if (preL > preR || inL > inR)
            return null;
        TreeNode root = new TreeNode(preorder[preL]);
        int index = map.get(root.val);
        root.left = helper(preorder, preL + 1, index - inL + preL, inorder, inL, index - 1, map);
        root.right = helper(preorder, preL + index - inL + 1, preR, inorder, index + 1, inR, map);
        return root;
    }


    /**
    * Input: root = [1,2,5,3,4,null,6]
    * Output: [1,null,2,null,3,null,4,null,5,null,6]
    */
    public void flattenBinaryTreeToLinkedListd(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.empty()) {
            if (p.right != null) {
                stack.push(p.right);
            }
            if (p.left != null) {
                p.right = p.left;
                p.left = null;
            } else if (!stack.empty()) {
                TreeNode temp = stack.pop();
                p.right = temp;
            }
            p = p.right;
        }
    }

    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (ie, buy one
     * and sell one share of the stock), design an algorithm to find the maximum profit.
     * Tags: Array, DP
     */
    public static int bestTimeStock2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0; // need at least 2
        int max = 0;
        int min = prices[0]; // track the minimum of profit array before cur ele
        for (int i = 1; i < prices.length; i++) { // note that i starts from 1
            min = Math.min(min, prices[i]); // update min
            if (prices[i] > prices[i - 1])
                max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    public static int bestTimeStock(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int local = 0;
        int global = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            local = Math.max(local + prices[i + 1] - prices[i], 0);
            global = Math.max(local, global);
        }
        return global;
    }

    /**
     * HARD The path may start and end at any node in the tree.
     * A path from start to end, goes up on the tree for 0 or more steps, then goes
     * down for 0 or more steps. Once it goes down, it can't go up. Each path has a
     * highest node, which is also the lowest common ancestor of all other nodes on the path.
     * A recursive method maxPathDown(TreeNode node) (1) computes the
     * maximum path sum with highest node is the input node, update maximum if
     * necessary (2) returns the maximum sum of the path that can be extended to
     * input node's parent.
     * Given a binary tree, find the maximum path sum.
     * The path may start and end at any node in the tree.
     * Given the below binary tree,
     * 1
     * / \
     * 2   3 Return 6.
     * Tags: Tree, DFS
     */
    int maxValue;
    public int maxPathSuma(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    /**
     * Created by GAOSHANSHAN835 on 2016/1/8.
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find the length of shortest transformation sequence from beginWord to endWord, such that:
     * Only one letter can be changed at a time.
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * Note Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * Input: beginWord = "hit", endWord = "cog",wordList = ["hot","dot","dog","lot","log","cog"] Output: 5
     * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",return its length 5.
     * beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"] Output: 0
     * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     */
    public int wordLadderb(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));
        wordDict.add(endWord);
        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.word;
            if (word.equals(endWord)) {
                return top.numSteps;
            }
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }
                    String newWord = new String(arr);
                    if (wordDict.contains(newWord)) {
                        queue.add(new WordNode(newWord, top.numSteps + 1));
                        wordDict.remove(newWord);
                    }
                    arr[i] = temp;
                }
            }
        }
        return 0;
    }

    /**
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * Given [100, 4, 200, 1, 3, 2], The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
     * Your algorithm should run in O(n) complexity.
     * Tags: Array, HashTable
     * We can solve this problem in O(n) time using an Efficient Solution. The idea is to use Hashing.
     * We first insert all elements in a Hash. Then check all the possible
     * starts of consecutive subsequences. Below is complete algorithm.
     * 1) Create an empty hash.
     * 2) Insert all array elements to hash.
     * 3) Do following for every element arr[i]
     * ....a) Check if this element is the starting point of a subsequence.
     * To check this, we simply look for arr[i] - 1 in hash, if not found, then this is
     * the first element a subsequence.
     * If this element is a first element, then count
     * number of elements in the consecutive starting with this element.
     * If count is more than current res, then updateres.
     */
    public static int longestConsecutiveSequence(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int max = 1;
        for (int e : num)
            set.add(e);
        for (int e : num) {
            int left = e - 1;
            int right = e + 1;
            int count = 1;
            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }
            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }
            max = Math.max(count, max);
        }
        return max;
    }


   /**
     * Given a string s, palindromePartition s such that every substring of the palindromePartition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * For example, given s = "aab", Return
     * [ ["aa","b"], ["a","a","b"] ]   Tags: Backtracking
     */
    public static List<List<String>> palindromePartition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        partition(s, 0, res, new ArrayList<>());
        return res;
    }
    public static void partition(String s, int pos, List<List<String>> res, List<String> cut) {
        if (pos == s.length()) { // note the stop condition
            res.add(new ArrayList<>(cut)); // dereference
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {// start from 1??
            String prefix = s.substring(pos, i);
            if (isPalindrome(prefix)) {
                cut.add(prefix);
                partition(s, i, res, cut); // update pos with i
                cut.remove(cut.size() - 1);
            }
        }
    }
    private static boolean isPalindrome(String str) {
        int s = 0;
        int e = str.length() - 1;
        while (s < e) {
            if (str.charAt(s) != str.charAt(e))
                return false;
            s++;
            e--;
        }
        return true;
    }

    /**
    * Input: nums = [2,2,1] Output: 1
    * Input: nums = [4,1,2,1,2] Output: 4
    */
    public static int singleNumbera(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int res = A[0];
        for (int i = 1; i < A.length; i++) {
            res ^= A[i];
        }
        return res;
    }

    /**
     * Use a hashmap to store map between original node and copy node 递归 最好的 第一解法
     * Get copy node from map
     */
    public RandomListNode CopyListWithRandom(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        return helper(head, map);
    }

    public RandomListNode helper(RandomListNode node, Map<RandomListNode, RandomListNode> map) {
        if (node == null)
            return null;
        if (map.containsKey(node))
            return map.get(node); // return copy
        RandomListNode res = new RandomListNode(node.label);
        map.put(node, res); // build map
        res.next = helper(node.next, map); // build next
        res.random = helper(node.random, map); // build copy
        return res;
    }


    /**
     * DP, bottom-up  最好的
     * Build from first character to last in input
     * Record whether it can be break in a boolean array
     * Traverse from start to current position and check whether current
     * boolean is true and the rest in set
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null)
            return false;
        int len = s.length();
        boolean[] can = new boolean[len + 1];  //???
        can[0] = true;
        for (int i = 1; i <= len; i++) { //注意范围
            for (int j = 0; j < i; j++) {
                if (can[j] && dict.contains(s.substring(j, i))) {
                    can[i] = true;
                    break;
                }
            }
        }
        return can[len];
    }

    public static boolean linkedListCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    /**
     * Given a linked list, return the node where the cycle begins. If there is no
     * cycle, return null.
     * Follow up: Can you solve it without using extra space?
     */
    public static ListNode linkedListCycleII(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

    //LRU Cache



    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode t = slow.next;
        slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(t);
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }


    class LRUCache {

        class Node {
            int key;
            int value;
            Node prev;
            Node next;
            Node() {

            }
            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, Node> cache;
        private Node head;
        private Node tail;
        private int capacity;
        private int size;

        public LRUCache(int capacity) {
            cache = new HashMap<>();
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.value = value;
                moveToHead(node);
            } else {
                Node node = new Node(key, value);
                cache.put(key, node);
                addToHead(node);
                ++size;
                if (size > capacity) {
                    node = removeTail();
                    cache.remove(node.key);
                    --size;
                }
            }
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        private Node removeTail() {
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
    }


    public static int maximumProductSubarray(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int max = A[0], min = A[0], maxAns = A[0];
        for (int i = 1; i < A.length; i++) {
            int mx = max, mn = min;
            max = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]);
            min = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
            maxAns = Math.max(max, maxAns);
        }
        return maxAns;
    }

    /**
     * Given an integer array nums, find the contiguous subarray within an array
     * (containing at least one number) which has the largest product.
     * Input: [2,3,-2,4]  Output: 6 Explanation: [2,3] has the largest product 6.
     * Input: [-2,0,-1] Output: 0 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     * (E) House Robber (M) Product of Array Except Self
     * 这道题是maximum subarray sum 的变体
     * 已然用动归 DP来解决 每个dp数组保存的都是以当前元素结尾的乘积最大值
     * 但是要注意因为是相乘 负负得正 所以还要保存最小值 因为最小值碰到个负数很可能就会变成最大值
     * DP, update according to A[i]  
     */
    public static int maximumProductSubarray(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int max = A[0], min = A[0], maxAns = A[0];
        for (int i = 1; i < A.length; i++) {
            int mx = max, mn = min;
            max = Math.max(Math.max(A[i], mx * A[i]), mn * A[i]);
            min = Math.min(Math.min(A[i], mx * A[i]), mn * A[i]);
            maxAns = Math.max(max, maxAns);
        }
        return maxAns;
    }


    class MinStack {
        static class Element {
            final int value;
            final int min;

            public Element(int x, int min) {
                this.value = x;
                this.min = min;
            }
        }

        Stack<Element> s;

        public void push(int x) {
            if (s == null)
                s = new Stack<>();
            int min = s.isEmpty() ? x : Math.min(x, s.peek().min);
            s.push(new Element(x, min));
        }

        public void pop() {
            s.pop();
        }

        public int top() {
            return s.peek().value;
        }

        public int getMin() {
            return s.peek().min;
        }
    }

    public ListNode intersectionOfTwoLinkedList(ListNode headA, ListNode headB) {
        if (null == headA || null == headB)
            return null;
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            curA = curA == null ? headB : curA.next;
            curB = curB == null ? headA : curB.next;
        }
        return curA;
    }

    public int majorityElementa(int[] num) {
        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major = num[i];
            } else if (major == num[i]) {
                count++;
            } else count--;

        }
        return major;
    }

    /**
     * Rotate an array of n elements to the right by k steps
     * For example, with n = 7 and k = 3,
     * the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
     */
    public void rotateArrayc(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return;
        if (nums.length == 1 || k == 0 || k == nums.length)
            return; // special cases
        int len = nums.length;
        k %= len;
        int idx = 0;
        int tmp = nums[idx]; // the number to write to new index
        int tmp2; // save the number at new index
        for (int i = 0, j = 0; i < len; i++) { // j is the start index of current circle
            idx = (idx + k) % len;
            tmp2 = nums[idx];
            nums[idx] = tmp;
            tmp = tmp2;
            if (idx == j) { // circle ends
                idx = ++j; // move to next circle
                tmp = nums[idx];
            }
        }
    }

    //House Robber

    /**
    * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
    * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
    * Input: grid = [
    * ["1","1","1","1","0"],
    * ["1","1","0","1","0"],
    * ["1","1","0","0","0"],
    * ["0","0","0","0","0"]
        *     ]
    * Output: 1
    * Input: grid = [
    *   ["1","1","0","0","0"],
    *   ["1","1","0","0","0"],
    *   ["0","0","1","0","0"],
    *  ["0","0","0","1","1"]
    * ]
    * Output: 3
 */
    public int NumberofIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    merge(grid, i, j);
                }
            }
        }
        return count;
    }

    public void merge(char[][] grid, int i, int j) {
        //validity checking
        if (i < 0 || j < 0 || i > grid.length - 1
                || j > grid[0].length - 1 || grid[i][j] != '1')
            return;
        //set visited cell to '0'
        grid[i][j] = '0';
        //merge all adjacent land
        merge(grid, i - 1, j);
        merge(grid, i + 1, j);
        merge(grid, i, j - 1);
        merge(grid, i, j + 1);
    }


    public Node reverseLinkedLista(Node head) {
        Node newHead = null;
        while (head != null) {
            Node next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
    
    Node reverseLinkedListb(Node head) {
        if (head == null || head.next == null)
            return head;
        Node temp = reverseLinkedListb(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }

    //？？
    public boolean courseSchedule(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0)
                indegree[ready]++; //duplicate case
            matrix[pre][ready] = 1;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[course][i] != 0) {
                    if (--indegree[i] == 0)
                        queue.offer(i);
                }
            }
        }
        return count == numCourses;
    }

    // Course Schedule II


    /**
     * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
     * the kth distinct element.
     * For example, Given [3,2,1,5,6,4] and k = 2, return 5.
     * Note: You may assume k is always valid, 1 ≤ k ≤ array's length.
     */
    public int kthLargestElementinanArray(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }


    /**
     * Given a 2D binary matrix filled with 0's and 1's,
     * find the largest square containing only 1's and return its area.
     * For example, given the following matrix:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * Return 4.
     * DP 动态规划 状态转移方程：
     * dp[x][y] = min(dp[x - 1][y - 1], dp[x][y - 1], dp[x - 1][y]) + 1
     * 上式中，dp[x][y]表示以坐标(x, y)为右下角元素的全1正方形矩阵的最大长度（宽度）
     * 如果当前点为0  则dp[x][y]就是0  肯定没有以0 为右下角元素的全1正方形
     */
    public int maximalSquare(char[][] a) {
        if (a.length == 0)
            return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }


     /**
     * Invert a binary tree.
     * Input:
     * 4
     * /   \
     * 2     7
     * / \   / \
     * 1   3 6   9
     * Output:
     * 4
     * /   \
     * 7     2
     * / \   / \
     * 9   6 3   1
     * 用BFS来翻转
     */
    public TreeNode invertBinaryTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode newLeft = invertBinaryTree(root.right);
        TreeNode newRight = invertBinaryTree(root.left);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }

    public TreeNode invertBinaryTreeb(TreeNode root) {
        if (root == null) {
            return null;
        }
        final Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }


    public static boolean palindromeLinkedList(ListNode head) {
        ListNode m = mid(head);
        m = reverse(m);
        while (m != head && m != null) {
            if (m.val != head.val) {
                return false;
            }
            m = m.next;
            head = head.next;
        }
        return true;
    }

    static ListNode mid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode t = head.next;
            head.next = prev;
            prev = head;

            head = t;
        }
        return prev;
    }

    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        // make sure p < q
        if (p.val > q.val)
            return lowestCommonAncestorBST(root, q, p);
        // find p <= root <= q
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            //        while (!(p.val <= root.val && root.val <= q.val)) {
            root = root.val > q.val ? root.left : root.right;
        }
        return root;
    }
    
    public TreeNode lowestCommonAncestorBSTB(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val)
            return lowestCommonAncestorBSTB(root, q, p);
        TreeNode m = root;
        if (m.val > p.val && m.val < q.val) {
            return m;
        }
        else if (m.val > p.val && m.val > q.val) {
            return lowestCommonAncestorBSTB(root.left, p, q);
        }
        else if (m.val < p.val && m.val < q.val) {
            return lowestCommonAncestorBSTB(root.right, p, q);
        }
        return root;
    }

    /**
     * 由于output[i] = (x0 * x1 * ... * xi-1) * (xi+1 * .... * xn-1)
     * 因此执行两趟循环：
     * 第一趟正向遍历数组，计算x0 ~ xi-1的乘积
     * 第二趟反向遍历数组，计算xi+1 ~ xn-1的乘积 Space is O(1) 最好的
     */
    public int[] productofArrayExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    /**
     * We scan the array from 0 to n-1, keep "promising" elements in the deque.
     * The algorithm is amortized O(n) as each element is put and polled once.
     * At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window.
     * This means If an element in the deque and it is out of i-(k-1), we discard them.
     * We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array
     * Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail.
     * This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i],
     * or any other subsequent window: a[i] would always be a better candidate.
     * As a result elements in the deque are ordered in both sequence in array and their value.
     * At each step the head of the deque is the max element in [i-(k-1),i]
     * * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * Therefore, return the max sliding window as [3,3,5,5,6,7].
     */
    public static int[] slidingWindowMaximum(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = a[q.peek()];
            }
        }
        return r;
    }

    /**
     * * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted in ascending from left to right. Integers in each column are sorted in ascending from top to bottom.
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true. Given target = 20, return false.
     * Search Space Reduction  Because the rows and columns of the matrix are sorted (from left-to-right and top-to-bottom, respectively),
     * we can prune O(m) or O(n) elements when looking at any particular value.
     * Time complexity : O(n+m) Space complexity : O(1)
     */
    public boolean searchMatrixA(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int row = 0;
        int col = matrix[row].length - 1;
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }

    /**
     * Given a positive integer n, find the least number of perfect square numbers
     * (for example, 1, 4, 9, 16, ...) which sum to n.
     * given n = 12, return 3 because 12 = 4 + 4 + 4;
     * given n = 13, return 2 because 13 = 4 + 9
     * 可以用 动态规划 DP 来做
     * 通项公式是 dp[i] = 1 + min (dp[i-j*j] for j*j<=i)
     * 就是看看当前数去掉一个完全平方数 然后找最小值  然后+1(1就是代表这个去掉的完全平方数)
     * DP, bottom-up
     */
    public int perfectSquares(int n) {
        int[] res = new int[n + 1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                res[i] = Math.min(res[i], res[i - j * j] + 1);
            }
        }
        return res[n];
    }

    /**
     * Given an array nums, write a function to move all 0's to the end of it
     * while maintaining the relative order of the non-zero elements.
     * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     * Note:You must do this in-place without making a copy of the array. Minimize the total number of operations.
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0)
                nums[insertPos++] = num;
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    /**
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     * Input: [1,3,4,2,2]Output: 2  Input: [3,1,3,4,2]Output: 3
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array,but it could be repeated more than once.
     */
    public int findtheDuplicateNumber(int[] nums) {
        if (nums.length > 1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }


    //FindMedianfromDataStream


    //SerializeandDeserializeBT

    /**
     * "Given an unsorted array of integers, find the length of longest increasing subsequence.
     * For example,
     * Given [10, 9, 2, 5, 3, 7, 101, 18],
     * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
     * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
     * Your algorithm should run in O(n2) complexity.
     * Follow up: Could you improve it to O(n log n) time complexity?"
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] maxLength = new int[nums.length];
        Arrays.fill(maxLength, 1);
        int maximumSoFar = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1);
                }
            }
            maximumSoFar = Math.max(maximumSoFar, maxLength[i]);
        }
        return maximumSoFar;
    }

    public int coinChangeB(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        int[] dp = new int[amount + 1];
        int sum = 0;
        while (++sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                if (sum >= coin && dp[sum - coin] != -1) {
                    int temp = dp[sum - coin] + 1;
                    min = min < 0 ? temp : (temp < min ? temp : min);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }

    /**
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
     * calculate the number of 1's in their binary representation and return them as an array.
     * For num = 5 you should return [0,1,1,2,1,2].
     */
    public static int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++)
            f[i] = f[i >> 1] + (i & 1);
        return f;
    }

    /**
     * Given a non-empty array of integers, return the k most frequent elements.
     * Given [1,1,1,2,2,3] and k = 2, return [1,2].
     * Note:You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }
        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    /**
     * Given an encoded string, return it's decoded string.The encoding rule is: k[encoded_string], where the encoded_string
     * inside the square brackets is being repeated exactly k times.Note that k is guaranteed to be a positive integer.
     * You may assume that the input string is always valid; No extra white spaces,square brackets are well-formed, etc.
     * Furthermore, you may assume that the original data does not contain
     * any digits and that digits are only for those repeat numbers, k.
     * For example, there won't be input like 3a or 2[4].
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     */
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    /**
     * Given a non-empty array containing only positive integers, find if the array can
     * be partitioned into two subsets such that the sum of elements in both subsets is equal.
     * Note:Each of the array element will not exceed 100.The array size will not exceed 200.
     * Input: [1, 5, 11, 5] Output: true Explanation: The array can be partitioned as [1, 5, 5] and [11].
     */
    public boolean partitionEqualSubsetSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int volumn = 0;
        for (int num : nums) {
            volumn += num;
        }
        if (volumn % 2 != 0) {
            return false;
        }
        volumn /= 2;
        // dp def
        boolean[] dp = new boolean[volumn + 1];
        // dp init
        dp[0] = true;
        // dp transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = volumn; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];
            }
        }
        return dp[volumn];
    }

    /**
     * You are given a binary tree in which each node contains an integer value.Find the number of paths that sum to a given value.
     * The path does not need to start or end at the root or a leaf,but it must go downwards (traveling only from parent nodes to child nodes).
     * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     * 10
     * /  \
     * 5   -3
     * / \    \
     * 3   2   11
     * / \   \
     * 3  -2   1
     * Return 3. The paths that sum to 8 are: 5 -> 3; 5 -> 2 -> 1 ; -3 -> 11
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null)
            return 0;
        return (node.val == sum ? 1 : 0)
                + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }


    /**
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
     * The order of output does not matter.
     * Input: s: "cbaebabacd" p: "abc" Output:[0, 6]
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     */
    public static List<Integer> findAllAnagramsinaString(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0)
            return list;
        int[] hash = new int[256]; //character hash
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1)
                count--;
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0)
                list.add(left);
            //if we find the window's size equals to p,
            // then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
                count++;
        }
        return list;
    }

    /**
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
     * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     * Input: nums is [1, 1, 1, 1, 1], S is 3. Output: 5
     * Explanation:
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * There are 5 ways to assign symbols to make the sum of nums be target 3.
     * Note: The length of the given array is positive and will not exceed 20.
     * The sum of elements in the given array will not exceed 1000.
     * Your output answer is guaranteed to be fitted in a 32-bit integer.
     */
    public static int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
    }

    public static int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[s];
    }

    /**
     * Given a binary tree, you need to compute the length of the diameter of the tree. 
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     * Given a binary tree
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     * Note: The length of path between two nodes is represented by the number of edges between them.
     */
    public int diameterOfBinaryTree(BinaryTreeLevelOrderTraversal.TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(BinaryTreeLevelOrderTraversal.TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    /**
     * Given an array of integers and an integer k, you need to find the total number
     * of continuous subarrays whose sum equals to k.
     * Input: nums = [1,1,1], k = 2  Output: 2 Note: The length of the array is in range [1, 20,000].
     * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
     */
    public int subarraySumEqualsK(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    public TreeNode mergeTwoBinaryTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode result = new TreeNode(t1.val + t2.val);
        result.left = mergeTwoBinaryTrees(t1.left, t2.left);
        result.right = mergeTwoBinaryTrees(t1.right, t2.right);
        return result;
    }


     /**
     * Given a list of daily temperatures T, return a list such that, for each day in the input,
     * tells you how many days you would have to wait until a warmer temperature.
     * If there is no future day for which this is possible, put 0 instead.
     * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
     * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
     * Note: The length of temperatures will be in the range [1, 30000].
     * Each temperature will be an integer in the range [30, 100].
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] ret = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            stack[++top] = i;
        }
        return ret;
    }

    /**
     * A string S of lowercase letters is given. We want to palindromePartition this string into
     * as many parts as possible so that each letter appears in at most one part,
     * and return a list of integers representing the size of these parts.
     * Input: S = "ababcbacadefegdehijhklij"  Output: [9,7,8]
     * The palindromePartition is "ababcbaca", "defegde", "hijhklij".
     * This is a palindromePartition so that each letter appears in at most one part.
     * A palindromePartition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
     * Note: S will have length in range [1, 500].
     * S will consist of lowercase letters ('a' to 'z') only.
     */
    public static List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
  }
```