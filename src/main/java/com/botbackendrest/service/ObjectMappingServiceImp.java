package com.botbackendrest.service;

import com.botbackendrest.entity.City;
import com.botbackendrest.entity.Structures;
import com.botbackendrest.entity.User;
import com.botbackendrest.entity.UserCityLInk;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ObjectMappingServiceImp implements MappingVisitor<String,Structures> {
    private final ObjectMapper objectMapper;



    @SneakyThrows
    @Override
    public Structures map(String input) {
        JsonNode node = objectMapper.readTree(input);
        return new User(node.asInt());
    }

}
