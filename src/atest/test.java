package atest;

import java.util.*;

public class test {

    public static void main(String[] args) {
        int cityLength = 5;
        int cityWidth = 7;
        int[] lockerXCoordinates = new int[]{2, 4};
        int[] lockerYCoordinates = new int[]{3, 7};
        int[][] result = getLockerDistanceGrid(cityLength, cityWidth, lockerXCoordinates,
                lockerYCoordinates);
        for (int i = 0; i < cityWidth; i++) {
            for (int j = 0; j < cityLength; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    static int[][] getLockerDistanceGrid(int cityWidth, int cityLength, int[] lockerXCoordinates,
                                         int[] lockerYCoordinates) {
        int[][] result = new int[cityLength][];
        for (int i = 0; i < cityLength; i++) {
            result[i] = new int[cityWidth];
        }
        for (int i = 0; i < cityLength; i++)
            for (int j = 0; j < cityWidth; j++) {
                int minSteps = Integer.MAX_VALUE;
                for (int k = 0; k < lockerXCoordinates.length; k++) {
                    int stepx = lockerYCoordinates[k] - 1 - i;
                    if (stepx < 0) {
                        stepx = -stepx;
                    }
                    int stepy = lockerXCoordinates[k] - 1 - j;
                    if (stepy < 0) {
                        stepy = -stepy;
                    }
                    if (stepx + stepy < minSteps) {
                        minSteps = stepx + stepy;
                    }
                }
                result[i][j] = minSteps;
            }
        return result;
    }
/*    complexity is the number of friends multiply the max number of courses of each friend.
    First get  the direct friends of the user, then get the direct friends of the direct friends,remove the user self.
    Use set to store the user's friend network.
    Get the courses of the user's friends network,remove the user's courses.
    sort the course information by the number of people who choose the course.*/

    public List<String> getRankedCourses(String user) {
        List<String> friends = getDirectFriendsForUser(user);
        List<String> userCourses = getAttendedCoursesForUser(user);
        Set<String> network = new HashSet<String>();
        Map<String, Integer> courseInfo = new HashMap<String, Integer>();
        for (String friend : friends) {
            network.add(friend);
            List<String> otherFriends = getDirectFriendsForUser(friend);
            network.addAll(otherFriends);
        }
        network.remove(user);

        for (String friend : network) {
            List<String> courses = getAttendedCoursesForUser(friend);
            for (String course : courses) {
                if (userCourses.contains(course)) {
                    continue;
                }
                if (courseInfo.get(course) != null) {
                    courseInfo.put(course, courseInfo.get(course) + 1);
                } else {
                    courseInfo.put(course, 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> result = new ArrayList<Map.Entry<String, Integer>>(
                courseInfo.entrySet());
        Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return (Integer) o2.getValue() - (Integer) o1.getValue();
            }
        });
        List<String> courses = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : result) {
            courses.add(entry.getKey());
        }
        return courses;
    }

    public List<String> getDirectFriendsForUser(String user) {
        return null;

    }

    public List<String> getAttendedCoursesForUser(String user) {
        return null;
    }

}
