package org.example.demo.sort;

public class BubbleSort extends ArraySort{
    @Override
    protected String getName() {
        return "冒泡排序";
    }

    @Override
    public void sort(int[] arr) {

        int len = arr.length;
        for(int i = 0; i < len; i++) {
            for(int j = len - 1; j > i; j--) {
                if(arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
