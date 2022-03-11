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
     * Reverse digits of an integer.
     * Example1: x = 123, return 321  x = -123, return -321
     */
    public int reverseIntegerb(int x) {
        if (x == Integer.MIN_VALUE)
            return 0;
        int num = Math.abs(x);
        int res = 0;
        while (num != 0) {
            if (res > (Integer.MAX_VALUE - num % 10) / 10)
                return 0;
            res = res * 10 + num % 10;
            num /= 10;
        }
        return x > 0 ? res : -res;
    }

     /**
     * Whitespace, sign, out of range Trim the unnecessary whitespaces
     * initialize a variable as long to store the result, use a boolean as a flag to mark whether its negative
     * return MAX or MIN if its out of range
     */
    public static int atoi(String str) {
        /*validate input*/
        if (str == null || str.length() == 0)
            return 0;
        long longRes = 0; // result can be out of range
        /*whitespaces*/
        str = str.trim(); // remove front and trailing whitespaces
        /*sign*/
        boolean neg = false; // is negative or not
        if (str.charAt(0) == '-') {
            neg = true;
            str = str.substring(1, str.length());
        } else if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }
        /*calculation*/
        int i = 0;
        while (i < str.length()) { // calculate without sign
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                longRes = longRes * 10 + (c - '0');
            } else
                break; // break when not a digit
            i++;
        }
        longRes = neg ? longRes * (-1) : longRes; // add sign
        /*out of range*/
        if (longRes > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (longRes < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) longRes;
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
     * Given a roman numeral, zigZagConversion it to an integer.
     * Input is guaranteed to be within the range from 1 to 3999.
     * Input: "III"  Output: 3 Input: "IV"  Output: 4
     * Input: "IX"  Output: 9 Input: "LVIII"  Output: 58 Explanation: L = 50, V= 5, III = 3.
     * Tags: Math, String
     */
    public int romantoInteger(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        int length = s.length();
        int result = m.get(s.charAt(length - 1));
        for (int i = length - 2; i >= 0; i--) { // backwards
            if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i)))
                result += m.get(s.charAt(i));
            else
                result -= m.get(s.charAt(i));
        }
        return result;
    }

     /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     * Input: ["flower","flow","flight"] Output: "fl" Input: ["dog","racecar","car"] Output: ""
     * Explanation: There is no common prefix among the input strings.
     * Note:All given inputs are in lowercase letters a-z.
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
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
    
    /**
     * Given a <strong>sorted</strong> array, remove the duplicates in place such that each element
     * appear only once and return the new length. Do not allocate extra space for another array,
     * you must do this in place with constant memory.
     * Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     * Tags: Array, Two pointers
     * 两个指针 碰到和start不一样的就放到start后面 然后移动start 然后pointer继续找
     * Use count to remember current position
     */
    public int removeDuplicatesFromSortedArray(int[] A) {
        int count = 0;
        int len = A.length;
        for (int i = 0; i < len; i++) {
            if (count == 0 || A[i] != A[count - 1]) { // first or not dup
                A[count++] = A[i]; // copy and update count
            }
        }
        return count;
    }

     /**
     * 两个循环 大循环就是遍历长字符串 然后在长字符串的每个起始位都开始小循环比对字符
     * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Tags: Two Pointers, String
     */
    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0)
            return 0;
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length())
                    return i;
                if (i + j == haystack.length())
                    return -1;
                if (needle.charAt(j) != haystack.charAt(i + j))
                    break;
            }
        }
    }

    /**
     * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
     * Return the quotient after dividing dividend by divisor.
     * The integer division should truncate toward zero.
     * Input: dividend = 10, divisor = 3 Output: 3
     * Input: dividend = 7, divisor = -3 Output: -2
     * Note:Both dividend and divisor will be 32-bit signed integers.The divisor will never be 0.
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
     * For the purpose of this problem,assume that your function returns 231 − 1 when the division result overflows.
     * Tags: Math, Binary Search
     */
    public int divideTwoIntegers(int dividend, int divisor) {
        //Reduce the problem to positive long integer to make it easier.
        //Use long to avoid integer overflow cases.
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            sign = -1;
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        //Take care the edge cases.
        if (ldivisor == 0) return Integer.MAX_VALUE;
        if ((ldividend == 0) || (ldividend < ldivisor)) return 0;
        long lans = ldivide(ldividend, ldivisor);
        int ans;
        if (lans > Integer.MAX_VALUE) { //Handle overflow.
            ans = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            ans = (int) (sign * lans);
        }
        return ans;
    }

    private long ldivide(long ldividend, long ldivisor) {
        // Recursion exit condition
        if (ldividend < ldivisor) return 0;
        //  Find the largest multiple so that (divisor * multiple <= dividend),
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        //Look for additional value for the multiple from the reminder (dividend - sum) recursively.
        return multiple + ldivide(ldividend - sum, ldivisor);
    }

    private static ListNode swap(ListNode next1, ListNode next2) {
        next1.next = next2.next;
        next2.next = next1;
        return next2; // return latter node 
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
     * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
     */
    public boolean validSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        return false;
                }
            }
        }
        return true;
    }

     /**
     * The count-and-say sequence is the sequence of integers beginning as follows:
     * 1, 11, 21, 1211, 111221, ...
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth sequence.
     * Note: The sequence of integers will be represented as a string.
     * Tags: String
     */
    public static String countAndSay(int n) {
        String res = "1";
        while (--n > 0) {
            StringBuilder sb = new StringBuilder();
            char[] prev = res.toCharArray();
            for (int i = 0; i < prev.length; i++) {
                int count = 1; // initialize current count as 1
                while (i + 1 < prev.length && prev[i] == prev[i + 1]) {
                    count++; // searchinRotatedSortedArrayb for same char
                    i++;
                }
                sb.append(count).append(prev[i]);
            }
            res = sb.toString();
        }
        return res;
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
     * Implement wildcard pattern matching with support for '?' and '*' '?' Matches any single character. HARD TODO
     * '*' Matches any sequence of characters (including the empty sequence).The matching should cover the entire input string (not partial).
     * The function prototype should be:bool wildcardMatchingc(const char *s, const char *p)
     * wildcardMatchingc("aa","a") → false
     * wildcardMatchingc("aa","aa") → true
     * wildcardMatchingc("aa", "*") → true
     * wildcardMatchingc("ab", "?*") → true
     * wildcardMatchingc("aab", "c*a*b") → false
     * My own examples:
     * wildcardMatchingc("aab", "a*a*b") → true
     * wildcardMatchingc("a", "a*") → true
     * Tags: DP, Backtracking, Greedy, String
     */
    public static boolean wildcardMatching(String s, String p) {
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
            return wildcardMatching(s.substring(1), p.substring(1));
        } else {
            int len = s.length();
            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (wildcardMatching(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
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

    public double power(double x, int n) {
        if (n == 0)
            return 1;
        double v = power(x, n / 2);
        if (n % 2 == 0) {
            return v * v;
        }
        else {
            return v * v * x;
        }
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
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * You should return [1,2,3,6,9,8,7,4,5].
     */
    public static List<Integer> spiralmatrixb(int[][] matrix) {
        List<Integer> elements = new ArrayList<>();
        if (matrix.length == 0)
            return elements;
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = -1;
        while (true) {
            for (int i = 0; i < n; i++) {
                elements.add(matrix[row][++col]);
            }
            if (--m == 0)
                break;
            for (int i = 0; i < m; i++) {
                elements.add(matrix[++row][col]);
            }
            if (--n == 0)
                break;
            for (int i = 0; i < n; i++) {
                elements.add(matrix[row][--col]);
            }
            if (--m == 0)
                break;
            for (int i = 0; i < m; i++) {
                elements.add(matrix[--row][col]);
            }
            if (--n == 0)
                break;
        }
        return elements;
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
     * Given a non-negative number represented as an array of digits,<strong>plus one</strong> to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     * Tags: Array, Math
     */
    public static int[] plusOnea(int[] digits) {
        if (digits == null || digits.length == 0)
            return digits;
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
            digits[i] = digit;
            if (carry == 0)
                return digits;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    /**
     * Implement int sqrtX(int x).Compute and return the square root of x.
     * 1. Validate input first 2.Binary Search from 1 ~ x 3.Negative? 4.Perfect square?
     * Note possible overflows when mid * mid or (left + right) / 2.
     */
    public static int sqrtX(int x) {
        if (x < 0)
            return -1; // if (x <= 0) return x;
        if (x == 0)
            return 0;
        int left = 1; // search range
        int right = x;
        int mid;
        while (left <= right) { // can equal
            mid = left + (right - left) / 2; // left + right can overflow
            if (mid == x / mid)
                return mid; // mid * mid can overflow
            else if (mid > x / mid)
                right = mid - 1; // not right = mid
            else
                left = mid + 1; // break equal
        }
        return right;
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
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
     * Go through the matrix and use first row and first col to remember which cols and rows are to be sets
     * Use two flags for whether first row and first col should be set
     * 这样的做法只需要两个额外变量，所以空间复杂度是O(1)。
     * 时间上来说上面三种方法都是一样的，需要进行两次扫描，一次确定行列置0情况，一次对矩阵进行实际的置0操作，所以总的时间复杂度是O(m*n)。
     */
    public static void setMatrixZeros(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean firstRow = false;
        boolean firstCol = false;
        //mark zeros on first row and column
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0)
                        firstRow = true;
                    if (j == 0)
                        firstCol = true;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //set first column and row
        if (firstRow) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
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
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * Note: The number of elements initialized in nums1 and nums2 are m and n respectively.You may assume that nums1 has enough space
     * (size that is greater or equal to m + n) to hold additional elements from nums2.
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * Output: [1,2,2,3,5,6]
     * 算法的时间复杂度是O(m+n),m和n分别是两个数组的长度，空间复杂度是O(1)
     */
    public static int[] mergeSortedArray(int[] A, int m, int[] B, int n) {
        int[] res = new int[m + n];
        if (A == null || B == null)
            return res;
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i > -1 && j > -1) {
            A[k--] = A[i] > B[j] ? A[i--] : B[j--];
        }
        while (j > -1) {
            A[k--] = B[j--];
        }
        return A;
    }

    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given an encoded message containing digits, determine the total number of ways to decode it.
     * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12). The number of ways decoding "12" is 2.
     * Tags: DP, String
     */
    public int decodeWaysa(String s) {
        int n = s.length();
        if (n == 0)
            return 0;
        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0')
                continue;
            else
                memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ?
                        memo[i + 1] + memo[i + 2] : memo[i + 1];
        return memo[0];
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

    public List<List<Integer>> btZigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean toggle = false;
        while (!q.isEmpty()) {
            List<Integer> curLevel = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (!toggle)
                    curLevel.add(n.val);
                else
                    curLevel.add(0, n.val);
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            toggle = !toggle;
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
     * Given an array where elements are sorted in ascending order, zigZagConversion it to a <strong>height balanced</strong> BST.
     */
    public static TreeNode convertSortedArrayToBinarySearchTree(int[] num) {
        if (num == null || num.length == 0)
            return null;
        return helper(num, 0, num.length - 1);
    }

    public static TreeNode helper(int[] num, int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = helper(num, left, mid - 1); // left and mid -1 
        root.right = helper(num, mid + 1, right); // mid + 1 and right
        return root;
    }

    //Populating Next Right Pointers in Each Node

     /**
     * Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5,
     */
    public List<List<Integer>> pascalsTriangle(int numRows) {
        List<List<Integer>> allrows = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            allrows.add(new ArrayList<Integer>(row));
        }
        return allrows;
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
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete
     * <strong>as many transactions as you like</strong> (ie, buy one and sell
     * one share of the stock multiple times). However, you may not engage in
     * multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * Tags: Array, Greedy If next value is bigger, add the difference up to the profit
     */
    public static int bestTimetoBuyandSellStockII(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++)
            if (prices[i + 1] > prices[i])
                max += prices[i + 1] - prices[i];
        return max;
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
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     * Note: Have you consider that the string might be empty? This is a good question to ask during an interview.
     * For the purpose of this problem, we define empty string as valid palindrome.
     * Tags: Two pointers, String
     * 用l<r判断比较好 否则会越界 记得每个循环都加上l<r的判断
     */
    public static boolean validPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }
        return true;
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
     * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * After running your function, the board should be:
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * Tags: BFS
     * 从矩阵的四条边开始, 如果碰到0的元素 就用BFS查找与他临近的元素 然后把这些元素统统置为Y
     * 一直到结束,然后把这些Y的变成O 剩下那些本来为O的就可以变成X 因为和边框上的元素有连接  就说明这些元素没有围起来
     */
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0)
            return;
        int m = board.length;
        int n = board[0].length;
        //merge O's on left & right boarder
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                merge(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                merge(board, i, n - 1);
            }
        }
        //merge O's on top & bottom boarder
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                merge(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                merge(board, m - 1, j);
            }
        }
        //process the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void merge(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;
        if (board[i][j] != 'O')
            return;
        board[i][j] = '#';
        merge(board, i - 1, j);
        merge(board, i + 1, j);
        merge(board, i, j - 1);
        merge(board, i, j + 1);
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
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with
     * an empty tank at one of the gas stations.
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     * Note: The solution is guaranteed to be unique.
     * Tags: Greedy
     * 复杂度是O(n)
     * 如果所有站点拥有的gas小于所有站点的cost 那么肯定无解 所以就返回-1
     * 如果能从一个站点能到另一个站点 那就说明 一定之前从一个起点开始 能到达这个点
     * 所以如果发现当前站点 不能到下一个站点 就将起始位置设置在下一个站点
     * 这样转了一圈之后 那个起始位置就是正确的
     */
    public static int gasStation(int[] gas, int[] cost) {
        int restGas = 0; // gas remain for current trip
        int previous = 0; // negative gas for previous trips
        int start = 0; // start index of current trip
        for (int i = 0; i < gas.length; i++) {
            restGas += gas[i] - cost[i];
            if (restGas < 0) {
                previous += restGas; // gas needed for previous trips
                restGas = 0; // reset restGas
                start = i + 1; // set start index to next station
            }
        }
        return previous + restGas >= 0 ? start : -1;
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

     /**
     * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
     * Return all such possible sentences.
     * Note:The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words
     * given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"]. A solution is ["cats and dog", "cat sand dog"].
     * given s = "catsandog" wordDict = ["cats", "dog", "sand", "and", "cat"] Output:[]
     * Tags: DP, Backtracking
     * DFS会超时 可以加入word break I 里面的那个判断的dp 在每次dfs前先判断当前的s可以不可以被dic分掉
     */
    public static ArrayList<String> wordBreakII(String s, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0)
            return res;
        helper(s, dict, 0, "", res);
        return res;
    }

    private static void helper(String s, Set<String> dict, int start, String item, ArrayList<String> res) {
        if (start >= s.length()) {
            res.add(item);
            return;
        }
        StringBuilder str = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            str.append(s.charAt(i));
            if (dict.contains(str.toString())) {
                String newItem = item.length() > 0 ? (item + " " + str.toString()) : str.toString();
                helper(s, dict, i + 1, newItem, res);
            }
        }
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

	//Max Points on a Line


	 /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     * Note: Division between two integers should truncate toward zero.The given RPN expression is always valid.
     * That means the expression would always evaluate to a result and there won't be any divide by zero operation.
     * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     * Tags: Stack
     * 用堆栈处理  碰见数字就入栈 碰见符号就把栈里的元素弹出计算 然后结果入栈
     * 用字典表示了各种符号的操作 很巧妙
     * 对于除法的计算 要注意python会对1/-100这种除法返回-1 只有用1/int(float(-100))这样才行
     */
    public static int evaluateReversePolishNotation(String[] tokens) {
        int a, b;
        Stack<Integer> S = new Stack<>();
        for (String s : tokens) {
            switch (s) {
                case "+":
                    S.add(S.pop() + S.pop());
                    break;
                case "/":
                    b = S.pop();
                    a = S.pop();
                    S.add(a / b);
                    break;
                case "*":
                    S.add(S.pop() * S.pop());
                    break;
                case "-":
                    b = S.pop();
                    a = S.pop();
                    S.add(a - b);
                    break;
                default:
                    S.add(Integer.parseInt(s));
                    break;
            }
        }
        return S.pop();
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

    /**
     * A peak element is an element that is greater than its neighbors.
     * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.You may imagine that num[-1] = num[n] = -∞.
     * in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     * Input: nums = [1,2,1,3,5,6,4] Output: 1 or 5
     * Note:Your solution should be in logarithmic complexity.
     * Tags: Array, Binary Search
     */
    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int n = num.length;
        if (n <= 1)
            return 0;
        // handle the first and last element in num[]
        if (num[0] > num[1])
            return 0;
        if (num[n - 1] > num[n - 2])
            return n - 1;
        int left = 1, right = n - 2;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1])
                return mid;
            else if (num[mid] > num[mid + 1])
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }


    //Missing Ranges

        /**
     * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     * Given numerator = 1, denominator = 2, return "0.5".
     * Given numerator = 2, denominator = 1, return "2".
     * Given numerator = 2, denominator = 3, return "0.(6)". Tags: Hashtable, Math
     */
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();
        String sign = (numerator < 0 == denominator < 0 || numerator == 0) ? "" : "-";
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        result.append(sign);
        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0)
            return result.toString();
        result.append(".");
        HashMap<Long, Integer> hashMap = new HashMap<>();
        while (!hashMap.containsKey(remainder)) {
            hashMap.put(remainder, result.length());
            result.append(10 * remainder / den);
            remainder = 10 * remainder % den;
        }
        int index = hashMap.get(remainder);
        result.insert(index, "(");
        result.append(")");
        return result.toString().replace("(0)", "");
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
     * Related to question Excel Sheet Column Title
     * Given a column title as appear in an Excel sheet, return its corresponding column number.
     * For example:
     * A -> 1
     * B -> 2
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28  Tags: Math
     * 还是一样 从后倒着往前走 但是有一个count要记录现在是几次方了
     */
    public static int ExcelSheetColNum(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }

     /**
     * Given an integer n, return the number of trailing zeroes in n!.
     * Note: Your solution should be in logarithmic time complexity.Tag: Math
     * 处理这个问题也很简单，首先对n÷5，移除所有的单个5，然后÷25，移除额外的5，以此类推。
     */
    public static int trailingZeroesD(int n) {
        if (n < 0)
            return -1;
        int count = 0;
        for (long i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        return count;
    }

     /**
     * Create a comparator for sorting Convert num to String and compare the concatenated result of them
     * Note {0, 0} is a special case
     * Given a list of non negative integers, arrange them such that they form the largest number.
     * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     * Note: The result may be very large, so you need to return a string instead of an integer.Tags: Sort
     * 要使用cmp函数来排序 比较规则是x+y 和y+x的大小 而且要倒序
     * 同时要注意[0,0]这种特殊情况
     */
    public String largestNumber(int[] num) {
        StringBuilder res = new StringBuilder();
        if (num == null || num.length == 0)
            return res.toString();
        String[] str = new String[num.length];
        for (int i = 0; i < num.length; i++)
            str[i] = num[i] + "";
        Comparator<String> comp = (s1, s2) -> Long.valueOf(s2 + s1).compareTo(Long.valueOf(s1 + s2));
        Arrays.sort(str, comp);
        if (str[0].equals("0"))
            return "0"; // deal with 0
        for (String s : str)
            res.append(s);
        return res.toString();
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

    /**
     * Reverse bits of a given 32 bits <strong>unsigned</strong> integer. easy
     * Example:
     * input 43261596, represented in binary as 00000010100101000001111010011100
     * return 964176192, represented in binary as 00111001011110000010100101000000
     * Follow up:If this function is <strong>called many times</strong>, how would you optimize it?
     * Answer:Cache result for each bytes.
     * Related problem: Reverse Integer
     * Tags: Bit Manipulation
     * 优化的办法就是先把0000 - 1111的翻转都存起来 这样就可以四位四位的截取 然后查询了
     */
    public int reverseBits(int n) {
        int res = 0;
        // concat n's ith digit with res
        for (int i = 0; i < 32; i++)
            res = (res << 1) ^ ((n >>> i) & 1);
        return res;
    }

      /**
     * Write a function that takes an unsigned integer and returns the number of
     * ’1' bits it has (also known as the Hamming weight).
     * For example, the 32-bit integer '11' has binary representation
     * 00000000000000000000000000001011, so the function should return 3.
     * Tags: Bit Manipulation
     * "n &= n - 1" is used to delete the right "1" of n,Stop when all 1s are deleted and n is zero
     */
    public int numberOf1Bits(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
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
        ]
Output: 1
    * Input: grid = [
    *   ["1","1","0","0","0"],
    *   ["1","1","0","0","0"],
    *   ["0","0","1","0","0"],
    *  ["0","0","0","1","1"]
    * ]
 * Output: 3*/
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

    /**
     * A happy number is a number defined by the following process: Starting with any positive integer,
     * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
     * or it loops endlessly in a cycle which does not include 1.Those numbers for which this process ends in 1 are happy numbers.
     * Example: 19 is a happy number
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */
    public static boolean happyNumber(int n) {
        Set<Integer> inLoop = new HashSet<Integer>();
        int squareSum, remain;
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1)
                return true;
            else
                n = squareSum;
        }
        return false;
    }

     /**
     * Count the number of prime numbers less than a non-negative number, n.
     */
    public static int countPrimes(int n) {
        if (n <= 2)
            return 0;
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
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

    //Implement Trie (Prefix Tree)

    // Course Schedule II

    //Word Search II

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
     * Given an array of integers, find if the array contains any duplicates.
     * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<Integer>();
        for (int n : nums) {
            if (s.contains(n)) {
                return true;
            }
            s.add(n);
        }
        return false;
    }

    //TheSkylineProblem


     /**
     * 这道题 是先把符号存下来 然后检测符号的时候 指针已经指向了符号后面的那个数字 (所以最开始默认是加号为了把第一个数字入栈)
     * 然后如果碰见加号 就把之前拼出来的数字入栈 减号的话就把那个数字取反入栈, 如果是乘号就要把栈里之前的数字弹出来做乘法之后再入栈 除法也一样
     * 然后返回结果把栈里的数字累加起来即可
     */
    public int basicCalculatorII(String s) {
        int len;
        if (s == null || (len = s.length()) == 0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';//重要
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';//重要
            }
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }

 	public int kthSmallestElementinaBST(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallestElementinaBST(root.left, k);
        } else if (k > count + 1) {
            return kthSmallestElementinaBST(root.right, k - 1 - count); // 1 is counted as current node
        }
        return root.val;
    }

    public int countNodes(TreeNode n) {
        if (n == null)
            return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
    
    public int kthSmallestElementinaBSTb(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        while (root != null) {
            st.push(root);
            root = root.left;
        }
        while (k != 0) {
            TreeNode n = st.pop();
            k--;
            if (k == 0)
                return n.val;
            TreeNode right = n.right;
            while (right != null) {
                st.push(right);
                right = right.left;
            }
        }
        return -1; // never hit if k is valid
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
     * Given only a pointer to a node to be deleted in a singly linked list, how do you deleteNodeinaLinkedList it?
     * Tags: LinkedList
     * 其实是把后面的点复制过来 然后把后面的点就删了
     */
    void deleteNodeinaLinkedList(Node n) {
        if (n.next == null)
            n = null;
        Node temp = n.next;
        n.val = temp.val;
        n.next = temp.next;
        temp = null;
    }
    public void deleteNode(Node node) {
        if (node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
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
     * The idea is simple. It creates a size 26 int arrays as buckets for each letter in
     * alphabet. It increments the bucket value with String s and decrement with string t.
     * So if they are anagrams, all buckets should remain with initial value which is zero.
     * So just checking that and return
     * * Given two strings s and t, write a function to determine if t is an anagram of s.
     * s = "anagram", t = "nagaram", return true. s = "rat", t = "car", return false.
     * Note: You may assume the string contains only lowercase alphabets.
     * Follow up: What if the inputs contain unicode characters?
     * How would you adapt your solution to such case?
     * 用哈希表统计或者直接排序就行
     */
    public boolean validAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++)
            alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++)
            alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet)
            if (i != 0)
                return false;
        return true;
    }

    //Flatten2DVector

     /**
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * find the minimum number of conference rooms required.
     * Given [[0, 30],[5, 10],[15, 20]], return 2.Input: [[7,10],[2,4]] Output: 1
     * uses min heap, average time complexity is O(nlogn).Sort the intervals by start time
     * Use a min heap to track the minimum end time of merged intervals start with the first meeting, put it to a meeting room
     * get the meeting room that finishes earliest if the current meeting starts right after there's no need for a new room, merge the interval
     * otherwise, this meeting needs a new room don't forget to put the meeting room back
     */
    public static int meetingRoomsIIa(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, Comparator.comparingInt(a -> a.end));
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {
                interval.end = intervals[i].end;
            } else {
                heap.offer(intervals[i]);
            }
            heap.offer(interval);
        }
        return heap.size();
    }

    /**
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,find the one that is missing from the array.
     * Input: [3,0,1]  Output: 2 Input: [9,6,4,2,3,5,7,0,1]  Output: 8
     * Note:Your algorithm should run in linear runtime complexity.
     * Could you implement it using only constant extra space complexity?
     * Tags: Array, Math, Bit Manipulation
     */
    //avoid overflow
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += i + 1 - nums[i];
        }
        return res;
    }


    //Alien Dictionary


    /**
     * The first pass is to pick out the candidate. If candidate knows i, then switch candidate.
     * The second pass is to check whether the candidate is real.
     * Given: function:  isFriend(a, b)
     * Returns true if b is treated as a friend by a group of persons, say,
     * represented as an array. all the other n - 1 people know him/her but he/she does not know any of them.
     * 可以通过一遍就过滤出来一个候选的
     * The key part is the first loop.
     * To understand this you can think the knows(a,b) as a a < b comparison,
     * if a knows b then a < b, if a does not know b, a > b.
     * then if there is a celebrity, he/she must be the "maximum" of the n people.
     * 然后只需要检查这个候选的是不是就行了
     */
    public int findTheCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i))
                candidate = i;
        }
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) return -1;
        }
        return candidate;
    }
    public boolean knows(int a, int b) {
        return true;
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
     * Given a binary search tree and a node in it, find the in-order inorderSuccessorinBST of that node in the BST.
     * Note: If the given node has no in-order inorderSuccessorinBST in the tree, return null.
     * 只有往左走的时候 也就是p的值比root得小的时候 就尝试去找个更小的 需要记录succ就可以
     */
    public TreeNode inorderSuccessorinBST(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        if (root.val <= p.val) {
            return inorderSuccessorinBST(root.right, p);
        } else {
            TreeNode left = inorderSuccessorinBST(root.left, p);
            return (left != null) ? left : root;
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


	//Game of Life

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


	//308. Range Sum Query 2D



    /**
     * You are given an integer array nums and you have to return a new counts array.
     * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
     * Given nums = [5, 2, 6, 1]
     * To the right of 5 there are 2 smaller elements (2 and 1).
     * To the right of 2 there is only 1 smaller element (1).
     * To the right of 6 there is 1 smaller element (1).
     * To the right of 1 there is 0 smaller element.
     * Return the array [2, 1, 1, 0]."
     * Traverse from the back to the beginning of the array,maintain an sorted array of numbers have been visited.
     * Use findIndex() to find the first element in the sorted array which is larger or equal to target number.
     * For example, [5,2,3,6,1], when we reach 2, we have a sorted array[1,3,6], findIndex() returns 1,
     * which is the index where 2 should be inserted and is also the number smaller than 2.
     * Then we insert 2 into the sorted array to form [1,2,3,6].
     */
    public List<Integer> countofSmallerNumbersAfterSelf(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        List<Integer> sorted = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            ans[i] = index;
            sorted.add(index, nums[i]);
        }
        return Arrays.asList(ans);
    }
    private int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0)
            return 0;
        int start = 0;
        int end = sorted.size() - 1;
        if (sorted.get(end) < target)
            return end + 1;
        if (sorted.get(start) >= target)
            return 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sorted.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        //重新判断一下
        if (sorted.get(start) >= target)
            return start;
        return end;
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


    //Wiggle Sort II

     /**
     * "Given an integer, write a function to determine if it is a power of three.
     * Follow up:Could you do it without using any loop / recursion?"
     * 和power of two一样 也可以直接用个3最大的次方去除一下就可以
     */
    public boolean isPowerOfThreea(int n) {
        return Integer.toString(n, 3).matches("10*");
    }

    public boolean powerOfThree(int n) {
        return n > 0 && (n == 1 || (n % 3 == 0 && powerOfThree(n / 3)));
    }

    public boolean powerOfThreeb(int n) {
        if (n > 1)
            while (n % 3 == 0) n /= 3;
        return n == 1;
    }
    
     /**
     * "Given a singly linked list, group all odd nodes together followed by the even nodes.
     * Please note here we are talking about the node number and not the value in the nodes.
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes)
     * time complexity.
     * Given 1->2->3->4->5->NULL, return 1->3->5->2->4->NULL.
     * Note: The relative order inside both the even and odd groups should remain as it was in the input.
     * The first node is considered odd, the second node even and so on ..."
     * 就是当长度为奇数和长度为偶数的时候 都画个图 来思考一下就行
     * 将一个链表内的奇数元素放在前面，偶数元素放在后面
     */
    public static ListNode oddEvenLinkedList(ListNode head) {
        if (head != null) {
            ListNode odd = head, even = head.next, evenHead = even;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }


      /**
     * Do DFS from every cell
     * Compare every 4 direction and skip cells that are out of boundary or smaller
     * Get matrix max from every cell's max, Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
     * The key is to cache the distance because it's highly possible to revisit a cell
     * Given an integer matrix, find the length of the longest increasing path.
     * From each cell, you can either move to four directions: left, right, up or down.
     * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
     * nums = [
     * [9,9,4],
     * [6,6,8],
     * [2,1,1]
     * ] Return 4 The longest increasing path is [1, 2, 6, 9].
     * nums = [
     * [3,4,5],
     * [3,2,6],
     * [2,2,1]
     * ] Return 4 The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     * Depth-first Search Topological Sort Memoization
     */
    public static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0)
            return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }
    public int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0)
            return cache[i][j];
        int max = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j])
                continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }

     /**
     * Return true if there exists i, j, k
     * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
     * Given [1, 2, 3, 4, 5],return true. Given [5, 4, 3, 2, 1],return false.
     */
    public boolean increasingTripletSubsequence(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both,
        // while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) {
                small = n;
            } // update small if n is smaller than both
            else if (n <= big) {
                big = n;
            } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }

    /**
     * Given a string, find the length of the longest substring T that contains at most k distinct characters.
     *  Given s = “eceba” and k = 2, Output: 3  T is "ece" which its length is 3.
     * Input: s = "aa", k = 1 Output: 2 ; T is "aa" which its length is 2.
     * 变形 返回string!!!~~~~
     */
    public static int longestSubstringwithAtMostKDistinctCharacters(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0)
                num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0) ;
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }


    //Flatten Nested List Iterator

      /**
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * You may assume all the characters consist of printable ascii characters.
     * Input: ["h","e","l","l","o"] Output: ["o","l","l","e","h"]
     * Input: ["H","a","n","n","a","h"] Output: ["h","a","n","n","a","H"]
     */
    public String reverseStringa(String s) {
        char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++;
            j--;
        }
        return new String(word);
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


	public class DesignTicTacToe {
	    private int[] rows;
	    private int[] cols;
	    private int diagonal;
	    private int antiDiagonal;
	    
	    public DesignTicTacToe(int n) {
	        rows = new int[n];
	        cols = new int[n];
	    }
	    /**
	     * Player {player} makes a move at ({row}, {col}).
	     * @return The current winning condition, can be either:
	     * 0: No one wins.
	     * 1: Player 1 wins.
	     * 2: Player 2 wins.
	     */
	    public int move(int row, int col, int player) {
	        int toAdd = player == 1 ? 1 : -1;
	        rows[row] += toAdd;
	        cols[col] += toAdd;
	        if (row == col) {
	            diagonal += toAdd;
	        }
	        if (col == (cols.length - row - 1)) {
	            antiDiagonal += toAdd;
	        }
	        int size = rows.length;
	        if (Math.abs(rows[row]) == size ||
	                Math.abs(cols[col]) == size ||
	                Math.abs(diagonal) == size ||
	                Math.abs(antiDiagonal) == size) {
	            return player;
	        }
	        return 0;
	    }

	}

	 /**
     * Given two arrays, write a function to compute their intersection.Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
     * Note:Each element in the result should appear as many times as it shows in both arrays.
     * The result can be in any order.
     * Follow up:What if the given array is already sorted? How would you optimize your algorithm?
     * What if nums1's size is small compared to nums2's size? Which algorithm is better?
     * What if elements of nums2 are stored on disk, and the memory is limited. such that you cannot load all elements into the memory at once?
     */
    public static int[] intersectionofTwoArraysII(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int j : nums1) {
            if (map.containsKey(j)) map.put(j, map.get(j) + 1);
            else map.put(j, 1);
        }
        for (int j : nums2) {
            if (map.containsKey(j) && map.get(j) > 0) {
                result.add(j);
                map.put(j, map.get(j) - 1);
            }
        }
        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }


    //Sum of Two Integers


     /**
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     * matrix = [
     * [ 1,  5,  9],
     * [10, 11, 13],
     * [12, 13, 15]
     * ], k = 8, return 13.
     * Note: You may assume k is always valid, 1 ≤ k ≤ n2.
     */
    public int kthSmallestElementinaSortedMatrix(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        for (int j = 0; j <= n - 1; j++)
            pq.offer(new Tuple(0, j, matrix[0][j]));
        for (int i = 0; i < k - 1; i++) {
            Tuple t = pq.poll();
            if (t.x == n - 1) continue;
            pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
        }
        return pq.poll().val;
    }
    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        @Override
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }


    public class InsertDeleteGetRandom {
        ArrayList<Integer> nums;
        HashMap<Integer, Integer> locs;
        java.util.Random rand = new java.util.Random();

        public InsertDeleteGetRandom() {
            nums = new ArrayList<Integer>();
            locs = new HashMap<Integer, Integer>();
        }
        
        public boolean insert(int val) {
            boolean contain = locs.containsKey(val);
            if (contain)
                return false;
            locs.put(val, nums.size());
            nums.add(val);
            return true;
        }
        
        public boolean remove(int val) {
            boolean contain = locs.containsKey(val);
            if (!contain)
                return false;
            int loc = locs.get(val);
            if (loc < nums.size() - 1) { // not the last one than swap the last one with this val
                int lastone = nums.get(nums.size() - 1);
                nums.set(loc, lastone);
                locs.put(lastone, loc);
            }
            locs.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }

        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
    }

	 /**
	 * Shuffle a set of numbers without duplicates.
	 * Init an array with set 1, 2, and 3.int[] nums = {1,2,3};
	 * Solution solution = new Solution(nums);
	 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
	 * solution.shuffle();
	 * // Resets the array back to its original configuration [1,2,3].
	 * solution.reset();
	 * // Returns the random shuffling of array [1,2,3].
	 * solution.shuffle();
	 */
	public class ShuffleanArray {
	    private int[] nums;
	    private Random random;
	    public ShuffleanArray(int[] nums) {
	        this.nums = nums;
	        random = new Random();
	    }
	    
	    public int[] reset() {
	        return nums;
	    }
	    
	    public int[] shuffle() {
	        if (nums == null) return null;
	        int[] a = nums.clone();
	        for (int j = 1; j < a.length; j++) {
	            int i = random.nextInt(j + 1);
	            swap(a, i, j);
	        }
	        return a;
	    }
	    private void swap(int[] a, int i, int j) {
	        int t = a[i];
	        a[i] = a[j];
	        a[j] = t;
	    }
	}

    /**
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
     * s = "leetcode" return 0.
     * s = "loveleetcode",return 2.
     * Note: You may assume the string contain only lowercase letters.
     * solution is pretty straightforward. It takes O(n) and goes through the string twice:
     * Get the frequency of each character.
     * Get the first character that has a frequency of one.
     */
    public static int firstUniqueCharacterinaString(String s) {
        int freq[] = new int[26];
        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }


    //Longest Substring with At Least K Repeating Characters

     /**
     Given an integer n, return a string array answer (1-indexed) where:
     answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
     answer[i] == "Fizz" if i is divisible by 3.
     answer[i] == "Buzz" if i is divisible by 5.
     answer[i] == i (as a string) if none of the above conditions are true.
     Input: n = 15 Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
     */
    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>(n);
        for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
            fizz++;
            buzz++;
            if (fizz == 3 && buzz == 5) {
                ret.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            } else if (fizz == 3) {
                ret.add("Fizz");
                fizz = 0;
            } else if (buzz == 5) {
                ret.add("Buzz");
                buzz = 0;
            } else {
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }

//4sumII
}
```
