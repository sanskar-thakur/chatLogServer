package com.project.chatlogserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChatLogServerException extends RuntimeException{
    public ChatLogServerException(String message){
        super(message);
    }
}
