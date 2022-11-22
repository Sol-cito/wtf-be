package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.utility.JPQLBuilder;
import com.wtf.webapp.wtfbe.vo.JPQLParamVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class JPQLBuilderService {

    private final EntityManager em;

    public <T> List<T> getJQPLResult(JPQLParamVO jpqlParamVO, Class<T> entityClass) throws ClassNotFoundException {
        Map<String, Class> entityFieldTypeMap = this.getTargetEntityFieldTypeMap(jpqlParamVO);

        JPQLBuilder builder = JPQLBuilder.builder().jpqlParamVO(jpqlParamVO).entityFieldTypeMap(entityFieldTypeMap).build();

        builder.initJpqlQuery();
        builder.addWhereCondition(entityFieldTypeMap);
        builder.addOrderBy();

        TypedQuery<T> typedQuery = em.createQuery(builder.getStringQuery(), entityClass);

        this.bindParametersOfWhereCondition(typedQuery, jpqlParamVO, entityFieldTypeMap);
        this.setRetrievedNumber(typedQuery, jpqlParamVO);
        return typedQuery.getResultList();
    }

    private Map<String, Class> getTargetEntityFieldTypeMap(JPQLParamVO jpqlParamVO) throws ClassNotFoundException {
        Class entityClass = Class.forName(jpqlParamVO.getEntityName());
        Map<String, Class> entityFieldTypeMap = new HashMap<>();
        for (Field field : entityClass.getDeclaredFields()) {
            entityFieldTypeMap.put(field.getName(), field.getType());
        }
        return entityFieldTypeMap;
    }

    public void bindParametersOfWhereCondition(Query query, JPQLParamVO jpqlParamVO, Map<String, Class> entityFieldTypeMap) {
        if (jpqlParamVO.isWhereConditionEmpty()) return;
        for (String fieldName : jpqlParamVO.getWhereCondition().keySet()) {
            String fieldValue = jpqlParamVO.getWhereCondition().get(fieldName);
            Class fieldType = entityFieldTypeMap.get(fieldName);
            query.setParameter(fieldName, fieldType.cast(fieldValue));
        }
    }

    public void setRetrievedNumber(Query query, JPQLParamVO jpqlParamVO) {
        if (jpqlParamVO.getStartIdx() != null && jpqlParamVO.getStartIdx().intValue() >= 0) {
            query.setFirstResult(jpqlParamVO.getStartIdx().intValue());
        }
        if (jpqlParamVO.getLimit() != null && jpqlParamVO.getLimit().intValue() >= 0) {
            query.setMaxResults(jpqlParamVO.getLimit().intValue());
        }
    }
}
