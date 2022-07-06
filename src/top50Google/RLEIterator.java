package top50Google;

public class RLEIterator {
    /**
     * We can use run-length encoding (i.e., RLE) to encode a sequence of integers. In a run-length encoded array of even length encoding (0-indexed), for all even i, encoding[i] tells us the number of times that the non-negative integer value encoding[i + 1] is repeated in the sequence.
     * For example, the sequence arr = [8,8,8,5,5] can be encoded to be encoding = [3,8,2,5]. encoding = [3,8,0,9,2,5] and encoding = [2,8,1,8,2,5] are also valid RLE of arr.
     * Given a run-length encoded array, design an iterator that iterates through it.
     * Implement the RLEIterator class:
     * RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
     * int next(int n) Exhausts the next n elements and returns the last element exhausted in this way. If there is no element left to exhaust, return -1 instead.
     * Example 1:
     * Input
     * ["RLEIterator", "next", "next", "next", "next"]
     * [[[3, 8, 0, 9, 2, 5]], [2], [1], [1], [2]]
     * Output
     * [null, 8, 8, 5, -1]
     */
    int index;
    int[] A;

    public RLEIterator(int[] A) {
        this.A = A;
        index = 0;
    }

    public int next(int n) {
        while (index < A.length && n > A[index]) {
            n = n - A[index];
            index += 2;
        }

        if (index >= A.length) {
            return -1;
        }

        A[index] = A[index] - n;
        return A[index + 1];
    }
}
