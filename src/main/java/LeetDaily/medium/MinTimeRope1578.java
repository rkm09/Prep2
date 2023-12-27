package LeetDaily.medium;

import java.util.HashSet;
import java.util.Set;

public class MinTimeRope1578 {
    public static void main(String[] args) {
        int[] neededTime = {3,5,10,7,5,3,5,5,4,8,1};
        String colors = "aaabbbabbbb";
        System.out.println(minCost(colors, neededTime));
    }
    public static int minCost(String colors, int[] neededTime) {
        int minTime = 0, n = colors.length();
        if(n == 1) return minTime;
        char[] carr = colors.toCharArray();
        for(int i = 1 ; i < n ; i++) {
            char c1 = carr[i-1];
            char c2 = carr[i];
            if(c1 == c2) {
                minTime += Math.min(neededTime[i], neededTime[i-1]);
                neededTime[i] = Math.max(neededTime[i-1], neededTime[i]);
            }
        }
        return minTime;
    }
}

/*
Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.
Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.
Return the minimum time Bob needs to make the rope colorful.
Input: colors = "abaac", neededTime = [1,2,3,4,5]
Output: 3
Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
Bob can remove the blue balloon at index 2. This takes 3 seconds.
There are no longer two consecutive balloons of the same color. Total time = 3.
Input: colors = "abc", neededTime = [1,2,3]
Output: 0
Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.
Input: colors = "aabaa", neededTime = [1,2,3,4,1]
Output: 2
Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.
Constraints:
n == colors.length == neededTime.length
1 <= n <= 105
1 <= neededTime[i] <= 104
colors contains only lowercase English letters.
 */