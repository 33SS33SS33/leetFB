package medium;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */
public class PaintHouse {
    static final int RED   = 001;
    static final int BLUE  = 100;
    static final int GREEN  = 011;

    static final int NONE  = 000;
    static final int ALL   = 111;

    static final int[] COLORS = {RED, BLUE, GREEN};

    int index(int color){
        return color / 2;
    }

    int min(int[] costs, int exclude){
        int includes = ~(ALL & exclude);

        int min = Integer.MAX_VALUE;
        for(int c : COLORS){
            if((c & includes) == c){
                min = Math.min(costs[index(c)], min);
            }
        }

        return min;
    }

    public int minCost(int[][] costs) {
        if(costs.length == 0) return 0;

        int[][] minCosts = new int[costs.length][COLORS.length];

        for(int c : COLORS){
            minCosts[0][index(c)] = costs[0][index(c)];
        }

        for(int i = 1; i < costs.length; i++){
            for(int c : COLORS){
                minCosts[i][index(c)] = costs[i][index(c)] + min(minCosts[i - 1], c);
            }
        }

        return min(minCosts[costs.length - 1], NONE);
    }
}
