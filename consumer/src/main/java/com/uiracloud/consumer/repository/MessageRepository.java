package com.uiracloud.consumer.repository;

import com.uiracloud.consumer.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}