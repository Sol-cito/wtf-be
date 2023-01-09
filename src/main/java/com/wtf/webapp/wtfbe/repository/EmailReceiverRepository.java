package com.wtf.webapp.wtfbe.repository;

import com.wtf.webapp.wtfbe.entity.EmailReceiverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailReceiverRepository extends JpaRepository<EmailReceiverEntity, Integer> {
}
