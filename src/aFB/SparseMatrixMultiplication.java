package aFB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 * "Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 * Example:
 * A = [
 * [ 1, 0, 0],
 * [-1, 0, 3]
 * ]
 * B = [
 * [ 7, 0, 0 ],
 * [ 0, 0, 0 ],
 * [ 0, 0, 1 ]
 * ]
 * |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 * | 0 0 1 |"
 * 模拟矩阵乘法即可  乘法之前可以先检查一下这个位置是否为0来加速
 */
public class SparseMatrixMultiplication {
    /**
     * 最好的
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0)
                            C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;
    }

}
