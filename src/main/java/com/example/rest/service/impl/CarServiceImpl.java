package com.example.rest.service.impl;

import com.example.rest.model.Car;
import com.example.rest.repository.CarRepository;
import com.example.rest.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car findCarByUserId(Integer userId) {
        return carRepository.findByUserId(userId).orElse(null);
    }
}
