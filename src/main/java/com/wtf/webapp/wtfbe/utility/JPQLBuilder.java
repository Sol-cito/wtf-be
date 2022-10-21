package com.wtf.webapp.wtfbe.utility;

import com.wtf.webapp.wtfbe.vo.JPQLParamVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JPQLBuilder {
    @Builder.Default
    private StringBuilder sb = new StringBuilder();
    private JPQLParamVO jpqlParamVO;
    private Map<String, Class> entityFieldTypeMap;

    public void initJpqlQuery() {
        this.sb.append("SELECT entity FROM ").append(this.jpqlParamVO.getEntityName()).append(" AS entity ");
    }

    public void addWhereCondition(Map<String, Class> entityFieldTypeMap) throws ClassNotFoundException {
        if (this.jpqlParamVO.isWhereConditionEmpty()) return;

        Set<String> whereConditionFieldNameSet = this.jpqlParamVO.getWhereCondition().keySet();

        int cnt = 0;
        for (String fieldName : whereConditionFieldNameSet) {
            if (!entityFieldTypeMap.containsKey(fieldName)) {
                throw new ClassNotFoundException(); // TO-DO : customized exception needed
            }
            this.sb.append("entity.").append(fieldName).append("=:").append(fieldName);
            if (cnt < whereConditionFieldNameSet.size() - 1) {
                this.sb.append(" AND ");
            }
            cnt++;
        }
    }
    public void addOrderBy() {
        if (this.jpqlParamVO.isOrderEmpty()) return;
        this.sb.append(" ORDER BY ")
                .append("entity.")
                .append(this.jpqlParamVO.getOrder().getEntityFieldName())
                .append(" ")
                .append(this.jpqlParamVO.getOrder().getOrderSortKeyword());
    }

    public String getStringQuery() {
        return this.sb.toString();
    }
}
