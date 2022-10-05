package com.burakkirbag.readingisgood.common.model;

import lombok.Data;

import java.util.List;

@Data
public class PagedList<T> {

    private List<T> items = List.of();
    private Integer page;
    private Integer size;
    private Long totalSize;

    public PagedList(List<T> items) {
        this.items = items;
    }

    public PagedList(List<T> items, Integer page, Integer size, Long totalSize) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalSize = totalSize;
    }
}
