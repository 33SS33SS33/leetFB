package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class RectangleArea {

    /**creek---*/
    public int computeAreaB(int A, int B, int C, int D, int E, int F, int G, int H) {
        if(C<E||G<A )
            return (G-E)*(H-F) + (C-A)*(D-B);

        if(D<F || H<B)
            return (G-E)*(H-F) + (C-A)*(D-B);

        int right = Math.min(C,G);
        int left = Math.max(A,E);
        int top = Math.min(H,D);
        int bottom = Math.max(F,B);

        return (G-E)*(H-F) + (C-A)*(D-B) - (right-left)*(top-bottom);
    }


    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // make sure left is left
        if(A > E){
            return computeArea(E, F, G, H, A, B, C, D);
        }
        int a = area(A, B, C, D) + area(E, F, G, H);
        // no share case1
        // [  ]C
        //       E[  ]
        if(C < E){
            return a;
        }
        // no share case2
        // [   ]B
        //
        //   [     ]H
        if(B > H || F > D){
            return a;
        }
        // remove share
        return a - area(E, Math.max(B, F), Math.min(C, G), Math.min(D, H));

    }
    int area(int x1, int y1, int x2, int y2){
        return (y2 - y1) * (x2 - x1);
    }


}
