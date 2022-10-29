package com.project.chatlogserver.services;

import com.project.chatlogserver.entities.Message;
import com.project.chatlogserver.entities.User;
import com.project.chatlogserver.exceptions.ChatLogServerException;
import com.project.chatlogserver.repositories.MessageRepository;
import com.project.chatlogserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatLogServiceImpl implements ChatLogService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Long addChatLogMessage(User user, Message message) throws ChatLogServerException {
        try {
            //check if user exists
            if(!userRepository.findById(user.getUserId()).isPresent()){
                //save in case user does not exist
                userRepository.save(user);
            }
            messageRepository.save(message);
        } catch (Exception e){
            throw new ChatLogServerException("Unable to add message for user : " + user);
        }
        return message.getMessageId();
    }

    @Override
    public List<Message> getChatLogMessage(User user, Integer limit) throws ChatLogServerException {
        List<Message> chatLogs;
        try {
            //check if user exists
            if(!userRepository.findById(user.getUserId()).isPresent()){
                throw new ChatLogServerException("No user found with id : " + user.getUserId());
            }

            Pageable page = PageRequest.of(0, limit, Sort.by("timestamp").descending());
            //get all chat logs with limit
            Page<Message> pagedResult = messageRepository.findByUserId(user, page);
            chatLogs = pagedResult.getContent();
        } catch (Exception e){
            throw new ChatLogServerException("Unable to get chat logs for user : " + user);
        }
        return chatLogs;
    }

    @Override
    public List<Message> getChatLogMessageByFilter(User user, Integer limit, Long start) throws ChatLogServerException {
        List<Message> chatLogs;
        try {
            //check if user exists
            if(!userRepository.findById(user.getUserId()).isPresent()){
                throw new ChatLogServerException("No user found with id : " + user.getUserId());
            }

            Pageable page = PageRequest.of(0, limit, Sort.by("timestamp").descending());
            //get all chat logs with limit
            Page<Message> pagedResult = messageRepository.findByIdAndUser(user, start, page);
            chatLogs = pagedResult.getContent();
        } catch (Exception e){
            throw new ChatLogServerException("Unable to get chat logs for user : " + user);
        }
        return chatLogs;
    }

    @Override
    @Transactional
    public void deleteAllChatLogs(User user) throws ChatLogServerException {

        try {
            //check if chat log by user exists
            Pageable page = PageRequest.of(0, 10, Sort.by("timestamp").descending());
            Page<Message> result = messageRepository.findByUserId(user, page);

            if(result.getTotalElements() == 0){
                throw new ChatLogServerException("user not found");
            }
            //delete chat log
            messageRepository.deleteByUser(user);
        } catch (Exception e){
            throw new ChatLogServerException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteChatLogs(User user, Long msgid) throws ChatLogServerException {
        try {
            //check if chat log with user and msgid exists
            Pageable page = PageRequest.of(0, 10, Sort.by("timestamp").descending());
            Page<Message> pagedResult = messageRepository.findByIdAndUser(user, msgid, page);
            if(pagedResult.getTotalElements() == 0){
                throw new ChatLogServerException("message with user" + user + "not found");
            }
            messageRepository.deleteById(msgid);
        } catch (Exception e){
            throw new ChatLogServerException(e.getMessage());
        }
    }
}
