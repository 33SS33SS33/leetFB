package top50Google;

import java.util.Arrays;

public class TheNumberofWeakCharactersintheGame {
    /**
     * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense.
     * You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
     * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels.
     * More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
     * Return the number of weak characters.
     * Input: properties = [[5,5],[6,3],[3,6]]
     * Output: 0
     * Explanation: No character has strictly greater attack and defense than the other.
     */
    public int numberOfWeakCharacters(int[][] properties) {
        int n = properties.length;
        int count = 0;
        Arrays.sort(properties, (a, b) -> (b[0] == a[0]) ? (a[1] - b[1]) : b[0] - a[0]);
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (properties[i][1] < max) {
                count++;
            }
            max = Math.max(max, properties[i][1]);
        }
        return count;
    }
}
