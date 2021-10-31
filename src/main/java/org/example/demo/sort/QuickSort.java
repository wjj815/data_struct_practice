package org.example.demo.sort;

import java.util.LinkedList;
import java.util.Queue;

public class QuickSort extends ArraySort {

    @Override
    protected String getName() {
        return "快速排序";
    }

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 不递归版的快速排序
     * @param arr
     * @param l
     * @param r
     */
    private void quickSortUnRecur(int[] arr, int l, int r) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{l, r});
        while (!queue.isEmpty()) {
            int[] range = queue.poll();
            l = range[0];
            r = range[1];

            // 获取基准数，使用随机数,随机数的取值在[0,1)
            int slot = l + (int) ((r - l + 1) * Math.random());
///            System.out.println("基准数： "+ slot);

            // 将 随机的基准数与当前区间的右边界互换位置
            swap(arr, slot, r);
            // 以基准数为参考，左小区间（比基准数小）和右大区间（比基准数大）
            int[] partition = partition(arr, l, r);
            if (l < partition[0]) {
                queue.offer(new int[]{l, partition[0]});
            }
            if (partition[1] < r) {
                queue.offer((new int[]{partition[1], r}));
            }

        }

    }

    /**
     * 快速排序
     *
     * @param arr 待排序数组
     * @param l   数组左闭边界
     * @param r   数组右闭边界
     */
    private void quickSort(int[] arr, int l, int r) {
        // 当[l, r]区间数 > 2时
        if (l < r) {
            // 获取基准数，使用随机数,随机数的取值在[0,1)
            int slot = l + (int) ((r - l + 1) * Math.random());
///            System.out.println("基准数： "+ slot);
            // 将 随机的基准数与当前区间的右边界互换位置
            swap(arr, slot, r);
            // 以基准数为参考，获取左区间右边界（比基准数小）和右区间的左边界（比基准数大）
            int[] partition = partition(arr, l, r);
            // 继续 处理左区间
            quickSort(arr, l, partition[0] - 1);
            // 继续处理右区间
            quickSort(arr, partition[1] + 1, r);
        }
    }

    /**
     * 交换数组中两个下标的元素
     * @param arr 数组
     * @param i 下标 i
     * @param j 下标 j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 基于基准数分区间，将比基准数小的放在左边， 比基准数大的放在右边，与基准数相同的放在中间
     * @param arr 数组
     * @param l 区间左闭边界
     * @param r 区间右闭边界
     * @return 基准数的区间
     */
    private int[] partition(int[] arr, int l, int r) {
        /**
         * arr[r] 为基准数
         */
        // 左区间边界
        int less = l - 1;
        // 右区间边界
        int more = r;
        // 遍历区间的指针
        int cur = l;

        // 当 l 指针未与 右区间碰撞时
        while (cur < more) {
            // l 指针指向的元素比基准数小
            if (arr[cur] < arr[r]) {
                // 将 l 指向的元素arr[l]与左区间的边界元素arr[++less]交换，并将 l 指针 + 1
                swap(arr, ++less, cur++);
            } else if (arr[cur] > arr[r]) {
                // 将 arr[--more]与 arr[l] 元素交换，因为交换后的元素并未考察，所以不移动 l 指针
                swap(arr, --more, cur);
            } else {
                // arr[l] == arr[r] 时
                cur++;
            }
        }
        // 将基准数换到中间
        swap(arr, r, more);

        // 这时，数组已分为三个区间
        // 左区间( < 基准数) [l, less],
        // 中区间（ = 基准数 ） [less + 1, more],
        // 右区间（ > 基准数） [more + 1, r]
        //下行代码返回基准数的中区间
        return new int[]{less + 1, more};
    }
}
