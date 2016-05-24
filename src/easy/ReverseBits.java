package easy;

import java.util.*;

/**
 * Reverse bits of a given 32 bits <strong>unsigned</strong> integer.
 * Example:
 * input 43261596, represented in binary as 00000010100101000001111010011100
 * return 964176192, represented in binary as 00111001011110000010100101000000
 * Follow up:
 * If this function is <strong>called many times</strong>, how would you
 * optimize it?
 * Answer:
 * Cache result for each bytes.
 * Related problem: Reverse Integer
 * Tags: Bit Manipulation
 */

/**
 * 优化的办法就是先把0000 - 1111的翻转都存起来 这样就可以四位四位的截取 然后查询了
 */
class ReverseBits {
    public static void main(String[] args) {
        ReverseBits r = new ReverseBits();
        int a = 43261596;
        System.out.println(r.reverseBits(a));
        System.out.println(r.reverseBitsOpt(a));
        System.out.println(r.reverseBitsC(a));

        int b = 1;
        System.out.println(r.reverseBits(b));
        System.out.println(r.reverseBitsOpt(b));
        System.out.println(r.reverseBitsC(b));
    }

    /**
     * O(1) Time, O(1) Space
     * Move res 1 bit left, a
     * Get first bit of n, b
     * res = a ^ b
     * Move n right 1 bit for next loop
     * Unsigned shift means fill new bit at the left with 0 instead of 1
     */
    public int reverseBits(int n) {
        int res = 0;
        // concat n's ith digit with res
        for (int i = 0; i < 32; i++)
            res = (res << 1) ^ ((n >>> i) & 1);
        return res;
    }

    private Map<Byte, Integer> cache = new HashMap<Byte, Integer>();

    /**
     * O(1) Time, O(1) Space
     * Divide 32 bits into 4 bytes
     * Cache each byte and its reversed result in a hashmap
     * Check cache for result first instead of computing all
     */
    public int reverseBitsOpt(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++)
            bytes[i] = (byte) ((n >>> 8 * i) & 0xFF);
        int res = 0;
        for (int i = 0; i < 4; i++)
            res = (res << 8) ^ reverseBytes(bytes[i]);
        return res;
    }

    public int reverseBytes(byte b) {
        if (cache.containsKey(b))
            return cache.get(b);
        int res = 0;
        for (int i = 0; i < 8; i++) {
            res = (res << 1) ^ ((b >>> i) & 1);
        }
        cache.put(b, res);
        return res;
    }

    /**
     * creek
     */
    public int reverseBitsC(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }
        return n;
    }

    public int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;
        if ((a ^ b) != 0) {
            return n ^= (1 << i) | (1 << j);
        }
        return n;
    }
}
