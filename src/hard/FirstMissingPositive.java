package hard;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 * Tags: Array
 */
class FirstMissingPositive {
    public static void main(String[] args) {
        int[] A = {1,2,0};
        System.out.println(new FirstMissingPositive().firstMissingPositive(A));
        System.out.println(new FirstMissingPositive().firstMissingPositiveB(A));
        System.out.println(new FirstMissingPositive().firstMissingPositiveC(A));
        System.out.println(new FirstMissingPositive().firstMissingPositiveAnd0D(A));
        System.out.println(new FirstMissingPositive().firstMissingPositiveD(A));
    }
    
    /**
     * Position of integer n should be n - 1 if sorted
     * Correct form [1, 2, 3, 4, ..., #, n]
     * If not in position swap it with A[A[p]-1]
     */
    public static int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) return 1;
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
            if (A[i] != i + 1) return i + 1;
        return n + 1; // nothing in middle losing, return largest
    }

    public int firstMissingPositiveB(int[] A) {
        final int N = A.length;
        next:
        for(int i = 0; i < N; i++){
            int v = A[i];
            if(v == i + 1) continue ;
            while(true){
                if(v <= 0 || v > N){
                    continue next;
                }
                int t = A[v - 1];
                if(t == v){
                    continue next;
                }
                A[v - 1] = v;
                v = t;
            }
        }
        for(int i = 0; i < N; i++){
            int v = A[i];
            if (v != i + 1){
                return i + 1;
            }
        }
        return N + 1;

    }

    public int firstMissingPositiveC(int[] nums) {
        sort(nums);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i){
                return i;
            }
        }
        return nums.length;
    }
    void sort(int[] nums){
        for(int i = 0; i < nums.length; i++){
            while(nums[i] != i){
                if(nums[i] < 0) break;

                if(nums[i] >= nums.length) {
                    nums[i] = -1;
                    break; // drop
                }
                swap(nums, nums[i], i);
            }
        }
    }
    void swap(int[] nums, int i, int j){
        int t   = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**creek*/
    int firstMissingPositiveAnd0D(int A[]) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            // when the ith element is not i
            while (A[i] != i) {
                // no need to swap when ith element is out of range [0,n]
                if (A[i] < 0 || A[i] >= n)
                    break;
                //handle duplicate elements
                if(A[i]==A[A[i]])
                    break;
                // swap elements
                int temp = A[i];
                A[i] = A[temp];
                A[temp] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (A[i] != i)
                return i;
        }
        return n;
    }

    /**creek This problem only considers positive numbers, so we need to shift 1 offset. The ith element is i+1.*/
    public int firstMissingPositiveD(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            while (A[i] != i + 1) {
                if (A[i] <= 0 || A[i] >= n)
                    break;
                if(A[i]==A[A[i]-1])
                    break;
                int temp = A[i];
                A[i] = A[temp - 1];
                A[temp - 1] = temp;
            }
        }
        for (int i = 0; i < n; i++){
            if (A[i] != i + 1){
                return i + 1;
            }
        }
        return n + 1;
    }

/*
    #Pass 1, move every value to the position of its value
    for cursor in range(N):
        target = array[cursor]
        while target < N and target != array[target]:
            new_target = array[target]
            array[target] = target
            target = new_target

    #Pass 2, find first location where the index doesn't match the value
    for cursor in range(N):
        if array[cursor] != cursor:
            return cursor
    return N
*/
}