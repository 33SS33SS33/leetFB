package aMaz;


class DivideTwoIntegers {
    public static void main(String[] args) {
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divideTwoIntegers(100, 3));
//        System.out.println(d.divideTwoIntegersb(100, 3));
//        System.out.println(d.divideTwoIntegersc(100, 3));
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

    //http://blog.csdn.net/linhuanmars/article/details/20024907

    /**
     * Take care of special cases, 0, +1, -1
     * dividend = a0 * 1 * divisor + a1 * 2 * divisor + a2 * 2^2 * divisor...
     * ai can be 0 or 1, set it to 1 if dividend >= ai * 2^i * divisor
     * All ais added up to result
     */
/*    public int divideTwoIntegers(int dividend, int divisor) {
        if (divisor == 0)
            return Integer.MAX_VALUE;
        if (dividend == 0)
            return 0;
        if (divisor == 1)
            return dividend;
        if (divisor == -1)
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        final boolean neg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long ldividend = Math.abs((long) dividend); // zigZagConversion to abstract long
        final long ldivisor = Math.abs((long) divisor);
        int res = 0;
        for (int bit = Integer.SIZE - 1;
             bit >= 0 && ldividend >= ldivisor; bit--) { // bit from 31 to 0, dividend >= divisor
            if (ldividend >= (ldivisor << bit)) {
                res |= 1 << bit; // set 1 in relative bit in result
                ldividend -= ldivisor << bit;
            }
        }
        return neg ? -res : res;
    }*/

    /**
     * creek---- is O(logn).
     * This problem can be solved based on the fact that any number can be converted to the format of the following:
     * num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n
     */
/*    public int divideTwoIntegersb(int dividend, int divisor) {
        //handle special cases
        if (divisor == 0)
            return Integer.MAX_VALUE;
        if (divisor == -1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
        //get positive values
        long pDividend = Math.abs((long) dividend);
        long pDivisor = Math.abs((long) divisor);
        int result = 0;
        while (pDividend >= pDivisor) {
            //calculate number of left shifts
            int numShift = 0;
            while (pDividend >= (pDivisor << numShift)) {
                numShift++;
            }
            //dividend minus the largest shifted divisor
            result += 1 << (numShift - 1);
            pDividend -= (pDivisor << (numShift - 1));
        }
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return result;
        } else {
            return -result;
        }
    }*/

    /**
     * 因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度为O(logn)
     * http://blog.csdn.net/linhuanmars/article/details/20024907
     */
/*    public int divideTwoIntegersc(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        boolean isNeg = (dividend ^ divisor) >>> 31 == 1;
        int res = 0;
        if (dividend == Integer.MIN_VALUE) {
            dividend += Math.abs(divisor);
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
            res++;
        }
        if (divisor == Integer.MIN_VALUE) {
            return res;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int digit = 0;
        while (divisor <= (dividend >> 1)) {
            divisor <<= 1;
            digit++;
        }
        while (digit >= 0) {
            if (dividend >= divisor) {
                res += 1 << digit;
                dividend -= divisor;
            }
            divisor >>= 1;
            digit--;
        }
        return isNeg ? -res : res;
    }*/

}
