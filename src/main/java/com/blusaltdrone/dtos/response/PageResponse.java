package com.blusaltdrone.dtos.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PageResponse<T>  {
    private int totalElements;
    private int totalPages;
    private boolean hasNext;
    private T content;
}
