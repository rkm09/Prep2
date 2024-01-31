package LeetDaily.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures739 {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }

//    monotonic stack; time: O(n), space: O(n)
//    monotonic stack is a good option when the problem involves comparing elements with their order being relevant;
//    delay(push to stack), when elements are coming in descending order, since all will have a common "greater than" answer;
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int currDay = 0 ; currDay < n ; currDay++) {
            int currTemp = temperatures[currDay];
            while(!stack.isEmpty() && temperatures[stack.peek()] < currTemp) {
                int prevDay = stack.pop();
                answer[prevDay] = currDay - prevDay;
            }
            stack.push(currDay);
        }
        return answer;
    }

//    [def]; TLE ; time: O(n^2)
    public static int[] dailyTemperaturesX(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        for(int i = 0; i < n - 1 ; i++) {
            if(temperatures[i + 1] > temperatures[i]) {
                answer[i] = 1;
            } else {
                int j = i + 1;
                while(j < n) {
                    if(temperatures[j] > temperatures[i]) {
                        answer[i] = j - i;
                        break;
                    }
                    j++;
                }
            }
        }
        return answer;
    }
}

/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]
Constraints:
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */