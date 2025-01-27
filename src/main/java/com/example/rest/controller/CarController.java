package com.example.rest.controller;

import com.example.rest.model.Car;
import com.example.rest.service.CarService;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    //Related to Actuator
    private final DistributionSummary summary;
    //Related to Actuator. Count the number of calls to getAllCars();
    private Double count = 0.0;

    private final CarService carService;

    /**
     * We Create custom metric in Spring Actuator.
     * We call this metric using: /actuator/metrics/cars.summary endpoint
     *
     * @param meter      It comes from spring actuator
     * @param carService Injected service
     */
    public CarController(MeterRegistry meter, CarService carService) {
        this.carService = carService;
        summary = DistributionSummary.builder("cars.all.count")  //Build a custom metric (cars.all.count)  and register it
                .baseUnit("times")
                .register(meter);

    }

    @GetMapping("/all")
    private ResponseEntity<List<Car>> getAllCars() {
        summary.record(++count);

        //This part of code is for learning purpose!!!
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //This part of code is for learning purpose!!!

        return ResponseEntity
                .ok()
                .body(carService.findAllCars());
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<Car> getCarByUserId(@PathVariable("userId") Integer userId) {
        Car car = carService.findCarByUserId(userId);

        if (car != null) {
            return ResponseEntity
                    .ok()
                    .body(car);
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
