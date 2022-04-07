package org.example.demo.leetcode.bfs;

import java.util.*;

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


}
