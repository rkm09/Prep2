package LeetDaily.easy;

import java.util.Arrays;

public class CalculateTime1165 {
    public static void main(String[] args) {
        String keyboard = "pqrstuvwxyzabcdefghijklmno";
        String word = "leetcode";
        System.out.println(calculateTime(keyboard, word));
    }

//    count sort; time: O(n), space: O(1)
    public static int calculateTime(String keyboard, String word) {
        int[] keyIndices = new int[26];
        int prev = 0, ans = 0;
        for(int i = 0 ; i < keyboard.length() ; i++) {
            keyIndices[keyboard.charAt(i) - 'a'] = i;
        }
        for(char c : word.toCharArray()) {
            ans += Math.abs(prev - keyIndices[c - 'a']);
            prev = keyIndices[c - 'a'];
        }
        return ans;
    }

//    [def]; time: O(n), space: O(1)
    public static int calculateTime1(String keyboard, String word) {
        int time = 0;
        int prevIdx = 0; int currIdx = 0;
        for(char c : word.toCharArray()) {
            currIdx = keyboard.indexOf(c);
            time += Math.abs(currIdx - prevIdx);
            prevIdx = currIdx;
        }
        return time;
    }
}

/*
There is a special keyboard with all keys in a single row.
Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25). Initially, your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.
You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.
Example 1:
Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
Output: 4
Explanation: The index moves from 0 to 2 to write 'c' then to 1 to write 'b' then to 0 again to write 'a'.
Total time = 2 + 1 + 1 = 4.
Example 2:
Input: keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
Output: 73
Constraints:
keyboard.length == 26
keyboard contains each English lowercase letter exactly once in some order.
1 <= word.length <= 104
word[i] is an English lowercase letter.
 */