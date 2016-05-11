package hard;

/**
 * Created by GAOSHANSHAN835 on 2016/5/11.
 */
/**"You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 Each 0 marks an empty land which you can pass by freely.
 Each 1 marks a building which you cannot pass through.
 Each 2 marks an obstacle which you cannot pass through.
 For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 1 - 0 - 2 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0
 The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 Note:
 There will be at least one building. If it is not possible to build such house according to the above rules, return -1."
 */
/**"使用bfs即可 最关键的地方在于建立一个同样的矩阵记录距离
 然后原先的矩阵就当碰见一个building的时候 就bfs一下它到所有其他点得距离 这里比较方便的就是 可以使用-1 -2 -3来给building编号 这样每次只用检查这个点是不是等于id+1 就可以知道这个点有没有走过
 最后的无解的判断也是通过id来进行的"	*/
public class ShortestDistancefromAllBuildings {
}
