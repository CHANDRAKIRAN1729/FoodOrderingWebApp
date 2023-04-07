package com.foodapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodapp.app.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
