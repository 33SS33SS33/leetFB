package aMaz;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shanshan on 2/11/19.
 * Given a robot cleaner in a room modeled as a grid.
 * Each cell in the grid can be empty or blocked.
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 * Input:
 * room = [
 * [1,1,1,1,1,0,1,1],
 * [1,1,1,1,1,0,1,1],
 * [1,0,1,1,1,1,1,1],
 * [0,0,0,1,0,0,0,0],
 * [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 * Explanation:
 * All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 */
public class RobotRoomCleaner {
    //these four values are clockwise: up, right, down, left. (0,1) meaning y+1 -> go up
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public void cleanRoom(Robot robot) {
        Set<String> hs = new HashSet<>();
        dfs(robot, 0, 0, 0, hs); //the start position is seen as the original point. facing up originally
    }

    public void dfs(Robot robot, int x, int y, int curDir, Set<String> hs) //curDir is the current facing direction
    {
        hs.add(x + "#" + y);
        robot.clean();
        for (int i = 0; i < 4; i++) {
            int nextDir = (curDir + i) % 4; //moving direction, let's say we are facing right (1), nextDir will be 1 as well.
            int nextX = x + dx[nextDir];
            int nextY = y + dy[nextDir];
            if (!hs.contains(nextX + "#" + nextY) && robot.move()) //robot.move() not only checks wall but also moves
            {
                dfs(robot, nextX, nextY, nextDir, hs);
                //go back to start cell
                robot.turnRight();
                robot.turnRight();
                robot.move();
                //go back to the original direction
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();//because we purposely arranged dx, dy to be clockwise. If we are facing right, we will be facing down in the next iteration
        }
    }

    interface Robot {
        public boolean move();
        public void turnRight();
        public void clean();
    }
}
