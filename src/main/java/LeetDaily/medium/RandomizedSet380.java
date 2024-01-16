package LeetDaily.medium;

import java.util.*;

public class RandomizedSet380 {
    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
    }
}

// time: O(1), space: O(n) [time: worst case O(n) when the capacity exceeds the current allocation and invokes reallocation]
// hashmap insert & delete : average O(1), but getRandom(linear) will be an issue as there is no index concept in map
// array list insert & getRandom is avg constant time, but has problem with delete(linear time).
// solution is to take a combination of the two. compute index in constant time => store as value in map
class RandomizedSet {
    Random random = new Random();
    List<Integer> list;
    Map<Integer, Integer> map;

//    initialize
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

//    Inserts a value to the set. Returns true if the set did not already contain the specified element
    public boolean insert(int val) {
        if(map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

//    Removes a value from the set. Returns true if the set contained the specified element.
    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }
//        swap with last element and then delete last
        int lastElement = list.get(list.size() - 1);
        int idx = map.get(val);
        list.set(idx, lastElement);
        map.put(lastElement, idx);

        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

//    Get a random element from the set.
    public int getRandom() {
        int rand = random.nextInt(list.size());
        return list.get(rand);
    }
}

/*
Implement the RandomizedSet class:
RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.
Example 1:
Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]
Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
Constraints:
-231 <= val <= 231 - 1
At most 2 * 105 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
 */