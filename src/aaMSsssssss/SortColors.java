package aaMSsssssss;

public class SortColors {
        public void sortColors(int[] A) {
            if (A == null || A.length == 0)
                return;
            int i = -1; // red count, start of white
            int j = -1; // red + white count, start of blue
            for (int k = 0; k < A.length; k++) {
                int v = A[k];
                A[k] = 2; // overwrite as blue
                if (v == 0) {
                    A[++j] = 1; // write white first, then red
                    A[++i] = 0; // overwrite 1 when there is no white yet
                } else if (v == 1)
                    A[++j] = 1;
            }
        }
}
