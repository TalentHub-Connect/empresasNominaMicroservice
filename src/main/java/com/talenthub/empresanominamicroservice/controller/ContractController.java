package com.talenthub.empresanominamicroservice.controller;
/**
 * Developed by: Juan Felipe Arias.
 */

import com.talenthub.empresanominamicroservice.exception.ContractNotFoundException;
import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.payload.request.ContractRequest;
import com.talenthub.empresanominamicroservice.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
     * @return An iterable list of contracts.
     * @name getAllContracts
     * @description Retrieves all existing contracts.
     */

    @Operation(summary = "Get all contracts")
    @ApiResponse(responseCode = "200", description = "Found the contracts")
    @ApiResponse(responseCode = "404", description = "Contracts not found")
    @GetMapping("/getContracts")
    public Iterable<Contract> getAllContracts() {
        return contractService.getAll();
    }

    /**
     * @param id The Id of the contract to find.
     * @return The contract with the specified Id, if existed.
     * @name getContractById
     * @description Retrieves a contract by its Id.
     */

    @Operation(summary = "Get contract by id")
    @ApiResponse(responseCode = "200", description = "Found the contract")
    @ApiResponse(responseCode = "404", description = "Contract not found")
    @GetMapping("/{id}")
    public Optional<Contract> getContractById(@PathVariable Integer id) {
        return contractService.getById(id);
    }

    /**
     * @param contract The information of the contract before created.
     * @return A newly created contract.
     * @name createContract
     * @description Creates a new contract.
     */

    @Operation(summary = "Create contract")
    @ApiResponse(responseCode = "201", description = "Contract created")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @PostMapping("/createContract")
    public ResponseEntity<?> createContract(@RequestBody ContractRequest contract) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(contractService.create(contract));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param id              The Id of the contract to update.
     * @param contractDetails The details of the updated contract.
     * @return The updated contract.
     * @name updateContract
     * @description Creates a new contract.
     */

    @Operation(summary = "Update contract")
    @ApiResponse(responseCode = "200", description = "Contract updated")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @PutMapping("/updateContract/{id}")
    public Contract updateContract(@PathVariable Integer id, @RequestBody ContractRequest contractDetails) {
        try {
            return contractService.update(id, contractDetails);

        } catch (Exception e) {
            throw new ContractNotFoundException("Contract not found with id: " + id);
        }
    }
}
