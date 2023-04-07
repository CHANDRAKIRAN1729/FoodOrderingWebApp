package com.foodapp.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.app.dao.FoodOrderDao;
import com.foodapp.app.dto.FoodOrder;
import com.foodapp.app.dto.Item;
import com.foodapp.app.email.EmailDetails;
import com.foodapp.app.email.EmailServiceImpl;
import com.foodapp.app.service.FoodOrderService;
import com.foodapp.app.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodOrderController {
	
	@Autowired
	FoodOrderDao foodOrderDao;
	@Autowired
	FoodOrderService foodOrderService;
	@Autowired
	EmailServiceImpl emailServiceImpl;
	@PostMapping("/savefoodOrder/staff:{id}/user:{id2}")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(@PathVariable int id,@PathVariable int id2,@RequestBody List<Item> items) {
		return foodOrderService.saveFoodOrder(items,id,id2);
	}
	
	@GetMapping("/getfoodOrder/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrder(@PathVariable int id) {
		return foodOrderService.getFoodOrderById(id);
	}
	
	@GetMapping("/getfoodOrders")
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getFoodOrders(){
		return foodOrderService.getAllFoodOrders();
	}
	
	@PutMapping("/updatefoodOrder/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(@RequestBody FoodOrder foodOrder,@PathVariable int id,@RequestParam List<Integer> ids) {
		return foodOrderService.updateFoodOrder(foodOrder, id,ids);
	}
	
	@DeleteMapping("/deletefoodOrder/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(@PathVariable  int id) {
		return foodOrderService.deleteFoodOrder(id);
	}
	
	@PostMapping("/mailfoodOrder/{id}")
	public ResponseEntity<ResponseStructure<EmailDetails>> sendMail(@PathVariable int id)
    {
		return emailServiceImpl.sendSimpleMail(id);
    }
	@PutMapping("/foodOrder/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> addItemToOrder(@PathVariable int id,@RequestParam List<Integer> ids) {
		return foodOrderService.addItemToOrder(id,ids);
	}
	@PutMapping("foodOrderStatus/{id}")
	public ResponseEntity<ResponseStructure<FoodOrder>> foodOrderStatus(@PathVariable int id){
		return foodOrderService.foodOrderStatus(id);
	}

}
