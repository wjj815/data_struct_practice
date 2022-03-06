package org.example.demo.leetcode.array;

/**
 * 二分搜索问题的泛化
 * <p>
 * 你要从题目中抽象出一个自变量x，一个关于x的函数f(x)，以及一个目标值target
 * 同时，x, f(x), target还要满足以下条件：
 * <p>
 * 1、f(x)必须是在x上的单调函数（单调增单调减都可以）。
 * <p>
 * 2、题目是让你计算满足约束条件f(x) == target时的x的值。
 * <p>
 * // 函数 f(x) 是关于自变量 x 的单调递增函数
 * // 入参 nums 是不会改变的，所以可以忽略，不算自变量
 * int f(int x, int[] nums) {
 * return nums[x];
 * }
 */
public class BinarySearchExtends {

   /* // 函数 f 是关于自变量 x 的单调函数
    int f(int x) {
        // ...
    }

    // 主函数，在 f(x) == target 的约束下求 x 的最值
    int solution(int[] nums, int target) {
        if (nums.length == 0) return -1;
        // 问自己：自变量 x 的最小值是多少？
        int left = ...;
        // 问自己：自变量 x 的最大值是多少？
        int right = ... + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(mid) == target) {
                // 问自己：题目是求左边界还是右边界？
                // ...
            } else if (f(mid) < target) {
                // 问自己：怎么让 f(x) 大一点？
                // ...
            } else if (f(mid) > target) {
                // 问自己：怎么让 f(x) 小一点？
                // ...
            }
        }
        return left;
    }*/

    /**
     * 875. 爱吃香蕉的珂珂
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * <p>
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
     * <p>
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * <p>
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {

        int l = 1;

        int r = piles[0];
        // 获取右边界
        for (int pile : piles) {
            if (pile > r) {
                r = pile;
            }
        }
        // 寻找在 h 时间的最小速度，因为是单调递减函数，所以寻找最左侧h 的速度
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (f(piles, m) > h) {
                l = m + 1;
            } else if (f(piles, m) < h) {
                r = m - 1;
            } else {
                r = m - 1;
            }
        }

        return l;
    }

    /**
     *  定义单调函数
     *  时间 = f(吃香蕉的速度）
     *  吃香蕉的速度越快，则时间会越来越小，所以符合单调函数，为单调递减函数
     * @param piles
     * @param x
     * @return
     */
    public int f(int[] piles, int x) {
        int hours = 0;

        for (int pile : piles) {
            hours += pile / x;
            if (pile % x > 0) {
                hours++;
            }
        }
        return hours;
    }


    /**
     * 1011. 在 D 天内送达包裹的能力
     * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
     *
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     *
     * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        int l = 0;
        int r = 0;
        // 货物不可分割，所以最小一次运输的重量为 weights 中的最大重量
        // 即 l = max(weights), r = sum(weights)
        for(int weight : weights) {
            l = Math.max(l, weight);
            r += weight;
        }

        while(l <= r) {

            int m = l + (r - l) / 2;

            if(transport(weights, m) > days) {
                l = m + 1;
            } else if(transport(weights, m) < days) {
                r = m - 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    /**
     * 单调递减函数， 即船所能承重越大，则花费的天数越小
     * @param weights
     * @param x
     * @return
     */
    public int transport(int[] weights, int x) {
        // base case
        int days = 1;
        int temp = x;
        for(int weight: weights) {
            if(temp < weight) {
                days ++;
                temp = x;
            }
            temp -= weight;
        }

        return days;
    }
}

