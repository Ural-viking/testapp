package com.example.testapp.service;

import com.example.testapp.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findAll(); // вывести список всех машин

    Car postCar(Car car); // добавление машины

    void deleteCarById(Long id); // удаление машины по ид

}
