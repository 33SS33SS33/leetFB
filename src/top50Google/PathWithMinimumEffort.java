package top50Google;

public class PathWithMinimumEffort {
    /**
     * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
     * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
     * Output: 2
     * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
     * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
     */

    public int minimumEffortPath(int[][] heights) {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dfsUtil(heights, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    boolean dfsUtil(int[][] heights, int mid) {
        int row = heights.length;
        int col = heights[0].length;
        boolean visited[][] = new boolean[row][col];
        return canReachDestinaton(0, 0, heights, visited, row, col, mid);
    }

    boolean canReachDestinaton(int x, int y, int[][] heights,
                               boolean[][] visited, int row, int col, int mid) {
        if (x == row - 1 && y == col - 1) {
            return true;
        }
        visited[x][y] = true;
        for (int[] direction : directions) {
            int adjacentX = x + direction[0];
            int adjacentY = y + direction[1];
            if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[x][y]);
                if (currentDifference <= mid) {
                    if (canReachDestinaton(adjacentX, adjacentY, heights, visited, row, col, mid))
                        return true;
                }
            }
        }
        return false;
    }

    boolean isValidCell(int x, int y, int row, int col) {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }
}
