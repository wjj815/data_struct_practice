package org.example.demo.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseTableSolution {


    /**
     * 存储访问标识
     */
    boolean[] visited;
    /**
     * 记录回溯的路径，是检查环是否存在的重要标志
     */
    boolean[] onPath;
    /**
     * 是否存在环
     */
    boolean hasCycle = false;
    /**
     * https://leetcode-cn.com/problems/course-schedule/
     *
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     *
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * @param numCourses 课程
     * @param prerequisites 先修课程
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            // 遍历图中的所有节点
            dfs(graph, i);
        }

        // 只有没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    /**
     * 图的遍历
     * @param graph 图
     * @param s 开始顶点

     */
    public void dfs(List<Integer>[] graph, int s) {

        if(onPath[s]) {
            // 发现环
            hasCycle = true;
            // 如果已经找到了环，也不用再遍历了
            return;
        }

        if(visited[s]) {
            return;
        }

        /* 前序遍历代码位置*/
        // 将当前节点标记为已遍历
        visited[s] = true;
        onPath[s] = true;
        for (Integer neighbor : graph[s]) {
            dfs(graph, neighbor);
        }
        /* 后序遍历代码位置 */
        onPath[s] = false;
    }


    public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // 图中 共有 numCourses 个节点
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            // 修完课程 from 才能修 课程 to
            // 在图中添加一条从 from指向 to的有向边
            graph[from].add(to);
        }
        return graph;
    }

    /**
     * bfs 版本
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishByBFS(int numCourses, int[][] prerequisites) {
        // 建图，有向边代表[被依赖]关系
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            // 节点 to 的入度加 1
            inDegree[to] ++;
        }

        // 根据入度初始化队列中的节点
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                // 节点 i 没有入度， 即没有依赖的节点
                // 可以作为拓扑排序的起点，加入队列
                q.offer(i);
            }
        }

        // 记录遍历的节点数
        int count = 0;
        while(!q.isEmpty()) {
            // 弹出节点 cur, 并将它指向的节点的入度坚毅
            int cur = q.poll();
            count++;
            for(int next : graph[cur]) {
                inDegree[next]--;
                if(inDegree[next] == 0) {
                    // 如果入度变为 0, 说明 next 依赖的节点都已被遍历
                    q.offer(next);
                }
            }
        }
        // 如果所有节点都被遍历过， 说明不成环
        return count == numCourses;
    }


    /**
     * 课程表 2
     * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
     *
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule-ii
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer> order = new LinkedList<>();
        // 建图
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        // 遍历图
        for(int i = 0; i < numCourses; i++) {
            dfs(graph, i, order);
        }

        return order.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] findOrderByBFS(int numCourses, int[][] prerequisites) {

        // 建图，有向边代表[被依赖]关系
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            // 节点 to 的入度加 1
            inDegree[to] ++;
        }

        // 根据入度初始化队列中的节点
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                // 节点 i 没有入度， 即没有依赖的节点
                // 可以作为拓扑排序的起点，加入队列
                q.offer(i);
            }
        }
        // 记录拓扑排序结果
        int[] res = new int[numCourses];

        // 记录遍历的节点数
        int count = 0;
        while(!q.isEmpty()) {
            // 弹出节点 cur, 并将它指向的节点的入度坚毅
            int cur = q.poll();
            res[count] = cur;
            count++;
            for(int next : graph[cur]) {
                inDegree[next]--;
                if(inDegree[next] == 0) {
                    // 如果入度变为 0, 说明 next 依赖的节点都已被遍历
                    q.offer(next);
                }
            }
        }

        if(count != numCourses) {
            return new int[]{};
        }
        // 如果所有节点都被遍历过， 说明不成环
        return res;
    }


    /**
     * 记录拓扑路径的遍历
     * @param graph
     * @param s
     * @param order
     */
    public void dfs(List<Integer>[] graph, int s, List<Integer> order) {

        if(onPath[s]) {
            hasCycle = true;
            return;
        }

        if(visited[s]) {
            return;
        }

        /* 前序遍历代码位置*/
        // 将当前节点标记为已遍历
        visited[s] = true;

        for (Integer neighbor : graph[s]) {
            dfs(graph, neighbor, order);
        }
        /* 后序遍历代码位置 */
        order.add(0, s);
    }


}
