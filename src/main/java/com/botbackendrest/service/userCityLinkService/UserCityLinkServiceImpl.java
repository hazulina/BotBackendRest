package com.botbackendrest.service.userCityLinkService;

import com.botbackendrest.dao.UserCityLinkRepository;
import com.botbackendrest.entity.UserCityLInk;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCityLinkServiceImpl implements UserCityLinkService {
    private final UserCityLinkRepository linkRepository;


    @Override
    public List<UserCityLInk> getAllLinks() {
        return linkRepository.findAll();
    }

    @Override
    public UserCityLInk getLinkById(int id) {
        Optional<UserCityLInk> optional = linkRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void saveOrUpdateLink(UserCityLInk lInk) {
        linkRepository.save(lInk);
    }

    @Override
    public void deleteLink(int id) {
        linkRepository.deleteById(id);
    }
}
