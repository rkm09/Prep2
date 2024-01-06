package LeetDaily.hard;

import java.util.*;

public class JobScheduling1235 {
    public static void main(String[] args) {
        int[] startTime = {6,15,7,11,1,3,16,2};
        int[] endTime = {19,18,19,16,10,8,19,8};
        int[] profit = {2,9,1,19,5,7,3,19};
        JobScheduling1235 jobs = new JobScheduling1235();
        System.out.println(jobs.jobScheduling(startTime, endTime, profit));
    }

//    Bottom up DP with binary search; time: O(nlogn), space: O(n)
    private Comparator<List<Integer>> jobsComparator = Comparator.comparingInt(s -> s.get(0));
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        final int n = startTime.length;
        List<List<Integer>> jobs = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            List<Integer> job = new ArrayList<>();
            job.add(startTime[i]);
            job.add(endTime[i]);
            job.add(profit[i]);
            jobs.add(job);
        }
        Collections.sort(jobs, jobsComparator);
        for(int i = 0 ; i < n ; i++) {
            startTime[i] = jobs.get(i).get(0);
        }
        return maxProfit(jobs, startTime);
    }
    private int findNextJob(int[] startTime, int lastEndTime) {
        int start = 0, end = startTime.length - 1, nextIndex = startTime.length;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(startTime[mid] >= lastEndTime) {
                nextIndex = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nextIndex;
    }
    private int maxProfit(List<List<Integer>> jobs, int[] startTime) {
        int[] memo = new int[50001];
        final int n = startTime.length;
        for(int pos = n - 1 ; pos >= 0 ; pos--) {
           int currProfit;
           int nextIndex = findNextJob(startTime, jobs.get(pos).get(1));
           if(nextIndex != n) {
               currProfit = jobs.get(pos).get(2) + memo[nextIndex];
           } else {
               currProfit = jobs.get(pos).get(2);
           }
           if(pos != n - 1) {
               memo[pos] = Math.max(currProfit, memo[pos + 1]);
           } else {
               memo[pos] = currProfit;
           }
        }
        return memo[0];
    }
}

/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
If you choose a job that ends at time X you will be able to start another job that starts at time X.
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.
Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6
Constraints:
1 <= startTime.length == endTime.length == profit.length <= 5 * 104
1 <= startTime[i] < endTime[i] <= 109
1 <= profit[i] <= 104
 */