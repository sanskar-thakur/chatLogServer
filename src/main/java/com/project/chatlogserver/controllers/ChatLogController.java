package com.project.chatlogserver.controllers;

import com.project.chatlogserver.entities.Message;
import com.project.chatlogserver.entities.User;
import com.project.chatlogserver.services.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chatlogs")
public class ChatLogController {

    @Autowired
    private ChatLogService chatLogService;

    @PostMapping("/{user}")
    public ResponseEntity<String> addLogs(@PathVariable String user,
                                        @RequestParam String message,
                                        @RequestParam long timestamp,
                                        @RequestParam boolean isSent){
        //create user entity
        User userObj = new User();
        userObj.setUserId(user);

        //create message entity
        Message messageObj = new Message();
        messageObj.setMessageText(message);
        messageObj.setTimestamp(timestamp);
        messageObj.setSent(isSent);
        messageObj.setUser(userObj);

        Long messageId = chatLogService.addChatLogMessage(userObj, messageObj);
        return new ResponseEntity<>("Added message with id : " + messageId, HttpStatus.OK);
    }

    @GetMapping("/{user}")
    public ResponseEntity<List<Message>> getLogs(@PathVariable String user,
                                                 @RequestParam(defaultValue = "10") Integer limit,
                                                 @RequestParam(name = "start") Optional<Long> start
                                                 ){
        List<Message> messages;
        //check if start message id is present
        if(start.isPresent()) {
            messages = chatLogService.getChatLogMessageByFilter(new User(user),limit, start.get());
        }
        else{
            messages = chatLogService.getChatLogMessage(new User(user), limit);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<String> deleteAllChatLogs(@PathVariable String user){
        chatLogService.deleteAllChatLogs(new User(user));
        return new ResponseEntity<>("Chat logs deleted for user : " + user, HttpStatus.OK);
    }

    @DeleteMapping("/{user}/{msgId}")
    public ResponseEntity<String> deleteChatLogs(@PathVariable String user,
                                                 @PathVariable long msgId){
        chatLogService.deleteChatLogs(new User(user),msgId);
        return new ResponseEntity<>("Chat log with message id : " + msgId + " deleted for user : " + user, HttpStatus.OK);
    }
}
