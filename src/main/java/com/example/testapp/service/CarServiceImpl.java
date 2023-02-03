package com.example.testapp.service;

import com.example.testapp.entity.Car;
import com.example.testapp.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ActionService actionService;

    @Override
    public List<Car> findAll() {
        List <Car> cars = carRepository.findAll();
        log.info("Произведен вывод всех зарегистрированных машин, найдено: {}", cars.stream().count());
        actionService.saveLogOfAction("Выведен список всех машин");
        return cars;
    }

    @Override
    @Transactional
    public Car postCar(Car car1) {
        if (carRepository.findCarByRegisterSign(car1.getRegisterSign()).isEmpty()) {
            Car car = carRepository.save(car1);
            log.info("Успешное добавление машины registerSign = {}", car.getRegisterSign());
            actionService.saveLogOfAction("Добавлена новая машина " + car.getRegisterSign().toString());
            return car;
        } else {
            log.warn("Не получилось добавить машину с номером registerSign ={}", car1.getRegisterSign());
            actionService.saveLogOfAction("Неудачная попытка добавления нового объекта");
            throw new RuntimeException("Машина с этим номером - " + car1.getRegisterSign() + "уже есть в каталоге");
        }
    }

    @Override
    @Transactional
    public void deleteCarById(Long id) throws RuntimeException {
        try{
            Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("Ошибка при удалении машины"));
            carRepository.delete(car);
            log.info("Успешное удаление машины id = {}", car.getId());
            actionService.saveLogOfAction("Удалена машина" + car.getRegisterSign().toString());
        }
        catch(RuntimeException e){
            log.warn("Не получилось удалить указанную запись");
            actionService.saveLogOfAction("Ошибка при попытке удалить объект");
        }
    }

}