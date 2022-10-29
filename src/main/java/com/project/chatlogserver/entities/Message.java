package com.project.chatlogserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_seq",
            sequenceName = "message_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_seq"
    )
    private long messageId;
    private String messageText;
    private long timestamp;
    private boolean isSent;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;
}
