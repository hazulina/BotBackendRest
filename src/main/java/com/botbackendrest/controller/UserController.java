package com.botbackendrest.controller;

import com.botbackendrest.entity.Structures;
import com.botbackendrest.entity.User;
import com.botbackendrest.service.MappingVisitor;
import com.botbackendrest.service.userService.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{chatId}")
    public User getUserByChatId(@PathVariable int chatId) {
        return userService.getUserByChatId(chatId);
    }

    @GetMapping("/users/{chatId}/language")
    public ResponseEntity<?> getUserLangByChatId(@PathVariable String chatId) {
        try {
            return new ResponseEntity<>(
                    userService.getUserByChatId(Integer.parseInt(chatId)).getUserLanguage(),
                    HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "no such user",
                    HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> addNewUser(@RequestBody String body) {
        try {
            userService.saveOrUpdateUser(new User(Integer.parseInt(body)));
            return new ResponseEntity<>("OK", HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>("Already exist", HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @PutMapping("/users/language")
    public ResponseEntity<?> updateUserLanguage(@RequestBody String request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(request);
            String chatId = node.path("chatId").asText();
            String language = node.path("language").asText();
            userService.updateUserLanguage(Integer.parseInt(chatId), language);
            return new ResponseEntity<>("Language is updated", HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>("Ooops! can't update language", HttpStatusCode.valueOf(400));
        }
    }

    @DeleteMapping("/users/{chatId}")
    public void deleteUser(@PathVariable int chatId) {
        userService.deleteUserByChatId(chatId);
    }
}
