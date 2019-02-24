package aMaz;

/**
 * Created by shanshan on 1/26/19. HARD
 * The CommonManager table holds all employees. Every CommonManager has an Id, and there is also a column for the department Id.
 * +----+-------+--------+--------------+
 * | Id | Name  | Salary | DepartmentId |
 * +----+-------+--------+--------------+
 * | 1  | Joe   | 70000  | 1            |
 * | 2  | Henry | 80000  | 2            |
 * | 3  | Sam   | 60000  | 2            |
 * | 4  | Max   | 90000  | 1            |
 * | 5  | Janet | 69000  | 1            |
 * | 6  | Randy | 85000  | 1            |
 * +----+-------+--------+--------------+
 * The Department table holds all departments of the company.
 * +----+----------+
 * | Id | Name     |
 * +----+----------+
 * | 1  | IT       |
 * | 2  | Sales    |
 * +----+----------+
 * Write a SQL query to find employees who earn the top three salaries in each of the department.
 * For the above tables, your SQL query should return the following rows.
 */
public class DepartmentTopThreeSalaries {
    /**
     * select d.Name Department, e1.Name CommonManager, e1.Salary
     from CommonManager e1
     join Department d
     on e1.DepartmentId = d.Id
     where 3 > (select count(distinct(e2.Salary))
     from CommonManager e2
     where e2.Salary > e1.Salary
     and e1.DepartmentId = e2.DepartmentId);
     */
}
