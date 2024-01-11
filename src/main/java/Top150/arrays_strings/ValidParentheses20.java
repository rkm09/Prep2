package Top150.arrays_strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses20 {
    public static void main(String[] args) {
        String s = "()";
        System.out.println(isValid2(s));
    }

//    fastest; time: O(n), space: O(n)
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] carr = s.toCharArray();
        for(char c : carr) {
            if (c == '(') stack.push(')');
            else if(c == '{') stack.push('}');
            else if(c == '[') stack.push(']');
            else if(stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid1(String s) {
        Map<Character, Character> validPairs = new HashMap<>();
        validPairs.put(')','(');
        validPairs.put(']','[');
        validPairs.put('}','{');
        Stack<Character> stack = new Stack<>();
        char[] carr = s.toCharArray();
        for(char c : carr) {
            if(c == ')' || c == '}' || c == ']') {
                if(stack.isEmpty() || validPairs.get(c) != stack.pop()) return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }


//    :| [def] costly
    public static boolean isValid(String s) {
        int n = s.length();
        while(s.length() > 0 && n-- > 0) {
            s = s.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "");
        }
        return s.length() == 0;
    }
}
/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
Example 1:
Input: s = "()"
Output: true
Example 2:
Input: s = "()[]{}"
Output: true
Example 3:
Input: s = "(]"
Output: false
Constraints:
1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 */
