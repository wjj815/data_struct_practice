package org.example.demo.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 堆排序
 */
public class HeapSort extends ArraySort {
    @Override
    protected String getName() {
        return "堆排序";
    }

    @Override
    public void sort(int[] arr) {
        minHeapSort(arr);
    }

    /**
     * 从小到大排序
     * @param arr
     */

    public void minHeapSort(int[] arr) {

        int len = arr.length;
        // 构建小根堆，其中 i = len / 2 - 1,是为了指向最后一个不是叶子结点的树节点
        // 找最后一个非叶子结点的方法：
        // 树节点以 0 为开头,len - 1 为最后一个叶子结点，即 (len - 2) / 2
        // 树节点以 1 为开头， len 为最后一个叶子结点， 即 len 的父节点为len / 2 ,因为数组下标以 0 开头，所以为（len / 2 - 1）
        for (int i = len / 2 - 1; i >= 0 ; i--) {
            adjustHeap(arr, i, len);
        }

        // 将堆顶元素与len - 1 元素交换，然后缩小区间[0, len - 2]调整堆
        // 因为将堆顶元素与堆底最后一个元素互换，且堆大小缩减一个元素，所以当前堆不满足堆的结构，需调整堆使符合堆结构
        // 调整堆的逻辑是将新的堆顶元素放到合适的位置
        for(int i = len - 1; i > 0; i--) {
            swap(arr, i, 0);
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整堆
     * @param arr 数组
     * @param i 当前下标
     * @param len 堆大小
     */
    public void adjustHeap(int[] arr, int i, int len) {
        // 获取当前树节点的左子树节点下标
        int left = 2 * i + 1;
        // 如果左子树节点下标未越界
        if(left < len) {
            // 如果右子树存在，则比较左子树和右子树的值大小，并将下标赋值给 largest
            int largest = left + 1 < len && arr[left] < arr[left + 1]? left + 1 : left;
            // 比较 largest 和当前树节点的值大小，并将下标赋值给 largest
            largest = arr[largest] < arr[i] ? i : largest;

            // 如果 largest 不为 i， 则说明当前树节点不是最大的，调整
            if(i != largest) {
                // 将 下标为largest 值与当前树节点的值交换
                swap(arr, i, largest);
                // 继续调整 largest 对应的树节点（不是左子树节点就是右子树节点），
                adjustHeap(arr, largest , len);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
