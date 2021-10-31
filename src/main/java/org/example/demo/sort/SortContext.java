package org.example.demo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortContext {

    private List<ConfigurableRunnableSort> configurableRunnableSortList;

    private final SortConfig sortConfig;

    public SortContext(SortConfig sortConfig, ConfigurableRunnableSort... configurableRunnableSortList) {
        this.configurableRunnableSortList = new ArrayList<>();
        if(configurableRunnableSortList.length != 0) {
            this.configurableRunnableSortList.addAll(Arrays.asList(configurableRunnableSortList));
        }

        this.sortConfig = sortConfig;
    }

    public void addSort(ConfigurableRunnableSort sort) {
        configurableRunnableSortList.add(sort);
    }

    public void run() {

        for (ConfigurableRunnableSort configurableRunnableSort : configurableRunnableSortList) {
            configurableRunnableSort.setSortConfig(sortConfig);
            configurableRunnableSort.run();
        }
    }
}
