package com.talenthub.empresanominamicroservice.service;

import com.talenthub.empresanominamicroservice.model.Pay;
import com.talenthub.empresanominamicroservice.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayService {
    
    @Autowired
    private PayRepository payRepository;

    public Iterable<Pay> getAll(){
        return payRepository.findAll();
    }

    public Optional<Pay> getById(Long id){
        return payRepository.findById(id);
    }

    public Pay create(Pay Pay){
        return payRepository.save(Pay);
    }

    public Pay update(Pay Pay){
        return payRepository.save(Pay);
    }
    
}
