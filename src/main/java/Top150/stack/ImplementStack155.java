package Top150.stack;

import java.util.ArrayList;
import java.util.List;

public class ImplementStack155 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
    }
}


// time: O(1) [as expected], space: O(n)
class MinStack {
    private List<int[]> minStack;
    public MinStack() {
        minStack = new ArrayList<>();
    }

    public void push(int val) {
        if(minStack.isEmpty()) {
            minStack.add(new int[]{val, val});
            return;
        }
        int currMin = minStack.get((minStack.size() - 1))[1];
        minStack.add(new int[]{val, Math.min(currMin, val)});
    }

    public void pop() {
        minStack.remove(minStack.size() - 1);
    }

    public int top() {
        return minStack.get(minStack.size() - 1)[0];
    }

    public int getMin() {
        return minStack.get(minStack.size() - 1)[1];
    }
}

// [def]; this is not O(1) for min
class MinStack1111 {
    List<Integer> minStack;
    public MinStack1111() {
        minStack = new ArrayList<>();
    }

    public void push(int val) {
        minStack.add(val);
    }

    public void pop() {
        minStack.remove(minStack.size() - 1);
    }

    public int top() {
        return minStack.get(minStack.size() - 1);
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for(int num : minStack) {
            min = Math.min(num, min);
        }
        return min;
    }
}