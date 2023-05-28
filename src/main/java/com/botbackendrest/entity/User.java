package com.botbackendrest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "users")
@Data
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class User extends Structures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "chat_id")
    private int chatId;

    @Column(name = "user_language")
    private String userLanguage;

    public User(int chatId) {
        this.chatId = chatId;
    }

}
