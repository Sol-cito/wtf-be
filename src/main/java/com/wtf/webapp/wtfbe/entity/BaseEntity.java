package com.wtf.webapp.wtfbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private Date regiDatetime;

    private String regiUser;

    @LastModifiedDate
    private Date modiDatetime;

    private String modiUser;
}