package com.example.r2s_mobile_store.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PaginationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<?> contents;
    private boolean isFirst;
    private boolean isLast;
    private int totalPages;
    private long totalItems;
    private int limit;
    private int no;
}
