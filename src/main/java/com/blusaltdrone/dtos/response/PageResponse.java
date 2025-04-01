package com.blusaltdrone.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T>  {
    private int totalElements;
    private int totalPages;
    private boolean hasNext;
    private T content;
}
