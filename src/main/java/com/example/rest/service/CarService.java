package com.example.rest.service;

import com.example.rest.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarService {
    List<Car> findAllCars();
}
