package com.talenthub.empresanominamicroservice.repository;

import com.talenthub.empresanominamicroservice.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
