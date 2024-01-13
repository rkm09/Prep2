package LeetDaily.medium;

import java.util.HashMap;
import java.util.Map;

public class MinSteps1347 {
    public static void main(String[] args) {
        String s = "leetcode"; String t = "practise";
        System.out.println(minSteps(s, t));
    }

//    count sort; time: O(n), space: O(1); faster;
    public static int minSteps(String s, String t) {
        int[] count = new int[26];
        for(int i = 0 ; i < s.length() ; i++) {
            count[t.charAt(i) - 'a']++;
            count[s.charAt(i) - 'a']--;
        }
        int ans = 0;
        for(int i = 0 ; i < 26 ; i++) {
            ans += Math.max(0, count[i]);
        }
        return ans;
    }

//    [def]; hashmap; time: O(n), space: O(n)
    public static int minSteps1(String s, String t) {
        int count = 0;
        Map<Character, Integer> smap = new HashMap<>();
        for(int i = 0 ; i < s.length() ; i++) {
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0) + 1);
        }
        Map<Character, Integer> tmap = new HashMap<>();
        for(int i = 0 ; i < t.length() ; i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        }
        for(char key : smap.keySet()) {
            if(tmap.containsKey(key)) {
                count += tmap.get(key) > smap.get(key) ? (tmap.get(key) - smap.get(key)) : 0;
                tmap.put(key, -1);
            }
        }
        for(char key : tmap.keySet()) {
            if(tmap.get(key) != -1) {
                count += tmap.get(key);
            }
        }
        return count;
    }

}

/*
You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.
Return the minimum number of steps to make t an anagram of s.
An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
Example 1:
Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
Example 2:
Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
Example 3:
Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams.
Constraints:
1 <= s.length <= 5 * 104
s.length == t.length
s and t consist of lowercase English letters only.
 */