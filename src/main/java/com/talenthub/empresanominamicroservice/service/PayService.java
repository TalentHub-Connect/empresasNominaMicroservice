package com.talenthub.empresanominamicroservice.service;
/**
 * Developed by: Juan Felipe Arias
 */

import com.talenthub.empresanominamicroservice.model.News;
import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing pays.
 */
@Service
public class PayService {
    
    @Autowired
    private PayRepository payRepository;

    @Autowired
    private NewsService newsService;

    /**
     * @name getAll
     * @description Retrieves all existing pays.
     *
     * @return An iterable list of pays
     */
    public Iterable<Pay> getAll(){
        return payRepository.findAll();
    }

    /**
     * @name getById
     * @description Retrieves pay by its ID.
     *
     * @param id the ID of the pay
     * @return An optional containing the pay with the specified ID, if exists
     */
    public Optional<Pay> getById(Long id){
        return payRepository.findById(id);
    }

    /**
     * @name getPayByEmployeeId
     * @description Retrieves pay by its Employee's Id.
     *
     * @param id the ID of the pay
     * @return An optional containing the pay with the specified ID, if exists
     */
    public Pay getPayByEmployeeId(Long id){

        try{
            Iterable<Pay> allPays = getAll();

            for(Pay pay : allPays){
                if(pay.getEmployeeid().longValue() == id){
                    return pay;
                }
            }
        }catch (Exception e){
            System.err.print(e.getMessage());
        }

        return null;
    }

    /**
     * @name getPayWithNewsById
     * @description Retrieves pay with news by its Employee's Id.
     *
     * @param id the ID of the employee
     * @return A Pay with the specified ID, if exists
     */
    public Pay getPayWithNewsById(Long id) {

        Pay payOfEmployee = getPayByEmployeeId(id);
        List<News> newsOfPay = newsService.getNewByEmployee(id);

        if(!newsOfPay.isEmpty()){

            Double salary = 0d;
            Double actualSalary = payOfEmployee.getDiscount();

            for(News n : newsOfPay){
                salary += n.getMoneybenefit().doubleValue();
            }

            payOfEmployee.setDiscount(actualSalary + salary);
        }

        return payOfEmployee;
    }

    /**
     * @name create
     * @description Creates a new pay.
     *
     * @param pay the details of the pay to create
     * @return The newly created pay
     */
    public Pay create(Pay pay){
        return payRepository.save(pay);
    }

    /**
     * @name update
     * @description Updates an existing pay.
     *
     * @param pay the pay to update
     * @return The updated pay
     */
    public Pay update(Pay pay){
        return payRepository.save(pay);
    }
    
}
