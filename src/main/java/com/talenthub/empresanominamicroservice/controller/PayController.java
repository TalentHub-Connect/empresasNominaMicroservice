package com.talenthub.empresanominamicroservice.controller;

/**
 * Developed by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.*;
import com.talenthub.empresanominamicroservice.service.ContractService;
import com.talenthub.empresanominamicroservice.service.EmployeeService;
import com.talenthub.empresanominamicroservice.service.NewsService;
import com.talenthub.empresanominamicroservice.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller to manage operations related to pay.
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ContractService contractService;

    /**
     * @name getAllPays
     * @description Retrieves all existing pays.
     * @param id - CompanyId
     *
     * @return An iterable list of pays
     */
    @Operation(summary = "Get all pays")
    @ApiResponse(responseCode = "200", description = "Found the pays")
    @ApiResponse(responseCode = "404", description = "Pays not founded")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/getPays/{id}")
    public Iterable<Pay> getAllPays(@PathVariable Integer id) {

        List<Pay> paysResponse = new ArrayList<>();

        List<Employee> allEmployees = employeeService.getAllEmployeesByCompanyId(id);

        for(Employee e : allEmployees){
            paysResponse.add(getPayById(e.getId().longValue()));
        }

        return paysResponse;
    }

    /**
     * @name getAllPays
     * @description Retrieves all existing pays without news values.
     *
     * @return An iterable list of pays
     */
    @Operation(summary = "Get all net pays")
    @ApiResponse(responseCode = "200", description = "Found the pays")
    @ApiResponse(responseCode = "404", description = "Pays not founded")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/getNetPays/{id}")
    public Iterable<Pay> getAllNetPays(@PathVariable Integer id) {

        List<Pay> paysResponse = new ArrayList<>();

        List<Employee> allEmployees = employeeService.getAllEmployeesByCompanyId(id);

        for(Employee e : allEmployees){
            paysResponse.add(payService.getPayByEmployeeId(e.getId().longValue()));
        }

        return paysResponse;
    }

    /**
     * @name getSalaries
     * @description Add up all employee salaries.
     *
     * @param id the ID of the companyId
     *
     * @return The total salaries
     */

     @Operation(summary = "Get all salaries of employees")
     @ApiResponse(responseCode = "200", description = "Found the salaries")
     @ApiResponse(responseCode = "404", description = "Salaries not founded")
     @ApiResponse(responseCode = "400", description = "Bad request")
     @GetMapping("/getSalaries/{id}")
     public Double getSalaries(@PathVariable Integer id){

         List<Employee> allEmployees = employeeService.getAllEmployeesByCompanyId(id);

         Double salariesTotal = 0d;

         for(Employee e : allEmployees){

             Optional<Contract> c = contractService.getById(e.getContractId().intValue());

             if(c != null && c.isPresent()){
                 salariesTotal += c.get().getSalary() + newsService.getNewTotalByEmployee(e.getId().longValue());
             }

         }

         return salariesTotal;
     }

    /**
     * @name emptyNews
     * @description Set all the News moneybenefit in 0.
     *
     * @param id the ID of the companyId
     *
     * @return Boolean confirmation.
     */

    @Operation(summary = "Collect all the news of a company and set their values in 0.")
    @ApiResponse(responseCode = "200", description = "Found the news")
    @ApiResponse(responseCode = "404", description = "News not founded")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/disperse/{id}")
    public boolean disperse(@PathVariable Integer id) {

        List<Employee> allEmployees = employeeService.getAllEmployeesByCompanyId(id);

        if(allEmployees.isEmpty()){
            return false;
        }

        for (Employee e : allEmployees) {

            List<News> AllnewsOfEmployee = newsService.getNewByEmployee(e.getId().longValue());

            if(!AllnewsOfEmployee.isEmpty()){
                emptyNews(AllnewsOfEmployee);
            }
        }

        return true;
    }


    /**
     * @name emptyNews
     * @description Set all the News moneybenefit in 0.
     *
     * @param newsOfEmployee the News of each Employee.
     *
     * @return Boolean confirmation.
     */
    private void emptyNews(List<News> newsOfEmployee){

        for(News n : newsOfEmployee){
            n.setMoneybenefit(0.0);
            n.setStatus("No revisado");
            newsService.update(n);
        }

    }

    /**
     * @name getPayById
     * @description Retrieves a pay by its ID.
     *
     * @param id the ID of the employee.
     * @return An optional containing the pay with the specified ID, if exists
     */
    @Operation(summary = "Get Pay by id")
    @ApiResponse(responseCode = "200", description = "Found the Pay")
    @ApiResponse(responseCode = "404", description = "Pay not found")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @GetMapping("/{id}")
    public Pay getPayById(@PathVariable Long id) {

        Pay payOfEmployee = payService.getPayByEmployeeId(id);
        Optional<Contract> contract = contractService.getById(employeeService.getById(id.intValue()).getContractId().intValue());
        List<News> newsOfPay = newsService.getNewByEmployee(id);

        if(!newsOfPay.isEmpty() && contract.isPresent()){

            Double salary = 0d;
            Double actualSalary = contract.get().getSalary();

            for(News n : newsOfPay){
                salary += n.getMoneybenefit().doubleValue();
            }

            payOfEmployee.setDiscount(actualSalary + salary);
        }

        return payOfEmployee;
    }

    /**
     * @name createPay
     * @description Creates a new pay.
     *
     * @param pay the details of the pay to create
     * @return The newly created pay
     */
    @PostMapping("/createPay")
    public Pay createPay(@RequestBody Pay pay) {
        return payService.create(pay);
    }

    /**
     * @name updatePay
     * @description Updates an existing pay.
     *
     * @param id the ID of the pay to update
     * @param payDetails  the details of the updated pay
     * @return The updated pay
     */
    @PutMapping("/updatePay/{id}")
    public Pay updatePay(@PathVariable Long id, @RequestBody Pay payDetails){
        Optional<Pay> optionalPay = payService.getById(id);

        Pay pay = optionalPay.get();

        pay.setPaydate(payDetails.getPaydate());
        pay.setDiscount(payDetails.getDiscount());
        pay.setDiscountreason(payDetails.getDiscountreason());
        pay.setStatus(payDetails.getStatus());
        pay.setEmployeeid(payDetails.getEmployeeid());

        return payService.update(pay);

    }

}
