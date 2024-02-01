package LeetDaily.easy;

import java.util.ArrayList;
import java.util.List;

public class FlipGame298 {
    public static void main(String[] args) {
        String currentState = "++++";
        System.out.println(generatePossibleNextMoves(currentState));
    }

//    time: O(n^2), space: O(1)
    public static List<String> generatePossibleNextMoves(String currentState) {
        List<String> possibleStates = new ArrayList<>();
        int n = currentState.length();
        for(int i = 0 ; i < n - 1 ; i++) {
            if(currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+') {
                String nextState = currentState.substring(0, i) + "--" + currentState.substring(i + 2);
                possibleStates.add(nextState);
            }
        }
        return possibleStates;
    }
}

/*
You are playing a Flip Game with your friend.
You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person will be the winner.
Return all possible states of the string currentState after one valid move. You may return the answer in any order. If there is no valid move, return an empty list [].
Example 1:
Input: currentState = "++++"
Output: ["--++","+--+","++--"]
Example 2:
Input: currentState = "+"
Output: []
Constraints:
1 <= currentState.length <= 500
currentState[i] is either '+' or '-'.
 */