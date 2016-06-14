package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 * 题目让求得是两个矩形加起来覆盖的面积 不是相交的面积！！！
 * 解法就是面积A加上面积B减去相交的面积  相交面积的解法 注意一下两个面积没相交的时候
 * 怎么判断 这个时候你求出来的假设相交的左下角和右上角一定是非法的 （相减负值） 所以就用0顶替了 因为没相交
 */
public class RectangleArea {

    public int computeAreaa(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaOfSqrA = (C - A) * (D - B);
        int areaOfSqrB = (G - E) * (H - F);
        int left = Math.max(A, E);
        int right = Math.min(G, C);
        int bottom = Math.max(F, B);
        int top = Math.min(D, H);
        //If overlap
        int overlap = 0;
        if (right > left && top > bottom)
            overlap = (right - left) * (top - bottom);
        return areaOfSqrA + areaOfSqrB - overlap;
    }

    /**
     * creek---
     */
    public int computeAreaB(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (C < E || G < A)
            return (G - E) * (H - F) + (C - A) * (D - B);
        if (D < F || H < B)
            return (G - E) * (H - F) + (C - A) * (D - B);
        int right = Math.min(C, G);
        int left = Math.max(A, E);
        int top = Math.min(H, D);
        int bottom = Math.max(F, B);
        return (G - E) * (H - F) + (C - A) * (D - B) - (right - left) * (top - bottom);
    }

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // make sure left is left
        if (A > E) {
            return computeArea(E, F, G, H, A, B, C, D);
        }
        int a = area(A, B, C, D) + area(E, F, G, H);
        // no share case1
        // [  ]C
        //       E[  ]
        if (C < E) {
            return a;
        }
        // no share case2
        // [   ]B
        //
        //   [     ]H
        if (B > H || F > D) {
            return a;
        }
        // remove share
        return a - area(E, Math.max(B, F), Math.min(C, G), Math.min(D, H));
    }

    int area(int x1, int y1, int x2, int y2) {
        return (y2 - y1) * (x2 - x1);
    }

}
