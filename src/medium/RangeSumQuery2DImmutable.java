package medium;

/**
 * Created by shanshan on 16/5/9.
 */
/**"用一个辅助矩阵每一个元素表示的是从左上角的起点到当前点得所有的和
 然后这个矩阵可以行列都比原来的矩阵大1 这样比较好处理边界问题
 生成以后就返回 当前右下角元素的值 - (左下角旁边的值 + 右上角上边的值 - 左上角的2对角的那个5) 因为被加了两次"

 "Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 Range Sum Query 2D
 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 Example:
 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]
 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12
 Note:
 You may assume that the matrix does not change.
 There are many calls to sumRegion function.
 You may assume that row1 ≤ row2 and col1 ≤ col2."*/
public class RangeSumQuery2DImmutable {
}
