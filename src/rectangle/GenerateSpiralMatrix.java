package rectangle;

/**
 * Created by GAOSHANSHAN835 on 2015/12/29.
 */
public class GenerateSpiralMatrix {

    public static void main(String[] args) {

        int[][] matrix= new GenerateSpiralMatrix().generateMatrix(4);
        for(int[] i:matrix){
            for(int j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    public int[][] generateMatrix(int n) {
        int total = n*n;
        int[][] result= new int[n][n];

        int x=0;
        int y=0;
        int step = 0;

        for(int i=0;i<total;){
            while(y+step<n){
                i++;
                result[x][y]=i;
                y++;

            }
            y--;
            x++;

            while(x+step<n){
                i++;
                result[x][y]=i;
                x++;
            }
            x--;
            y--;

            while(y>=0+step){
                i++;
                result[x][y]=i;
                y--;
            }
            y++;
            x--;
            step++;

            while(x>=0+step){
                i++;
                result[x][y]=i;
                x--;
            }
            x++;
            y++;
        }
        return result;
    }
}
