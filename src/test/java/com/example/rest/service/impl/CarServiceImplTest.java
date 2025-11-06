package com.example.rest.service.impl;

import com.example.rest.model.Car;
import com.example.rest.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    //The class whose methods we are testing
    @InjectMocks
    CarServiceImpl carServiceImpl;

    @Test
    void findAllCars() {
        Car car1 = new Car(1, "Toyota", "Corolla", null);
        Car car2 = new Car(2, "Honda", "Civic", null);

        when(carRepository.findAll()).thenReturn(List.of(car1, car2));

        List<Car> allCars = carServiceImpl.findAllCars();

        verify(carRepository, times(1)).findAll();

        assertEquals(2, allCars.size());
        assertEquals("Toyota", allCars.get(0).getMake());
        assertEquals("Honda", allCars.get(1).getMake());
    }

    @Test
    void findCarByUserId() {
    }
}