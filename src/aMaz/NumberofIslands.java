package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2015/12/29.
 */
public class NumberofIslands {
    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(new NumberofIslands().NumberofIslands(grid));
    }

    /**
     * 11110
     * 11010
     * 11000
     * 00000  Output: 1
     * <p>
     * 11000
     * 11000
     * 00100
     * 00011 answer：3
     * 使用DFS即可 设置一个矩阵记录有没有访问过
     * 然后遍历grid 如果这个点为1且没访问过 就调用dfs开始查找他附近都是1的点
     * 然后visit都置为True 这样就说明找到了一个岛
     * dfs的时候 少一层递归会快很多  所以才直接限定了dfs的调用
     * 也可以用 union find来做 下次可以试试
     */
    public int NumberofIslands(char[][] grid) {
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
        if (i < 0 || j < 0 || i > grid.length - 1
                || j > grid[0].length - 1 || grid[i][j] != '1')
            return;
        //set visited cell to '0'
        grid[i][j] = '0';
        //merge all adjacent land
        merge(grid, i - 1, j);
        merge(grid, i + 1, j);
        merge(grid, i, j - 1);
        merge(grid, i, j + 1);
    }

}
