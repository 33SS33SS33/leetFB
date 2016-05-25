package rectangle;

/**
 * Created by GAOSHANSHAN835 on 2015/12/29.
 * 11000
 * 11000
 * 00100
 * 00011
 * answer：3
 */

/**
 * 使用DFS即可 设置一个矩阵记录有没有访问过
 * 然后遍历grid 如果这个点为1且没访问过 就调用dfs开始查找他附近都是1的点 然后visit都置为True 这样就说明找到了一个岛
 * dfs的时候 少一层递归会快很多  所以才直接限定了dfs的调用
 * 也可以用 union find来做 下次可以试试
 */
public class NumIslands {
    public static void main(String[] args) {
        char[][] grid = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } };
        char[][] grid2 = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } };
        System.out.println(new NumIslands().numIslands(grid));
        System.out.print(new NumIslands().numIslands2(grid2));
    }

    /**
     * creek-----
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    merge(grid, i, j);
                }
            }
        }
        return count;
    }

    public void merge(char[][] grid, int i, int j) {
        //validity checking
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1)
            return;
        //if current cell is water or visited
        if (grid[i][j] != '1')
            return;
        //set visited cell to '0'
        grid[i][j] = '0';
        //merge all adjacent land
        merge(grid, i - 1, j);
        merge(grid, i + 1, j);
        merge(grid, i, j - 1);
        merge(grid, i, j + 1);
    }

    /**
     * 第二种
     */
    public int numIslands2(char[][] grid) {
        final int mx = grid.length;
        if (mx == 0)
            return 0;
        final int my = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[mx][my];
        for (int x = 0; x < mx; x++) {
            for (int y = 0; y < my; y++) {
                if (allowed(x, y, mx, my, grid, visited)) {
                    travel(x, y, mx, my, grid, visited);
                    count++;
                }
            }
        }
        return count;
    }

    boolean allowed(int x, int y, final int mx, final int my, char[][] grid, boolean[][] visited) {
        return (x < mx) && (x >= 0) && (y < my) && (y >= 0) && (grid[x][y] == '1')
                && (!visited[x][y]);
    }

    void travel(int x, int y, final int mx, final int my, char[][] grid, boolean[][] visited) {
        // x - 1, y
        // x + 1, y
        // x, y - 1
        // x, y + 1
        visited[x][y] = true;
        for (int[] xy : new int[][] { { x - 1, y }, { x + 1, y }, { x, y - 1 }, { x, y + 1 } }) {
            int _x = xy[0];
            int _y = xy[1];
            if (allowed(_x, _y, mx, my, grid, visited)) {
                travel(_x, _y, mx, my, grid, visited);
            }
        }
    }

}
