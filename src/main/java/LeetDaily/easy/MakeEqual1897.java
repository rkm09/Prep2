package LeetDaily.easy;

import java.util.Arrays;

public class MakeEqual1897 {
    public static void main(String[] args) {
        String[] words = {"kkkkkkkkkkkkkkkcaaaaa","aaaaaaaaa","a","bbb","bbbbbbbbb","bbb","cc","cccccccccccc","ccccccc","ccccccc","cc","cccc","c","cccccccc","c"};
        System.out.println(makeEqual(words));
    }

//  frequency count; time: O(n.k), space: O(1) ; n as words.length & k as word.length
//    could have used hashmap with similar result
    public static boolean makeEqual(String[] words) {
        int[] counts = new int[26];
        int n = words.length;
        if(n < 2) return true;
        for(String word : words) {
            for(char c : word.toCharArray()) {
                counts[c - 'a']++;
            }
        }
        for(int count : counts) {
//            if(count == 0) continue;
            if(count % n != 0) {
                return false;
            }
        }
        return true;
    }
}

/*
You are given an array of strings words (0-indexed).
In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any character from words[i] to any position in words[j].
Return true if you can make every string in words equal using any number of operations, and false otherwise.
Example 1:
Input: words = ["abc","aabc","bc"]
Output: true
Explanation: Move the first 'a' in words[1] to the front of words[2],
to make words[1] = "abc" and words[2] = "abc".
All the strings are now equal to "abc", so return true.
Example 2:
Input: words = ["ab","a"]
Output: false
Explanation: It is impossible to make all the strings equal using the operation.
Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
 */