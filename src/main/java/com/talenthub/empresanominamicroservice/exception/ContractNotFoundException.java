package com.talenthub.empresanominamicroservice.exception;

public class ContractNotFoundException extends RuntimeException{
    public ContractNotFoundException(String message) {
        super(message);
    }
}
