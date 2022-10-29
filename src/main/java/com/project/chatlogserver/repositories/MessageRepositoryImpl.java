package com.project.chatlogserver.repositories;

import com.project.chatlogserver.entities.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageRepositoryImpl{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Message> findByMessageId(String id, int limit){
        return entityManager.createQuery("Select m from Message m ORDER BY m.timestamp").setMaxResults(limit).getResultList();
    }

}
