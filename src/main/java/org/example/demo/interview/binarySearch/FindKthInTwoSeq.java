package org.example.demo.interview.binarySearch;


/**
 * 在两个有序数列中找到第 k 小
 * 时间复杂度为 O(log n)
 * <p>
 * 思路为：二分查找
 * 例：
 * 输入：
 * 10 4 8
 * 1, 2, 3, 3, 4, 4, 5, 5, 6, 6
 * 4, 7, 10, 20
 * <p>
 * 输出 5
 */
public class FindKthInTwoSeq {


    public int findKth(int[] arr1, int[] arr2, int k) {
        return findKth(arr1, arr2, 0, arr1.length - 1, 0, arr2.length - 1, k);
    }

    /**
     * @param a  第一个数组 a
     * @param b  第二个数组 b
     * @param la a 左边界
     * @param ra a 右边界
     * @param lb b 左边界
     * @param rb b 右边界
     * @param k  第 k 小
     * @return 第 k 小的值
     */
    public int findKth(int[] a, int[] b, int la, int ra, int lb, int rb, int k) {

        // a 边界判断
        if (la > ra) return b[lb + k - 1];
        // b 边界判断
        if (lb > rb) return a[la + k - 1];
        // 获取 a[la, ra] 的中间数
        int ma = (la + ra) / 2;
        // 获取 b[lb, rb] 的中间数
        int mb = (lb + rb) / 2;
        // 获取 a[la, ma] + b[lb, mb] 的区间长度
        int halfLen = ma - la + 1 + mb - lb + 1;

        // 表示a[la, ma]中的数小于 b[mb]
        if (a[ma] < b[mb]) {
            // 如果 k 在 a[la, ma] + b[lb, mb]的区间里，
            // 继续考察a[la, ra] + b[lb, mb - 1]
            if (k < halfLen) {
                return findKth(a, b, la, ra, lb, mb - 1, k);
            }
            // 否则
            // 考察 a[ma + 1, ra] + b[lb, rb] 中第(k - (ma - la + 1))小
            // 因为可以排除 a[la, ma]从而缩短考察区间
            return findKth(a, b, ma + 1, ra, lb, rb, k - (ma - la + 1));
        }
        // // 表示b[lb, mb]中的数小于等于 a[ma]
        else {
            // 如果 k 在 a[la, ma] + b[lb, mb]的区间里，
            // 继续考察b[lb, mb] + a[la, ma - 1]
            if (k < halfLen) {
                return findKth(a, b, la, ma - 1, ra, rb, k);
            }
            // 否则
            // 考察 b[mb + 1, rb] + a[la, ra] 中第(k - (mb - lb + 1))小
            // 因为可以排除 b[lb, mb]从而缩短考察区间
            return findKth(a, b, la, ra, mb + 1, rb, k - (mb - lb + 1));
        }
    }


    /**
     * 迭代法
     * @param arr1
     * @param arr2
     * @param k
     * @return
     */
    public int findKth2(int[] arr1, int[] arr2, int k) {
        int al = 0, ar = arr1.length - 1;
        int bl = 0, br = arr2.length - 1;

        while (al <= ar && bl <= br) {
            int am = al + (ar - al) / 2;
            int bm = bl + (br - bl) / 2;
            // 获取 a[la, ma] + b[lb, mb] 的区间长度
            int halfLen = am - al + 1 + bm - bl + 1;
            if (arr1[am] < arr2[bm]) {
                if (k < halfLen) {
                    br = bm - 1;
                } else {
                    k -= am - al + 1;
                    al = am + 1;
                }
            } else {
                if (k < halfLen) {
                    ar = am - 1;
                } else {
                    k -= bm - bl + 1;
                    bl = bm + 1;
                }
            }
        }

        // 边界判断
        if (al > ar && bl + k - 1 < arr2.length) return arr2[bl + k - 1];
        // b 边界判断
        if (bl > br && al + k - 1 < arr1.length) return arr1[al + k - 1];

        return -1;
    }


    public static void main(String[] args) {

        int m = 10;
        int n = 4;
        int[] arr1 = {1, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        int[] arr2 = {4, 7, 10, 20};
        int k = 15;
        int kth = new FindKthInTwoSeq().findKth2(arr1, arr2, k);
        System.out.println(kth);
    }

}
