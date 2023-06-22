package com.botbackendrest.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Objects;

@Entity
@Table(name = "users")
@DynamicInsert
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

    public User(int chatId, String userLanguage) {
        this.chatId = chatId;
        this.userLanguage = userLanguage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && chatId == user.chatId && Objects.equals(userLanguage, user.userLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, chatId, userLanguage);
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public int getUserId() {
        return userId;
    }

    public int getChatId() {
        return chatId;
    }

    public String getUserLanguage() {
        return userLanguage;
    }
}
