package amaoa;

import java.util.*;

/**
 * 注意：具体抽到的题目可能会有细微变化。
 * 让小明帮公司球场修场地，给一个二维的链表fields，场地里有坑，不能走。 场地里有树要砍掉。最后目的返回是修好一层的场地的最小步数。
 * public int flatFields (int numRows, int numColumns, List<List<Integer>> fields) {}.
 * [1, 3, 0, 2].
 * [1, 1, 3, 1]
 * 上图中的1代表平地，可以走。 0代表坑，不能走。 大于1的数字代表树木，需要砍掉。规则是从上下左右四个角开始任选一个开始走，先砍数字小的树木。比如2 < 3，那么就得先走2。
 * 上图如果从右下角开始走依次经过的坐标是： （1， 3） -> (0, 3) -> (1, 3) -> (1, 2) -> (1, 1) -> (1, 0) 所以返回的最小步数是5，
 * 因为通过这个路径可以修平第二层的球场[1, 1, 3, 1]， 并且走到左下角终点。
 * [1, 0]
 * [3, 2]
 * 上图中的最小步数返回-1因为，没有办法修好一层， 因为从左上角1开始走，不能走到0， 也不能走3， 因为在全局中3比2大，必须先走2。所以就没法走了。
 * <p>-----------------------------------
 * 解题思路参考：
 * BFS来按照砍树顺序寻找最优路径。坑：先要找到所有树（>1的点），然后按照顺序做BFS。可以把树放到TreeMap里面（排序的HashMap）。
 * 抽到的题目可能起始点是0.0也可能是四个角，没关系把起始点都放入queue中去做BFS即可。
 * 再有注意终点可能会是最后一棵树，也可能是最优路径走出来（从最后一棵数到任意四个点的最短路径）。参考答案是从四个点开始走最后走出来的解法。
 */
public class GolfEventCutTree {
    public static void main(String[] args) {
        int n = 2, m = 4;
        List<List<Integer>> fields = new ArrayList<>();
        fields.add(Arrays.asList(1, 3, 0, 2));
        fields.add(Arrays.asList(1, 1, 3, 1));

		PriorityQueue<int[]> treelist = new PriorityQueue<>(
                Comparator.comparingInt(tree -> fields.get(tree[0]).get(tree[1])));
		int[][] map = new int[n][m];

		for (int i = 0;i<n;i++){
			for (int j = 0;j< m;j++){
				map[i][j] = fields.get(i).get(j);
				if (fields.get(i).get(j) > 1){
					treelist.add(new int[]{i,j});
				}
			}
		}
		while(!treelist.isEmpty()){

			System.out.println(treelist.peek()[0]+"->"+treelist.peek()[1]);
			treelist.poll();
		}
        System.out.println(flatFields(n, m, fields));
    }

    public static int flatFields(int numRows, int numColumns, List<List<Integer>> fields) {
        PriorityQueue<int[]> treelist = new PriorityQueue<>(
                Comparator.comparingInt(tree -> fields.get(tree[0]).get(tree[1])));
        int[][] map = new int[numRows][numColumns];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                map[i][j] = fields.get(i).get(j);
                if (fields.get(i).get(j) > 1) {
                    treelist.add(new int[]{i, j});
                }
            }
        }
        int sum = 0;
        int[] start = new int[]{0, 0};
        int[] end;
        int res;
        while (!treelist.isEmpty()) {
            end = treelist.remove();
            int tH = map[end[0]][end[1]];
            map[end[0]][end[1]] = 1;
            res = shortestBFS(map, start, end);
            if (res == -1) {
                return -1;
            }
            sum += (res + tH);
            start = end;
        }
        return sum;
    }

    ///**********************************BFS*******************************
    public static int shortestBFS(int[][] maze, int[] start, int[] dest) {
        int m = maze.length, n = maze[0].length;
        int[][] path = new int[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int x = curr[0], y = curr[1], count = 0;
                while (x + dirs[d] >= 0 && x + dirs[d] < m && y + dirs[d + 1] >= 0 && y + dirs[d + 1] < n &&
                        maze[x + dirs[d]][y + dirs[d + 1]] == 1) {
                    x += dirs[d];
                    y += dirs[d + 1];
                    count++;
                }
                if ((x != start[0] || y != start[1]) &&
                        (path[x][y] == 0 || path[x][y] > path[curr[0]][curr[1]] + count)) {
                    path[x][y] = path[curr[0]][curr[1]] + count;
                    q.offer(new int[]{x, y});
                }
            }
        }
        return path[dest[0]][dest[1]] == 0 ?
                -1 : path[dest[0]][dest[1]];
    }

}
