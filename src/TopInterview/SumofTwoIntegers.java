package TopInterview;

public class SumofTwoIntegers {
    public static void main(String[] args) {
        System.out.println(new SumofTwoIntegers().getSuma(3, -2));
    }

    /**
     * Given two integers a and b, return the sum of the two integers without using the operators + and -.
     * Input: a = 1, b = 2 Output: 3
     */
    //BEST
    public int getSuma(int a, int b) {
        while (b != 0) {
            int answer = a ^ b;
            int carry = (a & b) << 1;
            a = answer;
            b = carry;
        }
        return a;
    }
}
