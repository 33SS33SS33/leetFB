package aMaz;

/**
 * Created by shanshan on 2/12/19.
 * Suppose we abstract our file system by a string in the following manner:
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * dir
 * subdir1
 * subdir2
 * file.ext
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system.
 * If there is no file in the system, return 0.
 */
public class LongestAbsoluteFilePath {

    public int longestAbsoluteFilePath(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length + 1];
        int maxLen = 0;
        for (String s : paths) {
            int lev = s.lastIndexOf("\t") + 1,
                    curLen = stack[lev + 1] = stack[lev] + s.length() - lev + 1;
            if (s.contains("."))
                maxLen = Math.max(maxLen, curLen - 1);
        }
        return maxLen;
    }
}
