package aMaz;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Tags: Stack, Data Structure
 * 用一个tuple来记录当前的最小值
 * Standard solution, two ss a minStack to store minimums
 */


/**
 * DP, one stack
 * build a static wrapper class for items in stack
 * including its value and current min
 */
class MinStack {
    /**
     * Wrapper class for element in stack
     */
    static class Element {
        final int value;
        final int min;

        public Element(int x, int min) {
            this.value = x;
            this.min = min;
        }
    }

    Stack<Element> s;

    public void push(int x) {
        if (s == null)
            s = new Stack<>();
        int min = s.isEmpty() ? x : Math.min(x, s.peek().min);
        s.push(new Element(x, min));
    }

    public void pop() {
        s.pop();
    }

    public int top() {
        return s.peek().value;
    }

    public int getMin() {
        return s.peek().min;
    }
}
