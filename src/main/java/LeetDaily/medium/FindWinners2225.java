package LeetDaily.medium;

import java.util.*;

public class FindWinners2225 {
    public static void main(String[] args) {
        int[][] matches = {{1,3},{2,3},{3,6},{5,6},{5,7},{4,5},{4,8},{4,9},{10,4},{10,9}};
        System.out.println(findWinners1(matches));
    }

//    counting sort (linear); time: O(n + k), space: O(k) [n: size of the input array, k: range of values in winner/loser]
//   constraint given of 10^5 for both match length & value type ; [fastest]
    public static List<List<Integer>> findWinners(int[][] matches) {
        int[] lossCount = new int[100001];
        Arrays.fill(lossCount, -1);
        for(int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            if(lossCount[winner] == -1) {
                lossCount[winner] = 0;
            }
            if(lossCount[loser] == -1) {
                lossCount[loser] = 1;
            } else {
                lossCount[loser]++;
            }
        }
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        for(int i = 1 ; i < 100001 ; i++) {
            if(lossCount[i] == 0) {
                answer.get(0).add(i);
            } else if(lossCount[i] == 1) {
                answer.get(1).add(i);
            }
        }
        return answer;
    }

//    hashmap; time: O(nlogn), space: O(n)
    public static List<List<Integer>> findWinners1(int[][] matches) {
          Map<Integer, Integer> lossCount = new HashMap<>();
          for(int[] match : matches) {
              int winner = match[0];
              int loser = match[1];
              lossCount.put(winner, lossCount.getOrDefault(winner, 0));
              lossCount.put(loser, lossCount.getOrDefault(loser, 0) + 1);
          }
          List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
          for(int key : lossCount.keySet()) {
              if(lossCount.get(key) == 0) {
                  answer.get(0).add(key);
              } else if(lossCount.get(key) == 1) {
                  answer.get(1).add(key);
              }
          }
          Collections.sort(answer.get(0));
          Collections.sort(answer.get(1));
          return answer;
    }

//    hashset; time: O(nlogn), space: O(n)
    public static List<List<Integer>> findWinners2(int[][] matches) {
        Set<Integer> zeroLoss = new HashSet<>();
        Set<Integer> oneLoss = new HashSet<>();
        Set<Integer> moreLosses = new HashSet<>();
        for(int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            if(!oneLoss.contains(winner) && !moreLosses.contains(winner)) {
                zeroLoss.add(winner);
            }
            if(zeroLoss.contains(loser)) {
                zeroLoss.remove(loser);
                oneLoss.add(loser);
            }else if(oneLoss.contains(loser)) {
                oneLoss.remove(loser);
                moreLosses.add(loser);
            } else if(moreLosses.contains(loser)) {
                continue;
            } else {
                oneLoss.add(loser);
            }
        }
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        answer.get(0).addAll(zeroLoss);
        answer.get(1).addAll(oneLoss);
        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));
        return answer;
    }

//    hashmap + hashset; time: O(nlogn), space: O(n)
    public static List<List<Integer>> findWinners3(int[][] matches) {
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        Map<Integer, Integer> loseMap = new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        for(int[] nums : matches) {
            int winner = nums[0];
            int loser = nums[1];
            seen.add(winner);
            seen.add(loser);
            loseMap.put(loser, loseMap.getOrDefault(loser, 0) + 1);
        }
        for(int key : seen) {
            if(!loseMap.containsKey(key)) {
                answer.get(0).add(key);
            } else if(loseMap.get(key) == 1) {
                answer.get(1).add(key);
            }
        }
        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));
        return answer;
    }

//    [def]; hashmap + hashset; time: O(nlogn), space: O(n) [faster than editorial except array approach]
    public static List<List<Integer>> findWinnersN(int[][] matches) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> allWin = new ArrayList<>();
        List<Integer> oneLoss = new ArrayList<>();
        Map<Integer, Integer> loseMap = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int[] nums : matches) {
            set.add(nums[0]);
            loseMap.put(nums[1], loseMap.getOrDefault(nums[1], 0) + 1);
        }
        for(int key : set) {
            if(!loseMap.containsKey(key)) {
                allWin.add(key);
            }
        }
        for(int key : loseMap.keySet()) {
            if(loseMap.get(key) == 1) {
                oneLoss.add(key);
            }
        }
        Collections.sort(allWin);
        Collections.sort(oneLoss);
        answer.add(allWin);
        answer.add(oneLoss);
        return answer;
    }
}

/*
You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
Return a list answer of size 2 where:
answer[0] is a list of all players that have not lost any matches.
answer[1] is a list of all players that have lost exactly one match.
The values in the two lists should be returned in increasing order.
Note:
You should only consider the players that have played at least one match.
The testcases will be generated such that no two matches will have the same outcome.
Example 1:
Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
Output: [[1,2,10],[4,5,7,8]]
Explanation:
Players 1, 2, and 10 have not lost any matches.
Players 4, 5, 7, and 8 each have lost one match.
Players 3, 6, and 9 each have lost two matches.
Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
Example 2:
Input: matches = [[2,3],[1,3],[5,4],[6,4]]
Output: [[1,2,5,6],[]]
Explanation:
Players 1, 2, 5, and 6 have not lost any matches.
Players 3 and 4 each have lost two matches.
Thus, answer[0] = [1,2,5,6] and answer[1] = [].
Constraints:
1 <= matches.length <= 105
matches[i].length == 2
1 <= winneri, loseri <= 105
winneri != loseri
All matches[i] are unique.
 */