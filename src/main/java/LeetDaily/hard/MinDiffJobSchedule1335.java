package LeetDaily.hard;


public class MinDiffJobSchedule1335 {
    public static void main(String[] args) {
        int[] jobDifficulty = {6,5,4,3,2,1};
        int d = 2;
        System.out.println(minDifficulty(jobDifficulty, d));
    }
    public static int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(n < d) return -1;
        int[][] memo = new int[n][d+1];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j <= d ; j++) {
                memo[i][j] = -1;
            }
        }
        return minDiff(jobDifficulty, memo, n, d,0);
    }
    private static int minDiff(int[] jobDifficulty, int[][] memo, int n, int daysRemaining, int i) {
        if(memo[i][daysRemaining] != -1) {
            return memo[i][daysRemaining];
        }
        if(daysRemaining == 1) {
            int res = 0;
            for(int j = i ; j < n ; j++) {
             res = Math.max(jobDifficulty[j], res);
            }
            return res;
        }
        int res = Integer.MAX_VALUE;
        int dailyMaxJobDiff = 0;
        for(int j = i ; j < n - daysRemaining + 1 ; j++) {
            dailyMaxJobDiff = Math.max(jobDifficulty[j], dailyMaxJobDiff);
            res = Math.min(res, dailyMaxJobDiff +  minDiff(jobDifficulty, memo, n,daysRemaining - 1, j + 1));
        }
        memo[i][daysRemaining] = res;
        return res;
    }
}

/*
You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7
Example 2:
Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
Example 3:
Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
Constraints:
1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10

Approach 1: Top down memo DP;
Complexity Analysis:
Let n be the length of the jobDifficulty array, and ddd be the total number of days.
Time complexity: O(n^2.d)
since there are n⋅d possible states, and we need O(n) time to calculate the result for each state.
Space complexity: O(n⋅d) space is required to memoize all n⋅d states.
 */