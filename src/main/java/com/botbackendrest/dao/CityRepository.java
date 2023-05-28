package com.botbackendrest.dao;

import com.botbackendrest.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CityRepository extends JpaRepository<City,Integer> {

}
