package aMaz;

/**
 * Created by GAOSHANSHAN835 on 2016/1/6.
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 * Tags: Greedy
 * 复杂度是O(n)
 * 如果所有站点拥有的gas小于所有站点的cost 那么肯定无解 所以就返回-1
 * 如果能从一个站点能到另一个站点 那就说明 一定之前从一个起点开始 能到达这个点
 * 所以如果发现当前站点 不能到下一个站点 就将起始位置设置在下一个站点
 * 这样转了一圈之后 那个起始位置就是正确的
 */
class GasStation {
    public static void main(String[] args) {
        int[] gas = {2, 4, 1, 6};
        int[] cost = {1, 4, 1, 5};
        System.out.println(gasStation(gas, cost));
    }

    /**
     * Use restGas to store the gas left for current trip
     * Use previous to store the gas needed for previous trips
     * Go through the list and calculate restGas
     * If restGas < 0, update previous, reset restGas, set start index from next
     * If previous + restGas >= 0, which means there is a solution, return start
     * Otherwise can't make the trip, return -1
     */
    public static int gasStation(int[] gas, int[] cost) {
        int restGas = 0; // gas remain for current trip
        int previous = 0; // negative gas for previous trips
        int start = 0; // start index of current trip
        for (int i = 0; i < gas.length; i++) {
            restGas += gas[i] - cost[i];
            if (restGas < 0) {
                previous += restGas; // gas needed for previous trips
                restGas = 0; // reset restGas
                start = i + 1; // set start index to next station
            }
        }
        return previous + restGas >= 0 ? start : -1;
    }

}
