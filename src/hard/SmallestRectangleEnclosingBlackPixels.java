package hard;

/**
 * Created by shanshan on 16/5/9.
 */
/**"肯定可以用dfs来做  但是效率比较慢 比较快的是可以用binary search
 这题最关键的前提是 只有一个黑色的区域 所以我们可以用binary search 分别找出来这个区域的轮廓  就是最上面的一个还有1的行  最下面的含有1的行 最左面含有1的列 和最右边含有1的列"

 "An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
 The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically.
 Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 For example, given the following image:
 [
 "0010",
 "0110",
 "0100"
 ]
 and x = 0, y = 2,
 Return 6."*/
public class SmallestRectangleEnclosingBlackPixels {
}
