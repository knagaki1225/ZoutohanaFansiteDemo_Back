package com.example.zoutohanafansitedemo.entity.info;

import lombok.Data;

@Data
public class PaginationInfo {
    private int current;
    private int total;

    public PaginationInfo(int current, int total) {
        this.current = current;
        this.total = total;
    }
}
