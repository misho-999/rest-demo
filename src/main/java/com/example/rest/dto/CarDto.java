package com.example.rest.dto;

import lombok.Data;

@Data
public class CarDto {
    private String make;
    private String model;
    private UserDto userDto;
}
