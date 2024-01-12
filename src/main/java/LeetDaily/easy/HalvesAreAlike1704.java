package LeetDaily.easy;

public class HalvesAreAlike1704 {
    public static void main(String[] args) {
        String s = "book";
        System.out.println(halvesAreAlike(s));
    }

//    using indexOf; time: O(n), space: O(1)
    public static boolean halvesAreAlike(String s) {
        int n = s.length();
        return countVowel(0, n/2, s) == countVowel(n/2, n, s);
    }
    private static int countVowel(int start, int end, String s) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for(int i = start; i < end ; i++) {
            if(vowels.indexOf(s.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

//    [def]; using switch; time: O(n), space: O(1)
    public static boolean halvesAreAlike1(String s) {
        int left = 0, right = s.length() - 1;
        int countLeft = 0, countRight = 0;
        while(left < right) {
            if(isVowel(s.charAt(left))) {
                countLeft++;
            }
            if(isVowel(s.charAt(right))) {
                countRight++;
            }
            left++; right--;
        }
        return countLeft == countRight;
    }
    private static boolean isVowel(char c) {
        int flag = 0;
        switch(c) {
            case 'a' :
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U': flag = 1; break;
        }
        return flag == 1;
    }
}

/*
You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
Return true if a and b are alike. Otherwise, return false.
Example 1:
Input: s = "book"
Output: true
Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
Example 2:
Input: s = "textbook"
Output: false
Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
Notice that the vowel o is counted twice.
Constraints:
2 <= s.length <= 1000
s.length is even.
s consists of uppercase and lowercase letters.
 */