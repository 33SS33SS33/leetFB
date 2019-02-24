package amaoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 寻找公司员工的最低共同上司
 * 变化1:不是binary tree
 * 变化2:ceo不是manager 如果共同上司是ceo return null
 * 变化3:员工可能不在这个公司 return null
 * 解题思路参考：
 * 题目描述不太清楚，坑：注意这个是个多叉数不是二叉。
 */
public class CommonManager {
    public static void main(String[] args) {

    }

    static class ResultType {
        boolean a_exist, b_exist;
        Employee node;

        ResultType(boolean a, boolean b, Employee n) {
            a_exist = a;
            b_exist = b;
            node = n;
        }
    }

    public static ResultType closestCommonManager1(Employee root, Employee node1, Employee node2) {
        if (root == null) {
            return new ResultType(false, false, null);
        }
        boolean a_exist = false, b_exist = false;
        if (root.reporters == null) {
            if (root == node1)
                a_exist = true;
            if (root == node2)
                b_exist = true;
            return new ResultType(a_exist, b_exist, (a_exist || b_exist) ? root : null);
        }
        int count = 0;
        Employee lca = null;
        for (Employee reporter : root.reporters) {
            ResultType sub = closestCommonManager1(reporter, node1, node2);
            if (sub.a_exist || root == node1)
                a_exist = true;
            if (sub.b_exist || root == node2)
                b_exist = true;

            if (sub.node != null) {
                lca = sub.node;
                count++;
            }
        }
        if (root == node1 || root == node2) {
            return new ResultType(a_exist, b_exist, root);
        }
        if (count == 2) {
            return new ResultType(a_exist, b_exist, root);
        }
        if (lca != null) {
            return new ResultType(a_exist, b_exist, lca);
        }

        return new ResultType(a_exist, b_exist, null);
    }

    public static Employee closestCommonManager2(Employee root, Employee node1, Employee node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        int count = 0;
        Employee lca = null;
        if (root.reporters == null) {
            return null;
        }
        for (Employee reporter : root.reporters) {
            Employee sub = closestCommonManager2(reporter, node1, node2);
            if (sub != null) {
                lca = sub;
                count++;
            }
        }
        if (count == 2) {
            return root;
        }
        return lca;
    }

    //----------------------------------------
    public static Employee comManager(Employee ceo, Employee emp1, Employee emp2) {
        Stack<Employee> e1 = new Stack<>();
        Stack<Employee> e2 = new Stack<>();
        dfs(ceo, emp1, e1);
        dfs(ceo, emp2, e2);
        if ((!e1.isEmpty() && e1.peek().getId() == emp1.getId()) && (!e2.isEmpty() && e2.peek().getId() == emp2.getId())) {
            int len1 = e1.size();
            int len2 = e2.size();
            if (len1 > len2) {
                moveUp(e1, len1 - len2);
            } else {
                moveUp(e2, len2 - len1);
            }
            while (e1.size() > 0 && e1.peek().getId() != e2.peek().getId()) {
                e1.pop();
                e2.pop();
            }
            if (e1.size() > 0) {
                return e1.peek();
            }

        }
        return null;
    }

    public static void moveUp(Stack<Employee> stack, int steps) {
        while (steps > 0 && !stack.isEmpty()) {
            stack.pop();
            steps--;
        }
    }

    public static boolean dfs(Employee ceo, Employee emp, Stack<Employee> stack) {
        stack.push(ceo);
        if (ceo.getId() == emp.getId()) {
            return true;
        }
        for (Employee em : ceo.getReporters()) {
            boolean result = dfs(em, emp, stack);
            if (result) {
                return true;
            }
        }
        stack.pop();
        return false;
    }

    //************************************************************Tree**************************************
    public static Employee comManagerTree(Employee ceo, Employee emp1, Employee emp2) {
        if (ceo == null || ceo == emp1 || ceo == emp2) {
            return ceo;
        }
        boolean judgeemp1 = false;
        boolean judgeemp2 = false;
        for (Employee em : ceo.getReporters()) {
            Employee result = comManagerTree(em, emp1, emp2);
            if (result == emp1) {
                judgeemp1 = true;
            } else if (result == emp2) {
                judgeemp2 = true;
            } else if (result != null) {
                return result;
            }
        }
        if (judgeemp1 && judgeemp2) {
            return ceo;
        } else if (judgeemp1) {
            return emp1;
        } else if (judgeemp2) {
            return emp2;
        }
        return null;
    }

    public static class Employee {
        int id;
        List<Employee> reporters = new ArrayList<Employee>();

        public void setId(int val) {
            id = val;
        }

        public int getId() {
            return id;
        }

        public void addReport(Employee emp) {
            reporters.add(emp);
        }

        public List<Employee> getReporters() {
            return reporters;
        }
    }

}
