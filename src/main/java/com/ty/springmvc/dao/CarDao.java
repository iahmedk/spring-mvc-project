package com.ty.springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ty.springmvc.dto.Car;

@Component
public class CarDao {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public Car saveCar(Car car) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(car);
		entityTransaction.commit();
		return car;
	}

	public List<Car> getAllCars() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("Select c from Car c");
		return query.getResultList();
	}

	public Car getCarById(int id) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("Select c from Car c where c.id = ?1");
		query.setParameter(1, id);

		List<Car> cars = query.getResultList();

		if (cars.size() > 0) {
			return cars.get(0);
		} else {
			return null;
		}
	}

	public void removeCar(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Query query = entityManager.createQuery("DELETE from Car c where c.id = ?1");
		query.setParameter(1, id);

		entityTransaction.begin();
		query.executeUpdate();
		entityTransaction.commit();
	}

	public Car updateCar(Car car) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Car carFromDB = getCarById(car.getId());
		carFromDB.setColor(car.getColor());
		carFromDB.setMaker(car.getMaker());
		carFromDB.setModel(car.getModel());
		carFromDB.setPrice(car.getPrice());
		carFromDB.setYom(car.getYom());

		entityTransaction.begin();
		entityManager.merge(carFromDB);
		entityTransaction.commit();
		return carFromDB;

	}
}
