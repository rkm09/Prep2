package LeetDaily.medium;


public class CampusBikes1066 {
//    bike max length (constraint)
    boolean[] visited = new boolean[10];
    int minimumDistanceSum = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] workers = {{0,0},{2,1}};
        int[][] bikes = {{1,2},{3,3}};
        CampusBikes1066 campusBikes1066 = new CampusBikes1066();
        System.out.println(campusBikes1066.assignBikes(workers, bikes));
    }

    private int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

//    Brute force; recursion; works only 'coz of lower limi; still feasible and slow
//    permutation; time: O(m!/(m-n)!), space: O(m+n) [n arrangements of bikes taken m at a time] n - workers, m - bikes
//    Greedy backtracking; if a path is futile stop computation of that route eg. (worker 1) m bikes -> (worker 2) m-1 bikes-> ...
    public int assignBikes(int[][] workers, int[][] bikes) {
        assignBikes(workers, 0, bikes, 0);
        return minimumDistanceSum;
    }
    private void assignBikes(int[][] workers, int workerIndex, int[][] bikes, int currDistanceSum) {
        if(workerIndex >= workers.length) {
            minimumDistanceSum = Math.min(minimumDistanceSum, currDistanceSum);
            return;
        }
        if(currDistanceSum > minimumDistanceSum) {
            return;
        }
        for(int bikeIndex = 0 ; bikeIndex < bikes.length ; bikeIndex++) {
            if(!visited[bikeIndex]) {
                visited[bikeIndex] = true;
                assignBikes(workers, workerIndex + 1, bikes, currDistanceSum + findDistance(workers[workerIndex], bikes[bikeIndex]));
                visited[bikeIndex] = false;
            }
        }
    }

}

/*
On a campus represented as a 2D grid, there are n workers and m bikes, with n <= m. Each worker and bike is a 2D coordinate on this grid.
We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.
Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation:
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.
Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation:
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
Example 3:
Input: workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
Output: 4995
Constraints:
n == workers.length
m == bikes.length
1 <= n <= m <= 10
workers[i].length == 2
bikes[i].length == 2
0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
All the workers and the bikes locations are unique.
 */