package LeetDaily.easy;

import java.util.*;

public class UniqueOcc1207 {
    public static void main(String[] args) {
        int[] arr = {1,2,2,1,1,3};
        System.out.println(uniqueOccurrences(arr));
    }


//    hashmap + hashset; time: O(n), space: O(n); fast
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int num : arr){
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        Set<Integer> frSet = new HashSet<>(countMap.values());
        return frSet.size() == countMap.size();
    }

//    [def]; hashmap + count sort; time: O(n), space: O(n); fast;
    public static boolean uniqueOccurrencesN(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int num : arr){
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int[] freq = new int[1001];
        for(int val : countMap.values()) {
            if(freq[val] == 0) {
                freq[val]++;
            } else return false;
        }
        return true;
    }

    //    count sort + sort; time: O(n + klogk), space: O(k); slowest;
    public static boolean uniqueOccurrences1(int[] arr) {
        final int K = 1000;
        int[] freq = new int[2 * K + 1];
        for(int num : arr) {
            freq[num + K]++;
        }
        Arrays.sort(freq);
        for(int i = 0 ; i < 2 * K ; i++) {
            if(freq[i] != 0 && freq[i] == freq[i+1]) {
                return false;
            }
        }
        return true;
    }
}

/*
Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.
Example 1:
Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
Example 2:
Input: arr = [1,2]
Output: false
Example 3:
Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true
Constraints:
1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000
 */