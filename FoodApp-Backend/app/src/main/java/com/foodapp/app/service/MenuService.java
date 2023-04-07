package com.foodapp.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodapp.app.dao.BranchManagerDao;
import com.foodapp.app.dao.ItemDao;
import com.foodapp.app.dao.MenuDao;
import com.foodapp.app.dto.BranchManager;
import com.foodapp.app.dto.Item;
import com.foodapp.app.dto.Menu;
import com.foodapp.app.exception.BranchManagerException;
import com.foodapp.app.exception.IdNotFoundException;
import com.foodapp.app.util.ResponseStructure;

@Service
public class MenuService {
	
	@Autowired
    MenuDao dao;
	
	@Autowired
	BranchManagerDao branchManagerDao;
	
	@Autowired
	ItemDao itemDao;
    public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu,int id) {
        ResponseStructure<Menu> structure = new ResponseStructure<>();
        BranchManager branchManager=branchManagerDao.findBranchManagerById(id).get();
        if(branchManager.getMenu()!=null)
        	throw new BranchManagerException();
        else
        	menu.setBranchManager(branchManager);
        List<Item> items=menu.getItems();
        List<Item> result=new ArrayList<>();
        int quantity=0;
        for(Item i:items) {
        	quantity++;
        	result.add(itemDao.saveItem(i));
        }
        menu.setQuantity(quantity);
        Menu menu2=dao.saveMenu(menu);
        for(Item i:result) {
        	i.setMenu(menu2);
        	itemDao.saveItem(i);
        }
        structure.setMessage("Menu Saved Successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(menu2);
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id) {
        Optional<Menu> optional = dao.deleteMenu(id);
        ResponseStructure<Menu> structure = new ResponseStructure<>();
        if (optional == null) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("deleted Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Menu>> getMenuById(int id) {
        Optional<Menu> optional = dao.findMenuById(id);
        ResponseStructure<Menu> structure = new ResponseStructure<>();
        if (optional.isEmpty()) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("Menu Found");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<Menu>>> getAllMenus() {
        ResponseStructure<List<Menu>> structure = new ResponseStructure<>();
        List<Menu> list = dao.findAllMenus();
        if (list.isEmpty())
            throw new IdNotFoundException();
        else {
            structure.setMessage("fetched Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(list);
            return new ResponseEntity<ResponseStructure<List<Menu>>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu menu, int id) {
    	List<Item> items=menu.getItems();
    	List<Item> result=new ArrayList<>();
        int quantity=0;
        for(Item i:items) {
        	quantity++;
        	result.add(itemDao.saveItem(i));
        }
        Menu menu2=dao.findMenuById(id).get();
        menu2.setQuantity(menu2.getQuantity()+quantity);
        List<Item> res_Items=menu2.getItems();
        for(Item i:result) {
        	i.setMenu(menu2);
        	itemDao.saveItem(i);
        	res_Items.add(i);
        }
        menu2.setItems(res_Items);
        dao.saveMenu(menu2);
        ResponseStructure<Menu> structure = new ResponseStructure<>();
        if (menu2 != null) {
            structure.setMessage("Updated Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(menu);
            return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException();
        }
    }
}
