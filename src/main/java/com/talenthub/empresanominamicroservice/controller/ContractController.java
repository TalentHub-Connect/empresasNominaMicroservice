package com.talenthub.empresanominamicroservice.controller;

import com.talenthub.empresanominamicroservice.model.Contract;
import com.talenthub.empresanominamicroservice.model.Employee;
import com.talenthub.empresanominamicroservice.service.ContractService;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @CrossOrigin
    @GetMapping("/getContracts")
    public Iterable<Contract> getAllContracts() {
        return contractService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<Contract> getContractById(@PathVariable Long id) {
        return contractService.getById(id);
    }

    @CrossOrigin
    @PostMapping("/createContract")
    public Contract createContract(@RequestBody Contract contract) {
        return contractService.create(contract);
    }

    @CrossOrigin
    @PutMapping("/updateContract/{id}")
    public Contract updateContract(@PathVariable Long id, @RequestBody Contract contractDetails){
        Optional<Contract> optionalcontract = contractService.getById(id);

        Contract contract = optionalcontract.get();

        contract.setDescription(contractDetails.getDescription());
        contract.setSalary(contractDetails.getSalary());
        contract.setCharge(contractDetails.getCharge());
        contract.setStartdate(contractDetails.getStartdate());
        contract.setEnddate(contractDetails.getEnddate());
        contract.setEps(contractDetails.getEps());
        contract.setContractType(contractDetails.getContractType());
        contract.setCandidateId(contractDetails.getCandidateId());

        return contractService.update(contract);

    }


}
