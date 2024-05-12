package com.talenthub.empresanominamicroservice.service;
/**
 * Developed by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing contracts.
 */
@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    /**
     * @name getAll
     * @description Retrieves all existing contracts.
     *
     * @return An iterable list of contracts.
     */
    public Iterable<Contract> getAll(){
        return contractRepository.findAll();
    }

    /**
     * @name getById
     * @description Retrieves a contract by its ID.
     *
     * @param id the ID of the contract.
     * @return An optional containing the contract with the specified ID, if exists.
     */
    public Optional<Contract> getById(Long id){
        return contractRepository.findById(id);
    }

    /**
     * @name create
     * @description Creates a new contract.
     *
     * @param contract the details of the contract to create.
     * @return The newly created contract.
     */
    public Contract create(Contract contract){
        return contractRepository.save(contract);
    }

    /**
     * @name update
     * @description Updates an existing contract.
     *
     * @param contract the contract to update.
     * @return The updated contract.
     */
    public Contract update(Contract contract){
        return contractRepository.save(contract);
    }
    
}
