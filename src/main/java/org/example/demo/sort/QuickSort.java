package org.example.demo.sort;

import java.util.LinkedList;
import java.util.Queue;

public class QuickSort extends BaseSort {

    @Override
    protected String getName() {
        return "快速排序";
    }

    @Override
    protected void sort(int[] arr) {
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
        if (l < r) {
            // 获取基准数，使用随机数,随机数的取值在[0,1)
            int slot = l + (int) ((r - l + 1) * Math.random());
///            System.out.println("基准数： "+ slot);
            // 将 随机的基准数与当前区间的右边界互换位置
            swap(arr, slot, r);
            // 以基准数为参考，左小区间（比基准数小）和右大区间（比基准数大）
            int[] partition = partition(arr, l, r);

            quickSort(arr, l, partition[0]);
            quickSort(arr, partition[1], r);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private int[] partition(int[] arr, int l, int r) {
        /**
         * arr[r] 为基准数
         */
        int less = l - 1;
        int more = r;

        while (l < more) {

            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        // 将基准数换到中间
        swap(arr, r, more);

        return new int[]{less, more + 1};
    }
}
