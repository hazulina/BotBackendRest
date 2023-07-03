package com.botbackendrest.controller;

import com.botbackendrest.entity.UserCityLInk;
import com.botbackendrest.service.userCityLinkService.UserCityLinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
@AllArgsConstructor
public class UserCityLinksController {
    private final UserCityLinkService linkService;

    @GetMapping()
    public List<UserCityLInk> getAllLinks() {
        return linkService.getAllLinks();
    }

    @GetMapping("/{id}")
    public UserCityLInk getLinkById(@PathVariable int id) {
        return linkService.getLinkById(id);
    }

    @PostMapping()
    public void addNewLink(@RequestBody UserCityLInk link) {
        linkService.saveOrUpdateLink(link);
    }

    @PutMapping()
    public void updateLink(@RequestBody UserCityLInk link) {
        linkService.saveOrUpdateLink(link);
    }

    @DeleteMapping("/{id}")
    public void deleteLink(@PathVariable int id) {
        linkService.deleteLink(id);
    }
}
