package org.example.demo.leetcode.bfs;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    /**
     * 752. 打开转盘锁
     * 难度
     * 中等
     * <p>
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * <p>
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * <p>
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * <p>
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/open-the-lock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {


        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";
        // 判断边界条件
        if (dead.contains(start) || dead.contains(target)) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            // 将当前队列中的所有节点向周围扩散
            for (int x = 0; x < size; x++) {
                String cur = queue.poll();

                // 判断是否到达终点
                if (cur.equals(target)) {
                    return step;
                }

                // 将一个节点的未遍历相邻节点加入队列
                for (int i = 0; i < 4; i++) {
                    String up = plusOne(cur, i);
                    String down = minusOne(cur, i);
                    if (!dead.contains(up)) {
                        queue.offer(up);
                        dead.add(up);
                    }
                    if (!dead.contains(down)) {
                        queue.offer(down);
                        dead.add(down);
                    }
                }
            }
            // 在这里增加步数
            step++;
        }
        // 如果穷举完都没有找到目标密码，那就是找不到了
        return -1;
    }

    public int openLock2(String[] deadends, String target) {

        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";
        if (deads.contains(start) || deads.contains(target)) {
            return -1;
        }

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();

        int step = 0;
        q1.add(start);
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();
            // 将 q1 中所有节点向周围扩散
            for (String cur : q1) {
                if (deads.contains(cur)) {
                    continue;
                }
                if (q2.contains(cur)) {
                    return step;
                }
                deads.add(cur);
                for (int i = 0; i < 4; i++) {
                    String up = plusOne(cur, i);
                    String down = minusOne(cur, i);
                    if (!deads.contains(up)) {
                        temp.add(up);
                    }
                    if (!deads.contains(down)) {
                        temp.add(down);
                    }
                }
            }
            // 这里增加步数
            step++;
            //temp 相当于 q1
            // 这里交换q1, q2 下一轮 while 就是扩散 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

    public String plusOne(String s, int i) {
        char[] chars = s.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return new String(chars);
    }

    public String minusOne(String s, int i) {
        char[] chars = s.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return new String(chars);
    }


    /**
     * 773. 滑动谜题
     * 难度
     * 困难
     ** 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
     *
     * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
     *
     * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sliding-puzzle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        int[][] neighbors = {
                {1, 3},
                {0, 2, 4},
                {1, 5},
                {0, 4},
                {1, 3, 5},
                {2, 4}
        };

        String target = "123450";

        StringBuilder start = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                start.append(board[i][j]);
            }
        }

        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();
        String init = start.toString();
        queue.offer(init);
        visited.add(init);
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String cur = queue.poll();

                if(target.equals(cur)) {
                    return step;
                }

                int x = 0;
                for(; cur.charAt(x) != '0'; x++);
                for (int neighbor : neighbors[x]) {

                    char[] chars = cur.toCharArray();
                    char temp = chars[x];
                    chars[x] = chars[neighbor];
                    chars[neighbor] = temp;
                    String next = new String(chars);
                    if(!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }

            step++;
        }

        return -1;
    }

}
