package com.wtf.webapp.wtfbe.vo;

import com.wtf.webapp.wtfbe.dto.QueryOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class JPQLParamVO {
    private String entityName;
    private Map<String, String> whereCondition;
    private Integer startIdx;
    private Integer limit;
    private QueryOrderDto order;

    public boolean isWhereConditionEmpty() {
        return whereCondition == null || whereCondition.isEmpty();
    }

    public boolean isOrderEmpty() {
        return order == null;
    }
}
