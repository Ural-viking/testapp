package com.example.testapp.controller;

import com.example.testapp.entity.Car;
import com.example.testapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/get")
    public List<Car> getAllCars(){
        return carService.findAll();
    }

    @PostMapping("/post")
    public Car postCar(@RequestBody Car car) {
        return carService.postCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
    }
}