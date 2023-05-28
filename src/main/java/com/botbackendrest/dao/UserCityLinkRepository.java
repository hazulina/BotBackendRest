package com.botbackendrest.dao;

import com.botbackendrest.entity.UserCityLInk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserCityLinkRepository extends JpaRepository<UserCityLInk, Integer> {
}
