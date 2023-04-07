package com.foodapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.app.dto.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {

}
