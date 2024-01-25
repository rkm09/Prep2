package LeetDaily.medium;

import java.util.*;

public class MaxLength1239 {
    public static void main(String[] args) {
        List<String> arr = Arrays.asList(new String[] {"un","iq","ue"});
        System.out.println(maxLength(arr));
    }

//   optimised recursion; time: O(2^n), space: O(2^(min(n,k)))
    public static int maxLength(List<String> arr) {
        Set<Integer> optSet = new HashSet<>();
        for(String word : arr) {
            wordToBitSetK(word, optSet);
        }
        int[] optArr = new int[optSet.size()];
        int i = 0;
        for(int num : optArr) {
            optArr[i] = num;
        }
        return dfs(optArr, 0, 0);
    }
    private static void wordToBitSetK(String word, Set<Integer> optSet) {
        int charBitSet = 0;
        for(char c : word.toCharArray()) {
            int mask = 1 << c - 'a';
            if((charBitSet & mask) > 0) {
                return;
            }
            charBitSet += mask;
        }
        optSet.add(charBitSet + (word.length() << 26));
    }
    private static int dfs(int[] optArr, int pos, int res) {
        int oldLen = res >> 26;
        int oldChars = res & ((1 << 26) - 1);
        int best = oldLen;
        for(int i = pos ; i < optArr.length ; i++) {
            int newChars = optArr[i] & ((1 << 26) - 1);
            int newLen = optArr[i] >> 26;
            if((oldChars & newChars) > 0) {
                continue;
            }
            int newRes = oldChars + newChars + ((oldLen + newLen) << 26);
            best = Math.max(best, dfs(optArr, i + 1, newRes));
        }
        return best;
    }

//    recursion; time: O(2^n), space: O(2^(min(n,k))) n is the length of arr, k are the number of distinct chars in arr
//    slightly greater space, since we don't backtrack
    public static int maxLength1(List<String> arr) {
        return dfs1(arr, 0, "");
    }
    private static int dfs1(List<String> arr, int pos, String res) {
        Set<Character> resSet = new HashSet<>();
        for(char c : res.toCharArray()) {
            resSet.add(c);
        }
        if(resSet.size() != res.length()) {
            return 0;
        }
        int best = res.length();
        for(int i = pos ; i < arr.size() ; i++) {
            best = Math.max(best, dfs1(arr, i + 1, res + arr.get(i)));
        }
        return best;
    }

    // optimized backtracking; time: O(2^n), space: O(n); fast
    public static int maxLength2(List<String> arr) {
        Set<Integer> optSet = new HashSet<>();
        for(String word : arr) {
            wordToBitSet(word, optSet);
        }
        int[] optArr = new int[optSet.size()];
        int i = 0;
        for(int num : optSet) {
            optArr[i++] = num;
        }
        return backtrack(optArr, 0, 0, 0);
    }
    private static void wordToBitSet(String word, Set<Integer> optSet) {
        int charBitSet = 0;
        for(char c : word.toCharArray()) {
            int mask = 1 << c - 'a';
            if((charBitSet & mask) > 0) {
                return;
            }
            charBitSet += mask;
        }
        optSet.add(charBitSet + (word.length() << 26));
    }
    private static int backtrack(int[] optArr, int pos, int resChars ,int resLen) {
        int best = resLen;
        for(int i = pos ; i < optArr.length ; i++) {
            int newChars = optArr[i] & ((1 << 26) - 1);
            int newLen = optArr[i] >> 26;
            if((resChars & newChars) > 0) {
                continue;
            }
            resChars += newChars;
            resLen += newLen;
            best = Math.max(best, backtrack(optArr, i + 1, resChars, resLen));

//            backtrack
            resLen -= newLen;
            resChars -= newChars;
        }
        return best;
    }


//    backtracking (dfs recursion); time: O(2^n), space: O(n)
    public static int maxLength3(List<String> arr) {
        return backtrack3(arr, 0, new HashMap<>());
    }
    private static int backtrack3(List<String> arr, int pos, Map<Character, Integer> resMap) {
        for(Integer val : resMap.values()) {
            if(val > 1) {
                return 0;
            }
        }
        int best = resMap.size();
        for(int i = pos ; i < arr.size() ; i++) {
            char[] wordArr = arr.get(i).toCharArray();
            for(char c : wordArr) {
                resMap.put(c, resMap.getOrDefault(c, 0) + 1);
            }
            best = Math.max(best, backtrack3(arr, i + 1, resMap));
//            backtrack to previous state once done
            for(char c : wordArr) {
                if(resMap.get(c) == 1) {
                    resMap.remove(c);
                } else if(resMap.get(c) > 1) {
                    resMap.put(c, resMap.get(c) - 1);
                }
            }
        }
        return best;
    }

//    iterative optimized brute force; time: O(2^n), space: O(2^n)
    public static int maxLength4(List<String> arr) {
        int best = 0;
        Set<Integer> results = new HashSet<>(){{add(0);}};
        for(String word : arr) {
            best = Math.max(best, addWord(word, results));
        }
        return best;
    }
    private static int addWord(String word, Set<Integer> results) {
        int best = 0;
        int charBitSet = 0;
        for(char c : word.toCharArray()) {
            int mask = (1 << c - 'a');
//            char already found or not
            if((charBitSet & mask) > 0) {
                return 0;
            }
//            mark char as seen in bitset
            charBitSet += mask;
        }
//        if the initial bitset is already present, then any possibility with that combination will have been found
        if(results.contains(charBitSet + (word.length() << 26))) {
            return 0;
        }

        Set<Integer> newResults = new HashSet<>();
        for(Integer res : results) {
//            check for overlap of bits
            if((charBitSet & res) > 0) {
                continue;
            }
            int newResLen = (res >> 26) + word.length();
            int newCharBitSet = (charBitSet + res) & ((1 << 26) - 1);
            newResults.add((newResLen << 26) + newCharBitSet);
            best = Math.max(best, newResLen);
        }
        results.addAll(newResults);
        return best;
    }

//    iterative brute force; time: O(2^n), space: O(n)
    public static int maxLength5(List<String> arr) {
        List<String> results = new ArrayList<>();
        int best = 0;
        results.add("");
        for(String word : arr) {
            int resLen = results.size();
            for(int i = 0 ; i < resLen ; i++) {
                String newRes = results.get(i) + word;
                Set<Character> newResSet = new HashSet<>();
                for(char c : newRes.toCharArray()) {
                    newResSet.add(c);
                }
                if(newResSet.size() != newRes.length()) {
                    continue;
                }
                results.add(newRes);
                best = Math.max(best, newRes.length());
            }
        }
        return best;
    }

}

/*
You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
Return the maximum possible length of s.
A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
Example 1:
Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All the valid concatenations are:
- ""
- "un"
- "iq"
- "ue"
- "uniq" ("un" + "iq")
- "ique" ("iq" + "ue")
Maximum length is 4.
Example 2:
Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
Example 3:
Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
Explanation: The only string in arr has all 26 characters.
Constraints:
1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lowercase English letters.
 */