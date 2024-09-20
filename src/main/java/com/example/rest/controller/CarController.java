package com.example.rest.controller;

import com.example.rest.model.Car;
import com.example.rest.repository.CarRepository;
import com.example.rest.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    private ResponseEntity<List<Car>> getAllCars(){
        return ResponseEntity
                .ok()
                .body(carService.findAllCars());
    }
}
