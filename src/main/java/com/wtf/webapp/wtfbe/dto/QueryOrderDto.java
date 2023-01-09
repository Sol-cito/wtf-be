package com.wtf.webapp.wtfbe.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class QueryOrderDto {
    private String entityFieldName;
    private String orderSortKeyword;
}
