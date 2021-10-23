package org.example.demo.sort;

public class TestSort {

    public static void main(String[] args) {

        SortContext sortContext = new SortContext();
        sortContext.setArrLen(1000000);
        sortContext.setPrintArr(false);
        BaseSort.setSortContext(sortContext);
        QuickSort quickSort = new QuickSort();
        quickSort.run();

        MergeSort mergeSort = new MergeSort();
        mergeSort.run();


    }
}
