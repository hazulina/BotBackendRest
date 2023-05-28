package com.botbackendrest.service.userService;

import com.botbackendrest.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByChatId(int chatId);
    void saveOrUpdateUser(User user);
    void deleteUserByChatId(int chatId);
    void updateUserLanguage(int chatId, String language);
}
