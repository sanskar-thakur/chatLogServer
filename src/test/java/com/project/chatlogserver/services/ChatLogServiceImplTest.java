package com.project.chatlogserver.services;

import com.project.chatlogserver.entities.Message;
import com.project.chatlogserver.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatLogServiceImplTest {

    @Autowired
    private ChatLogService chatLogService;

    @Test
    void addChatLogMessage() {

        User user = User.builder()
                .userId("1012")
                .build();
        Message message = Message.builder()
                .messageText("Fourth message")
                .timestamp(1335)
                .user(user)
                .build();
        chatLogService.addChatLogMessage(user,message);
    }

    @Test
    void getChatLogs() {
        User user = User.builder()
                .userId("1001")
                .build();

        System.out.println(chatLogService.getChatLogMessage(user, 10));
    }

    @Test
    void getChatLogsByFilter() {
        User user = User.builder()
                .userId("1001")
                .build();

        System.out.println(chatLogService.getChatLogMessageByFilter(user, 10, 18L));
    }

    @Test
    void deleteAllChatLogs(){
        User user = User.builder()
                .userId("1003")
                .build();

        chatLogService.deleteAllChatLogs(user);
    }

    @Test
    void deleteChatLogs(){
        User user = User.builder()
                .userId("1002")
                .build();

        chatLogService.deleteChatLogs(user,12L);
    }
}