package com.botbackendrest.controller;

import com.botbackendrest.entity.UserCityLInk;
import com.botbackendrest.service.userCityLinkService.UserCityLinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserCityLinksController {
    private final UserCityLinkService linkService;

    @GetMapping("/links")
    public List<UserCityLInk> getAllLinks(){
       return linkService.getAllLinks();
    }
    @GetMapping("/links/{id}")
    public UserCityLInk getLinkById(@PathVariable int id){
        return linkService.getLinkById(id);
    }
    @PostMapping("/links")
    public void addNewLink(@RequestBody UserCityLInk link){
        linkService.saveOrUpdateLink(link);
    }
    @PutMapping("/links")
    public void updateLink(@RequestBody UserCityLInk link){
        linkService.saveOrUpdateLink(link);
    }
    @DeleteMapping("/links/{id}")
    public void deleteLink(@PathVariable int id){
        linkService.deleteLink(id);
    }
}
