package LeetDaily.medium;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ReversePolishNotation150 {
    public static void main(String[] args) {
        String[] tokens = {"2","1","+","3","*"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        if(tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.parseInt(tokens[0]));
        int i = 0, res = 0;
        while(!stack.isEmpty() && i++ < tokens.length - 1) {
            if(!isNan(tokens[i])) {
                stack.push(Integer.parseInt(tokens[i]));
                continue;
            }
            int num2 = stack.pop();
            int num1 = stack.pop();
            switch(tokens[i]) {
                case "+" : res = num1 + num2; break;
                case "-" : res = num1 - num2; break;
                case "*" : res = num1 * num2; break;
                case "/" : res = num1 / num2; break;
            }
            stack.push(res);
        }
        return res;
    }
    private static boolean isNan(String var) {
        if(var.equals("+") || var.equals("-") || var.equals("*") || var.equals("/")) {
            return true;
        }
        return false;
    }

    public static int evalRPN1(String[] tokens) {
        int res = 0;
//        int num1 = Integer.parseInt(tokens[0]), num2 = Integer.parseInt(tokens[1]);
//        for(int i = 2 ; i < tokens.length ; i++) {
//            boolean isInt = false;
//            String curr = tokens[i];
//            switch(curr) {
//                case "+" : res += num1 + num2; break;
//                case "-" : res += num1 - num2; break;
//                case "*" : res += num1 * num2; break;
//                case "/" : res += num1 / num2; break;
//                default: isInt = !isInt;
//            }
//            if(isInt) {
//                num1 = num2;
//                num2 = Integer.parseInt(curr);
//            }
//        }
        return res;
    }
}

/*
You are given an array of string tokens that represents an arithmetic expression in a Reverse Polish Notation.
Evaluate the expression. Return an integer that represents the value of the expression.
Note that:
The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.
Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
Constraints:
1 <= tokens.length <= 104
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */