package com.botbackendrest.service.userService;

import com.botbackendrest.dao.UserRepository;
import com.botbackendrest.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByChatId(int chatId) {
        Optional<User> optional = userRepository.findUserByChatId(chatId);
        return optional.orElse(null);
    }

    @Override
    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserByChatId(int chatId) {
        userRepository.deleteUserByChatId(chatId);
    }

    @Override
    public void updateUserLanguage(int chatId, String language) {
        User user = getUserByChatId(chatId);
        if (user != null) {
            user.setUserLanguage(language);
            saveOrUpdateUser(user);
        }
    }
}
