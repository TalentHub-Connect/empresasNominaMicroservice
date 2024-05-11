package com.talenthub.empresanominamicroservice.service;

import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public Iterable<Contract> getAll(){
        return contractRepository.findAll();
    }

    public Optional<Contract> getById(Long id){
        return contractRepository.findById(id);
    }

    public Contract create(Contract contract){
        return contractRepository.save(contract);
    }

    public Contract update(Contract contract){
        return contractRepository.save(contract);
    }
    
}
