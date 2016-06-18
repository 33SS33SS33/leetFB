package easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shanshan on 16/6/17.
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

 For example,
 MovingAverage m = new MovingAverage(3);
 m.next(1) = 1
 m.next(10) = (1 + 10) / 2
 m.next(3) = (1 + 10 + 3) / 3
 m.next(5) = (10 + 3 + 5) / 3
 */

/*Essentially, we just need to keep track of the sum of the current window as we go.
This prevents an O(n) traversal over the Queue as we add new numbers to get the new average.
If we need to evict then we just subtract that number off of our sum and divide by the size*/
public class MovingAveragefromDataStream {
    private double previousSum = 0.0;
    private int maxSize;
    private Queue<Integer> currentWindow;

    public MovingAveragefromDataStream(int size) {
        currentWindow = new LinkedList<Integer>();
        maxSize = size;
    }

    public double next(int val) {
        if (currentWindow.size() == maxSize)
        {
            previousSum -= currentWindow.remove();
        }

        previousSum += val;
        currentWindow.add(val);
        return previousSum / currentWindow.size();
    }
}
