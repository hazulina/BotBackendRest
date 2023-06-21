package com.botbackendrest.controller;

import com.botbackendrest.entity.User;
import com.botbackendrest.service.userService.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{chatId}")
    public User getUserByChatId(@PathVariable int chatId) {
        return userService.getUserByChatId(chatId);
    }

    @GetMapping("/{chatId}/language")
    public ResponseEntity<?> getUserLanguageByChatId(@PathVariable int chatId) {
        try {
            String userLanguage = userService.getUserByChatId(chatId).getUserLanguage();
            return ResponseEntity.ok(userLanguage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addNewUser(@RequestBody int chatId) {
        try {
            userService.saveOrUpdateUser(new User(chatId));
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Already exist");
        }
    }

    @PutMapping()
    public void updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @PutMapping("/language")
    public ResponseEntity<?> updateUserLanguage(@RequestBody User user) {
        try {
            userService.updateUserLanguage(user.getChatId(), user.getUserLanguage());
            return ResponseEntity.ok("Language is updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ooops! can't update language");
        }
    }

    @DeleteMapping("/{chatId}")
    public void deleteUser(@PathVariable int chatId) {
        userService.deleteUserByChatId(chatId);
    }
}
