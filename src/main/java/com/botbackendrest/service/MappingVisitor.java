package com.botbackendrest.service;

public interface MappingVisitor<String,R> {

    R map (String input);

}
