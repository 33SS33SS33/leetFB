package medium;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 * 
 * Tags: Math, Binary Search
 */
class DivideTwoIntegers {

    public static void main(String[] args) {
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divide(100, 3));
    }
    
    /**
     * Take care of special cases, 0, +1, -1
     * dividend = a0 * 1 * divisor + a1 * 2 * divisor + a2 * 2^2 * divisor...
     * ai can be 0 or 1, set it to 1 if dividend >= ai * 2^i * divisor
     * All ais added up to result
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        final boolean neg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long ldividend = Math.abs((long)dividend); // convert to abstract long
        final long ldivisor = Math.abs((long)divisor);
        int res = 0;
        for (int bit = Integer.SIZE - 1; bit >= 0 && ldividend >= ldivisor; bit--) { // bit from 31 to 0, dividend >= divisor
            if (ldividend >= (ldivisor << bit)) {
                res |= 1 << bit; // set 1 in relative bit in result
                ldividend -= ldivisor << bit;
            }
        }
        return neg ? -res : res;
    }

    /**creek---- is O(logn).
     * This problem can be solved based on the fact that any number can be converted to the format of the following:

     num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n*/
    public int divideB(int dividend, int divisor) {
        //handle special cases
        if(divisor==0) return Integer.MAX_VALUE;
        if(divisor==-1 && dividend == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        //get positive values
        long pDividend = Math.abs((long)dividend);
        long pDivisor = Math.abs((long)divisor);

        int result = 0;
        while(pDividend>=pDivisor){
            //calculate number of left shifts
            int numShift = 0;
            while(pDividend>=(pDivisor<<numShift)){
                numShift++;
            }

            //dividend minus the largest shifted divisor
            result += 1<<(numShift-1);
            pDividend -= (pDivisor<<(numShift-1));
        }

        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            return result;
        }else{
            return -result;
        }
    }
}
