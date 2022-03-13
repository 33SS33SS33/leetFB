package aTOP50facebook;

import java.util.*;

class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home//foo/"));
        System.out.println(
                simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
    }

    public static String simplifyPath(String path) {
        // Initialize a stack
        Stack<String> stack = new Stack<String>();
        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : path.split("/")) {
            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } else if (directory.equals("..") && !stack.isEmpty()) {
                // If the current component is a "..", then
                // we pop an entry from the stack if it's non-empty
                stack.pop();
            } else {
                // Finally, a legitimate directory name, so we add it
                // to our stack
                stack.add(directory);
            }
        }
        // Stich together all the directory names together
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }
        return result.length() > 0 ? result.toString() : "/";
    }
}
