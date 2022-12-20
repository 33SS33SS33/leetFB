package TopInterview;

/**
 * Given a non-negative number represented as an array of digits,<strong>plus one</strong> to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Tags: Array, Math
 */
class PlusOne {
    public static void main(String[] args) {
        int[] result = plusOnew(new int[]{9, 9, 9, 9, 8});
        System.out.print("{ ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println("}");
        int[] result2 = plusOnew(new int[]{9, 9, 9, 9, 9});
        System.out.print("{ ");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + " ");
        }
        System.out.println("}");
    }

    //best
    public static int[] plusOnew(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
