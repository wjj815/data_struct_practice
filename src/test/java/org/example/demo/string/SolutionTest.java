package org.example.demo.string;

import org.junit.Test;

public class SolutionTest {

    /**
     * 输入：s = "10101"
     * 输出：4
     * 解释：总共有 4 种方法将 s 分割成含有 '1' 数目相同的三个子字符串。
     * "1|010|1"
     * "1|01|01"
     * "10|10|1"
     * "10|1|01"
     */
    @Test
    public void numWays() {
        Solution solution = new Solution();
        int i = solution.numWays("10101");
        System.out.println(i);
    }
}