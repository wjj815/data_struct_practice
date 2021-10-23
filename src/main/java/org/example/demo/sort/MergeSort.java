package org.example.demo.sort;

/**
 * @author wangjunjie
 */
public class MergeSort extends BaseSort{

    @Override
    protected String getName() {
        return "归并排序";
    }

    @Override
    protected void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }


    /**
     * 归并排序核心
     * 采用闭区间
     * @param arr 数组
     * @param l 左区间
     * @param r 右区间
     */
    public void mergeSort(int[] arr, int l,  int r) {

        if(l < r) {
            // 获取区间中位数
            int mid = l  + (r - l) / 2;
            // 递归分左区间
            mergeSort(arr, l, mid);
            // 递归分右区间
            mergeSort(arr, mid + 1, r);
            // 合并左右区间
            merge(arr, l, mid, r);
        }
    }

    public void merge(int[] arr, int l, int mid, int r) {
        // 额外辅助数组,大小为arr[l, r]区间大小
        int[] temp = new int[r - l + 1];
        // 设置两个指针，一个指向 l， 一个指向 mid+1
        int i = l, j = mid + 1;
        // 设置 temp 的指针
        int k = 0;

        // 排序合并两个区间arr[l, mid], arr[mid + 1, r]，并将结果放入 temp
        while(i <= mid && j <= r) {
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        // 将[l, mid]区间剩余的元素追加到 temp 里
        while(i <= mid) {
            temp[k++] = arr[i++];
        }
        // 将[mid + 1, r]区间剩余的元素加到 temp 里
        while(j <= r) {
            temp[k++] = arr[j++];
        }
        // 将 排序好的temp 数组复制到 arr 的[l, r]区间里
        for(i = l; i <= r; i++) {
            arr[i] = temp[i - l];
        }
    }
}
