package org.example.demo.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RandomizedPickNotInBlackList {


    /**
     * 710. 黑名单中的随机数
     * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
     *
     * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
     *
     * 实现 Solution 类:
     *
     * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
     * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/random-pick-with-blacklist
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static class Solution {

        Map<Integer, Integer> map = new HashMap<>();
        int sz;

        public Solution(int n, int[] blacklist) {
            sz = n - blacklist.length;
            // 先将所有的黑名单数字加入 mapmap
            for(int b : blacklist) {
                // 这里赋值多少都可以
                // 目的仅仅把键存进哈希表
                // 方便快速判断数字是否在黑名单内
                map.put(b, 666);
            }

            int last = n - 1;
            for(int b : blacklist) {
                // 如果 b 已经在区间 [sz, N)
                // 可以直接忽略
                if(b >= sz) {
                    continue;
                }
                // 跳过所有黑名单的数字
                while(map.containsKey(last)) {
                    last--;
                }
                // 将黑名单的索引映射到合法数字
                map.put(b, last);
                last--;
            }
        }

        public int pick() {
            // 随机选取一个索引
            int index = (int)(Math.random()  * (sz));
            // 这个索引命中了黑名单
            // 需要被映射到其他位置
            if(map.containsKey(index)) {
                return map.get(index);
            }
            // 若没有命中黑名单，则直接返回
            return index;
        }
    }
}
