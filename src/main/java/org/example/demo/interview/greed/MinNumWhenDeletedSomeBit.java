package org.example.demo.interview.greed;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 	2. 给定一个整数字符串，在删除 k 位后， 获取最小数（贪心思想）局部最优
 * 输入案例： 1542219   3
 * 输出案例： 1219
 */
public class MinNumWhenDeletedSomeBit {


    public static void main(String[] args) {

        String numStr = "01522219";
        int k = 3;

        String minNum = new MinNumWhenDeletedSomeBit().getMinNum(numStr, k);
        System.out.println(minNum);
    }

    public String getMinNum(String num, int k) {
        Deque<Character> dq = new LinkedList<>();

        // 维护单调栈
        for(char c : num.toCharArray()) {
            while(!dq.isEmpty() && k > 0 && c < dq.peek()) {
                dq.pop();
                k--;
            }
            dq.push(c);
        }

        StringBuilder sb = new StringBuilder();

        boolean firstZero = true;
        while(!dq.isEmpty()) {
            char c = dq.pollLast();
            // 清除先导为 0
            if(firstZero && c == '0') {
                continue;
            }
            firstZero = false;
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
