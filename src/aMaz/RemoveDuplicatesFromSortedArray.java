package aMaz;

class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray r = new RemoveDuplicatesFromSortedArray();
        int[] B = {1, 1, 2, 2, 3};
        System.out.println(r.removeDuplicatesFromSortedArray(B));
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

}
