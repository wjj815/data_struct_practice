package org.example.demo.graph;

import junit.framework.TestCase;

import java.util.Arrays;

public class CourseTableSolutionTest extends TestCase {

    public void testCanFinish() {
        /**
         *
         * 输入：numCourses = 2, prerequisites = [[1,0]]
         * 输出：true
         * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/course-schedule
         */
        CourseTableSolution courseTableSolution = new CourseTableSolution();
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        boolean res = courseTableSolution.canFinish(numCourses, prerequisites);
        assertTrue(res);
    }

    public void testFindOrder() {

        /**
         * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
         * 输出：[0,2,1,3]
         * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
         * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/course-schedule-ii
         */
        CourseTableSolution courseTableSolution = new CourseTableSolution();
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] order = courseTableSolution.findOrder(numCourses, prerequisites);

        System.out.println(Arrays.toString(order));
    }
}