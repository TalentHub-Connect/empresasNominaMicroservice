package com.talenthub.empresanominamicroservice.service;

import com.talenthub.empresanominamicroservice.model.candidate;
import com.talenthub.empresanominamicroservice.repository.candidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class candidateService {

    @Autowired
    private candidateRepository candidateRepository;

    public Iterable<candidate> getAllUsers(){
        return candidateRepository.findAll();
    }

}
