package Top150.intervals;

import java.util.*;

public class MergeIntervals56 {
    private Map<int[], List<int[]>> graph;
    private Map<Integer, List<int[]>> nodesInComp;
    private Set<int[]> visited;

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        MergeIntervals56 mergeIntervals56 = new MergeIntervals56();
        int[][] ans = mergeIntervals56.merge(intervals);
        for(int[] a : ans) {
            System.out.println(Arrays.toString(a));
        }
    }

//    sorting; time: O(nlogn), space: O(logn) or O(n) [in case sorting cannot be done in-place]
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for(int[] interval : intervals) {
            if(merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }



//    MLE :|
//    brute force connected component graph approach; time: O(n^2), space: O(n^2)
    public int[][] mergeX(int[][] intervals) {
        buildGraph(intervals);
        buildComponents(intervals);
//        for each component, merge all intervals into one interval
        List<int[]> merged = new LinkedList<>();
        for(int comp = 0 ; comp < nodesInComp.size() ; comp++) {
            merged.add(mergeNodes(nodesInComp.get(comp)));
        }
        return merged.toArray(new int[merged.size()][]);
    }

//    return whether two intervals overlap (inclusive)
    private boolean overlap(int[] a, int[] b) {
        if(a[0] <= b[1] && b[0] <= a[1]) {
            return true;
        }
        return false;
    }

//    build a graph where an undirected edge exists between u & v if their intervals overlap
    private void buildGraph(int[][] intervals) {
        graph = new HashMap<>();
        for(int[] interval : intervals) {
            graph.put(interval, new LinkedList<>());
        }
        for(int[] interval1 : intervals) {
            for(int[] interval2 : intervals) {
                if(overlap(interval1, interval2)) {
                    graph.get(interval1).add(interval2);
                    graph.get(interval2).add(interval1);
                }
            }
        }
    }

//    get the connected components of the interval overlap graph
    private void buildComponents(int[][] intervals) {
        nodesInComp = new HashMap<>();
        visited = new HashSet<>();
        int compNumber = 0;
        for(int[] interval : intervals) {
            if(!visited.contains(interval)) {
                markComponentDFS(interval, compNumber);
                compNumber++;
            }
        }
    }

//    use depth first search to mark all nodes in the same connected component with the same number
    private void markComponentDFS(int[] start, int compNumber) {
        Stack<int[]> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()) {
            int[] node = stack.pop();
            if(!visited.contains(node)) {
                visited.add(node);
                if(nodesInComp.get(compNumber) == null) {
                    nodesInComp.put(compNumber, new LinkedList<>());
                }
                nodesInComp.get(compNumber).add(node);
                for(int[] child : graph.get(node)) {
                    stack.push(child);
                }
            }
        }
    }

//    merge all nodes in this connected component
    private int[] mergeNodes(List<int[]> nodes) {
        int minStart = nodes.get(0)[0];
        for(int[] node : nodes) {
            minStart = Math.min(node[0], minStart);
        }
        int maxEnd = nodes.get(0)[1];
        for(int[] node : nodes) {
            maxEnd = Math.max(node[1], maxEnd);
        }
        return new int[]{minStart, maxEnd};
    }
}

/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */