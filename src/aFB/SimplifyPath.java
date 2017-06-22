package aFB;

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
        System.out.println(simplifyPatha("/home/"));
        System.out.println(simplifyPatha("/a/./b/../../c/"));
        System.out.println(simplifyPatha("/../"));
        System.out.println(simplifyPatha("/home//foo/"));
        System.out.println(
                simplifyPatha("/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///"));
    }

    //最好的
    public static String simplifyPatha(String path) {
        Deque<String> stack = new LinkedList<String>();
        Set<String> skip = new HashSet<String>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty())
                stack.pop();
            else if (!skip.contains(dir))
                stack.push(dir);
        }
        String res = "";
        for (String dir : stack)
            res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }

}
