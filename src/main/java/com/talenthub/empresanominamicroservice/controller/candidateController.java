package com.talenthub.empresanominamicroservice.controller;

import com.talenthub.empresanominamicroservice.model.candidate;
import com.talenthub.empresanominamicroservice.service.candidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
public class candidateController {

    @Autowired
    private candidateService candidateService;

    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8088"})
    @GetMapping("/getCandidates")
    public Iterable<candidate> getAllCanelaUsers() {
        return candidateService.getAllUsers();
    }


}
