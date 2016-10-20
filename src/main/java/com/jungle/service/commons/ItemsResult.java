package com.jungle.service.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jungle
 * @version Created on 2016/3/24.
 */
public class ItemsResult<T> {
    public ItemsResult() {
        initEmpty();
    }

    public ItemsResult(List<T> list) {
        if(null == list) {
            this.initEmpty();
        }
        else {
            this.count = list.size();
            this.items = list;
        }
    }

    private void initEmpty() {
        this.count = 0;
        this.items = new ArrayList<>();
    }
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    private long count = 0;
    private List<T> items = new ArrayList<>();
}
