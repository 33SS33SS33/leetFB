package medium;

import java.util.*;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together,
 * such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * Tags: Stack, String
 */
class SimplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home//foo/"));
        System.out.println(simplifyPath("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
        System.out.println(simplifyPathB("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
    }

    /** 最好的
     * Split words with /, use a stack to save directories
     * If ".", skip
     * If "..", check stack. If stack empty, skip; If not, pop
     * Else, push it to stack
     * Initialize result as "/" if stack is empty, otherwise as empty string
     * Go through stack and concatenate words
     * Return result
     */
    public static String simplifyPath(String path) {
        if (path == null)
            return "";
        Stack<String> s = new Stack<String>();
        String[] words = path.split("/");
        for (String str : words) {
            if (str.length() == 0 || str.equals("."))
                continue;
            if (str.equals("..")) {
                if (s.isEmpty())
                    continue;
                else
                    s.pop();
            } else
                s.push(str); // is a word
        }
        String res = s.isEmpty() ? "/" : ""; // check whether stack is empty
        for (String word : s)
            res += "/" + word;
        return res;
    }

    public static String simplifyPathB(String path) {
        Stack<String> stack = new Stack<String>();
        //stack.push(path.substring(0,1));
        while (path.length() > 0 && path.charAt(path.length() - 1) == '/') {
            path = path.substring(0, path.length() - 1);
        }
        int start = 0;
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                stack.push(path.substring(start, i));
                start = i;
            } else if (i == path.length() - 1) {
                stack.push(path.substring(start));
            }
        }
        LinkedList<String> result = new LinkedList<String>();
        int back = 0;
        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (top.equals("/.") || top.equals("/")) {
                //nothing
            } else if (top.equals("/..")) {
                back++;
            } else {
                if (back > 0) {
                    back--;
                } else {
                    result.push(top);
                }
            }
        }
        //if empty, return "/"
        if (result.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            String s = result.pop();
            sb.append(s);
        }
        return sb.toString();
    }

}
