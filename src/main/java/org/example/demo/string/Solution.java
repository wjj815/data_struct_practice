package org.example.demo.string;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * 给你一个二进制串 s  （一个只包含 0 和 1 的字符串），我们可以将 s 分割成 3 个 非空 字符串 s1, s2, s3 （s1 + s2 + s3 = s）。
     *
     * 请你返回分割 s 的方案数，满足 s1，s2 和 s3 中字符 '1' 的数目相同。
     *
     * 由于答案可能很大，请将它对 10^9 + 7 取余后返回。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-ways-to-split-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 1573. 分割字符串的方案数
     *
     * @param s 字符串
     * @return
     */
    public int numWays(String s) {
        final int MOD = 1000000007;
        List<Integer> ones = new ArrayList<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        int m = ones.size();
        if (m % 3 != 0) {
            return 0;
        }
        if (m == 0) {
            /*
              说明 1 的个数为 0，则在 n-1个分割点里选取两个
             */
            long way = (long) (len - 1) * (len - 2) / 2;
            return (int) (way % MOD);
        } else {
            // 若 1 的个数可以分为 3 份，则可以分割
            int index1 = m / 3, index2 = m / 3 * 2;
            int cont1 = ones.get(index1) - ones.get(index1 - 1);
            int cont2 = ones.get(index2) - ones.get(index2 - 1);
            long way = (long) cont1 * cont2;
            return (int) (way % MOD);
        }

    }


}
