package LeetDaily.medium;

import java.util.Arrays;

public class NumOfDiceRolls1155 {
    static final int MOD = (int) 1e9 + 7;
    public static void main(String[] args) {
        System.out.println(numRollsToTarget(3,2,4));
    }
    public static int numRollsToTarget(int n, int k, int target) {
        Integer[][] memo = new Integer[n + 1][target + 1];
        int res =  waysToTarget(memo, n, k, target, 0, 0);
        for(Integer[] g : memo) {
            System.out.println(Arrays.toString(g));
        }
        return res;
    }
    private static int waysToTarget(Integer[][] memo, int n, int k, int target, int diceIndex, int currSum) {
        if(diceIndex == n) {
            return currSum == target ? 1 : 0;
        }
        if(memo[diceIndex][currSum] != null) {
            return memo[diceIndex][currSum];
        }
        int ways = 0;
        for(int i = 1 ; i <= Math.min(k, target - currSum) ; i++) {
            System.out.println("diceIndex: "+ diceIndex + ", currSum: "+ currSum + ", i: "+ i);
            ways = (ways + waysToTarget(memo, n, k, target, diceIndex + 1, currSum + i)) % MOD;
            System.out.println("ways: "+ ways);
        }
        return memo[diceIndex][currSum] = ways;
    }
}

/*
You have n dice, and each die has k faces numbered from 1 to k.
Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
Example 1:
Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.
Example 2:
Input: n = 2, k = 6, target = 7
Output: 6
Explanation: You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
Example 3:
Input: n = 30, k = 30, target = 500
Output: 222616187
Explanation: The answer must be returned modulo 109 + 7.
Constraints:
1 <= n, k <= 30
1 <= target <= 1000
 */