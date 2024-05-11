package com.talenthub.empresanominamicroservice.controller;

import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pay")
public class PayController {
    
    @Autowired
    private PayService payService;

    @CrossOrigin
    @GetMapping("/getPays")
    public Iterable<Pay> getAllPays() {
        return payService.getAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<Pay> getPayById(@PathVariable Long id) {
        return payService.getById(id);
    }

    @CrossOrigin
    @PostMapping("/createPay")
    public Pay createPay(@RequestBody Pay Pay) {
        return payService.create(Pay);
    }

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
