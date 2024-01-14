package LeetDaily.medium;

import java.util.*;

public class CloseStrings1657 {
    public static void main(String[] args) {
        String word1 = "uau"; String word2 = "ssx";
        System.out.println(closeStrings1(word1, word2));
    }

//    count freq; time: O(n), space: O(1) [sorting for constant length, so 26log26 can be ignored]; faster;
    public static boolean closeStrings1(String word1, String word2) {
        if(word1.length() != word2.length()) {
            return false;
        }
        int[] wordMap1 = new int[26];
        int[] wordMap2 = new int[26];
        for(char c : word1.toCharArray()) {
            wordMap1[c - 'a']++;
        }
        for(char c : word2.toCharArray()) {
            wordMap2[c - 'a']++;
        }
        for(int i = 0 ; i < 26 ; i++) {
            if((wordMap1[i] == 0 && wordMap2[i] > 0) ||
                    (wordMap2[i] == 0 && wordMap1[i] > 0)) {
                return false;
            }
        }

        Arrays.sort(wordMap1);
        Arrays.sort(wordMap2);
        return Arrays.equals(wordMap1, wordMap2);
    }

//    hashmap; time: O(n) (since the sort count is constant); space: O(1)
    public static boolean closeStrings2(String word1, String word2) {
        if(word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> count1 = new HashMap<>();
        Map<Character, Integer> count2 = new HashMap<>();
        for(char c : word1.toCharArray()) {
            count1.put(c, count1.getOrDefault(c, 0) + 1);
        }
        for(char c : word2.toCharArray()) {
            count2.put(c, count2.getOrDefault(c, 0) + 1);
        }
        if(!count1.keySet().equals(count2.keySet())) {
            return false;
        }
        List<Integer> freqList1 = new ArrayList<>(count1.values());
        List<Integer> freqList2 = new ArrayList<>(count2.values());
        Collections.sort(freqList1);
        Collections.sort(freqList2);
        return freqList1.equals(freqList2);
    }

//    [def]; faster than hashmap approach;
    public static boolean closeStringsN(String word1, String word2) {
        if(word1.length() != word2.length()) return false;
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for(char c : word1.toCharArray()) {
            set1.add(c);
        }
        for(char c : word2.toCharArray()) {
            set2.add(c);
        }
        for(char c : set1) {
            if(!set2.contains(c)) {
                return false;
            }
        }
        return helper(word1).equals(helper(word2));
    }
    private static List<Integer> helper(String word) {
        int[] cnt = new int[26];
        for(int i = 0 ; i < word.length() ; i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        List<Integer> li = new ArrayList<>();
        for(int num : cnt) {
            li.add(num);
        }
        Collections.sort(li);
        return li;
    }
}

/*
Two strings are considered close if you can attain one from the other using the following operations:
Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.
Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
Example 1:
Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"
Example 2:
Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
Example 3:
Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"
Constraints:
1 <= word1.length, word2.length <= 105
word1 and word2 contain only lowercase English letters.

 */