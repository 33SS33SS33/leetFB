package aMaz;

import java.util.HashMap;
import java.util.Map;

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
class ReverseBits {
    public static void main(String[] args) {
        ReverseBits r = new ReverseBits();
        int a = 43261596;
        System.out.println(r.reverseBits(a));

        int b = 1;
        System.out.println(r.reverseBits(b));
    }

    public int reverseBits(int n) {
        int res = 0;
        // concat n's ith digit with res
        for (int i = 0; i < 32; i++)
            res = (res << 1) ^ ((n >>> i) & 1);
        return res;
    }

}
