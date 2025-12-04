package com.example.rest.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String username;
    private String email;
    private Set<CarDto> carDtoSet;
}
