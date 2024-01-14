package Top150.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

// doing the same thing with two stacks
class MinStack2 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public MinStack2() {
        minStack = new Stack<>();
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

//      <= condition is necessary, 'coz otherwise the pop() operation will remove the min when appropriate and throw error of the stack being empty
//      hence, even same min val will have to be pushed
        if(minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if(stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

// Improved 2 stacks; maintaining count
// time : O(1) all operations, space: O(n)
class MinStack3 {
    private Stack<Integer> stack;
    private Stack<int[]> minStack;
    public MinStack3() {
        minStack = new Stack<>();
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

//      <= condition is necessary, 'coz otherwise the pop() operation will remove the min when appropriate and throw error of the stack being empty
//      hence, even same min val will have to be pushed
        if(minStack.isEmpty() || val < minStack.peek()[0]) {
            minStack.push(new int[]{val, 1});
        } else if(val == minStack.peek()[0]) {
            minStack.peek()[1]++;
        }
    }

    public void pop() {
        if(stack.peek().equals(minStack.peek()[0])) {
            minStack.peek()[1]--;
        }
        if(minStack.peek()[1] == 0) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek()[0];
    }
}
