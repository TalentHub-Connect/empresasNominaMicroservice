package com.talenthub.empresanominamicroservice.repository;

import com.talenthub.empresanominamicroservice.model.candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface candidateRepository extends JpaRepository<candidate, Long> {
}
