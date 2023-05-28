package com.botbackendrest.service.userCityLinkService;

import com.botbackendrest.entity.UserCityLInk;

import java.util.List;

public interface UserCityLinkService {
    List<UserCityLInk> getAllLinks();
    UserCityLInk getLinkById(int id);
    void saveOrUpdateLink(UserCityLInk lInk);
    void deleteLink(int id);
}
