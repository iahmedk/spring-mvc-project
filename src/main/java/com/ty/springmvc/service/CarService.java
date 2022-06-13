package com.ty.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ty.springmvc.dao.CarDao;
import com.ty.springmvc.dto.Car;

@Component
public class CarService {
	@Autowired
	private CarDao carDao;

	public Car saveCar(Car car) {
		return carDao.saveCar(car);
	}

	public List<Car> getAllCars() {
		return carDao.getAllCars();
	}

	public Car getCarById(int id) {
		return carDao.getCarById(id);

	}

	public void removeCar(String[] id) {
		carDao.removeCar(id);
	}

	public Car updateCar(Car car) {
		return carDao.updateCar(car);
	}
}
