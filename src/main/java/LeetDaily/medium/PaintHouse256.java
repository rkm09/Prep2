package LeetDaily.medium;


import GenDS.Pair;

import java.util.HashMap;
import java.util.Map;

public class PaintHouse256 {
    private int[][] costs;
//    or could have done string concat instead of pair
    private static Map<Pair<Integer, Integer>, Integer> memo;
    public static void main(String[] args) {
        PaintHouse256 ph = new PaintHouse256();
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        System.out.println(ph.minCost(costs));
    }
    public int minCost(int[][] costs) {
        int cost = 0;
        int m = costs.length;
        int[][] dp = new int[m][3];
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < m ; j++) {
//                dp[i][0] = costs[i][0] + costs[1]
            }

        }
        return cost;
    }

//    memoized recursion; time: O(n), space: O(n)
    public int minCost2(int[][] costs) {
        if(costs.length == 0) return 0;
        this.costs = costs;
        memo = new HashMap<>();
        return Math.min(paintCost(0, 0), Math.min(paintCost(0, 1), paintCost(0, 2)));
    }
    private int paintCost(int house, int color) {
        int totalCost = costs[house][color];
        if(memo.containsKey(new Pair<>(house, color))) {
            return memo.get(new Pair<>(house, color));
        }
        if(house == costs.length - 1) {}
        else if(color == 0) {
            totalCost += Math.min(paintCost(house + 1, 1), paintCost(house + 1, 2));
        } else if(color == 1) {
            totalCost += Math.min(paintCost(house + 1, 0), paintCost(house + 1, 2));
        } else if(color == 2) {
            totalCost += Math.min(paintCost(house + 1, 0), paintCost(house + 1, 1));
        }
        memo.put(new Pair<>(house, color), totalCost);
        return totalCost;
    }

//    [TLE] brute force with recursion; O(2^n), space: O(n)
    public int minCostX(int[][] costs) {
        if(costs.length == 0) return 0;
        this.costs = costs;
        return Math.min(paintCostX(0, 0), Math.min(paintCostX(0, 1), paintCostX(0, 2)));
    }
    private int paintCostX(int house, int color) {
        int totalCost = costs[house][color];
        if(house == costs.length - 1) {}
        else if(color == 0) {
            totalCost += Math.min(paintCostX(house + 1, 1), paintCostX(house + 1, 2));
        } else if(color == 1) {
            totalCost += Math.min(paintCostX(house + 1, 0), paintCostX(house + 1, 2));
        } else if(color == 2) {
            totalCost += Math.min(paintCostX(house + 1, 0), paintCostX(house + 1, 1));
        }
        return totalCost;
    }
}


/*
There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
Return the minimum cost to paint all houses.
Example 1:
Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.
Example 2:
Input: costs = [[7,6,2]]
Output: 2
Constraints:
costs.length == n
costs[i].length == 3
1 <= n <= 100
1 <= costs[i][j] <= 20
 */
