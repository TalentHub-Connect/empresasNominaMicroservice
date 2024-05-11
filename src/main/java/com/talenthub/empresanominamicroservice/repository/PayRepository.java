package com.talenthub.empresanominamicroservice.repository;

import com.talenthub.empresanominamicroservice.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
