package com.project.chatlogserver.repositories;

import com.project.chatlogserver.entities.Message;
import com.project.chatlogserver.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query("select m from Message m where m.user = ?1")
    Page<Message> findByUserId(User user, Pageable pageable);

    @Query("select m from Message m where m.user = ?1 and m.messageId >= ?2")
    Page<Message> findByIdAndUser(User user, Long messageId, Pageable pageable);

    @Modifying
    @Query("delete from Message m where m.user = ?1")
    void deleteByUser(User user);
}
