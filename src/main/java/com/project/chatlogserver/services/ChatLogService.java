package com.project.chatlogserver.services;

import com.project.chatlogserver.entities.Message;
import com.project.chatlogserver.entities.User;
import com.project.chatlogserver.exceptions.ChatLogServerException;

import java.util.List;

public interface ChatLogService {
    Long addChatLogMessage(User user, Message message) throws ChatLogServerException;
    List<Message> getChatLogMessage(User user, Integer limit) throws ChatLogServerException;
    List<Message> getChatLogMessageByFilter(User user, Integer limit, Long start) throws ChatLogServerException;
    void deleteAllChatLogs(User user) throws ChatLogServerException;
    void deleteChatLogs(User user, Long msgid) throws ChatLogServerException;
}
