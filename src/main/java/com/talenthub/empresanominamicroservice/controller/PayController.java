package com.talenthub.empresanominamicroservice.controller;

/**
 * Developed by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller to manage operations related to pay.
 */
@RestController
@RequestMapping("/pay")
public class PayController {
    
    @Autowired
    private PayService payService;

    /**
     * @name getAllPays
     * @description Retrieves all existing pays.
     *
     * @return An iterable list of pays
     */
    @CrossOrigin
    @GetMapping("/getPays")
    public Iterable<Pay> getAllPays() {
        return payService.getAll();
    }

    /**
     * @name getPayById
     * @description Retrieves a pay by its ID.
     *
     * @param id the ID of the pay
     * @return An optional containing the pay with the specified ID, if exists
     */
    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<Pay> getPayById(@PathVariable Long id) {
        return payService.getById(id);
    }

    /**
     * @name createPay
     * @description Creates a new pay.
     *
     * @param pay the details of the pay to create
     * @return The newly created pay
     */
    @CrossOrigin
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
    @CrossOrigin
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
