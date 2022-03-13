package aMaz;

class NextPermutation {
    public static void main(String[] args) {
        int[] num = {1, 3, 2};
        nextPermutation(num);
        for (int i : num) {
            System.out.print(i + ",");
        }
    }

    /**
     * Implement next permutation, which rearranges numbers into the
     * lexicographically next greater permutation of numbers.
     * If such arrangement is not possible, it must rearrange it as the lowest
     * possible order (ie, sorted in ascending order).
     * The replacement must be in-place, do not allocate extra memory.
     * Here are some examples. Inputs are in the left-hand column and its
     * corresponding outputs are in the right-hand column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * 思路： 如果一个数列是降序排列的（从后往前看就是升序） 那它就是不能再置换了 只能将他从新按升序排列
     * 所以每一遍都从后往前 一共扫描三遍
     * 第一遍先找到那个打破升序排列的数字(从后往前)
     * 第二遍从后往前找到第一个比打破升序的数字大的数字 然后置换二者
     * 第三遍就是把从打破升序的位置之后剩下的数列按升序排列（从前往后看） 这样的数字才最接近之前的数字
     * O(n) Time Move pointer to second last element of ascending order
     * From that index, find the farthest element that is bigger Swap these two elements
     * Reverse the order from the next index
     */
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
}
