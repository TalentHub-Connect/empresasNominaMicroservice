package com.talenthub.empresanominamicroservice.service;

import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.payload.request.ContractDTO;
import com.talenthub.empresanominamicroservice.payload.response.ContractResponse;
import com.talenthub.empresanominamicroservice.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing contracts.
 */
@Service
public class ContractService {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    /**
     * @return An iterable list of contracts.
     * @name getAll
     * @description Retrieves all existing contracts.
     */

    public Iterable<Contract> getAll() {
        return contractRepository.findAll();
    }

    /**
     * @param id the ID of the contract.
     * @return An optional containing the contract with the specified ID, if exists.
     * @name getById
     * @description Retrieves a contract by its ID.
     */
    public Optional<Contract> getById(Long id) {
        return contractRepository.findById(id);
    }

    /**
     * @param contractDTO the details of the contract to create.
     * @return The newly created contract.
     * @name create
     * @description Creates a new contract.
     */

    public ContractResponse create(ContractDTO contractDTO) {
        Contract newContract = Contract.builder()
                .description(contractDTO.getDescription())
                .salary(contractDTO.getSalary())
                .charge(contractDTO.getCharge())
                .startDate(contractDTO.getStartDate())
                .endDate(contractDTO.getEndDate())
                .eps(contractDTO.getEps())
                .contractType(contractDTO.getContractType())
                .candidateId(contractDTO.getCandidateId())
                .build();
        Contract savedContract = contractRepository.save(newContract);
        return ContractResponse.builder().contractId(savedContract.getId()).build();
    }

    /**
     * @param contract the contract to update.
     * @return The updated contract.
     * @name update
     * @description Updates an existing contract.
     */

    public Contract update(Contract contract) {
        return contractRepository.save(contract);
    }

}
