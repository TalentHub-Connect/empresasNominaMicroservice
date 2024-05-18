package com.talenthub.empresanominamicroservice.controller;
/**
 * Developed by: Juan Felipe Arias.
 */

import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.payload.request.ContractDTO;
import com.talenthub.empresanominamicroservice.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller to manage the petitions related to contracts.
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    /**
     * @name getAllContracts
     * @description Retrieves all existing contracts.
     *
     * @return An iterable list of contracts.
     */

    @CrossOrigin
    @GetMapping("/getContracts")
    public Iterable<Contract> getAllContracts() {
        return contractService.getAll();
    }

    /**
     * @name getContractById
     * @description Retrieves a contract by its Id.
     *
     * @param id The Id of the contract to find.
     *
     * @return The contract with the specified Id, if existed.
     */

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<Contract> getContractById(@PathVariable Long id) {
        return contractService.getById(id);
    }

    /**
     * @name createContract
     * @description Creates a new contract.
     *
     * @param contract The information of the contract before created.
     *
     * @return A newly created contract.
     */

    @PostMapping("/createContract")
    public ResponseEntity<?> createContract(@RequestBody ContractDTO contract) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.create(contract));
    }

    /**
     * @name updateContract
     * @description Creates a new contract.
     *
     * @param id The Id of the contract to update.
     * @param contractDetails The details of the updated contract.
     *
     * @return The updated contract.
     */

    @PutMapping("/updateContract/{id}")
    public Contract updateContract(@PathVariable Long id, @RequestBody Contract contractDetails){
        Optional<Contract> optionalcontract = contractService.getById(id);

        Contract contract = optionalcontract.get();

        contract.setDescription(contractDetails.getDescription());
        contract.setSalary(contractDetails.getSalary());
        contract.setCharge(contractDetails.getCharge());
        contract.setStartDate(contractDetails.getStartDate());
        contract.setEndDate(contractDetails.getEndDate());
        contract.setEps(contractDetails.getEps());
        contract.setContractType(contractDetails.getContractType());
        contract.setCandidateId(contractDetails.getCandidateId());

        return contractService.update(contract);

    }


}
