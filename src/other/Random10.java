package other;

/**
 * Created by GAOSHANSHAN835 on 2016/4/18.
 */
public class Random10 {
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }

    public int rand7() {
        return 1;
    }

}
