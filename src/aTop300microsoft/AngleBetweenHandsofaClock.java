package aTop300microsoft;

//https://leetcode.com/problems/angle-between-hands-of-a-clock/

/**
 * Input: hour = 12, minutes = 30
 * Output: 165
 */
public class AngleBetweenHandsofaClock {
    public double angleClock(int hour, int minutes) {
        int oneMinAngle = 6;
        int oneHourAngle = 30;

        double minutesAngle = oneMinAngle * minutes;
        double hourAngle = (hour % 12 + minutes / 60.0) * oneHourAngle;

        double diff = Math.abs(hourAngle - minutesAngle);
        return Math.min(diff, 360 - diff);
    }
}
