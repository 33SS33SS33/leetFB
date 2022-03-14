package aaMSsssssss;

import java.util.*;

/**
 * i) Sort by start day
 * ii) Loop all days where
 * <p>
 * If start day matches current day, enter end day in min heap
 * Peek and remove from min heap if the first element < current day. This means event could not be attended.
 * Be greedy. Poll the first element from the min heap i.e. the event is attended on the current day
 */
public class MaximumNumberofEventsThatCanBeAttended {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(eventStartDay -> eventStartDay[0]));
        int maxEventsThatCanBeAttended = 0, i = 0, numberOfEvents = events.length;
        // The default PriorityQueue is implemented with Min-Heap, that is the top element is the minimum one in the heap.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int day = 1; day <= 100000; day++) {

            // Store end dates in a priority queue if start date == day
            while (i < numberOfEvents && events[i][0] == day) {
                minHeap.add(events[i++][1]);
            }

            // Remove from queue if the event is over
            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            // Be greedy and use day to attend to the event that closes earlier
            // which is at the head of the queue
            if (!minHeap.isEmpty()) {
                maxEventsThatCanBeAttended++;
                // Removes the head of this queue
                minHeap.poll();
            }
        }
        return maxEventsThatCanBeAttended;
    }
}
