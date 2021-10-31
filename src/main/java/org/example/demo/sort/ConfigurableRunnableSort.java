package org.example.demo.sort;

/**
 * 可运行的排序
 */

public interface ConfigurableRunnableSort extends Runnable {

    /**
     * 排序配置
     * @param sortConfig
     */
    void setSortConfig(SortConfig sortConfig);
}
