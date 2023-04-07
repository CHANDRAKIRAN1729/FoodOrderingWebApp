import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ItemsServicesService {

  constructor(private http:HttpClient) { }

  getData(){
    return this.http.get("http://localhost:8080/getitems");
  }

  getItem(id:any){
    return this.http.get(`http://localhost:8080/getitem/${id}`);
  }

  editItem(item:any,id:any){
    return this.http.put(`http://localhost:8080/updateitem/${id}`,item);
  }

  getMenu(){
    return this.http.get("http://localhost:8080/getmenus");
  }

  getFoodOrder(){
    return this.http.get("http://localhost:8080/getfoodOrders");
  }
  
  getUsers(){
    return this.http.get("http://localhost:8080/getusers");
  }

  getUser(id:any){
    return this.http.get(`http://localhost:8080/getuser/${id}`);
  }

  editUser(user:any,id:any){
    return this.http.put(`http://localhost:8080/updateuser/${id}`,user);
  }

  getManagers(id:any){
    return this.http.get(`http://localhost:8080/getmanagerbyid/${id}`);
  }

  editManager(manager:any,id:any){
    return this.http.put(`http://localhost:8080/updatebranchManager/${id}`,manager);
  }

  getManager(id:any){
    return this.http.get(`http://localhost:8080/getbranchManager/${id}`);
  }

  getStaff(id:any){
    return this.http.get(`http://localhost:8080/getstaffbyid/${id}`);
  }

  getStaffById(id:any){
    return this.http.get(`http://localhost:8080/getstaff/${id}`);
  }

  editStaff(staff:any,id:any){
    return this.http.put(`http://localhost:8080/updatestaff/${id}`,staff);
  }

  getAdmins(){
    return this.http.get(`http://localhost:8080/getadmins`);
  }
  
  getAdmin(id:any){
    return this.http.get(`http://localhost:8080/getadmin/${id}`);
  }

  editAdmin(admin:any,id:any){
    return this.http.put(`http://localhost:8080/updateadmin/${id}`,admin);
  }

  foodOrderStatus(id:any){
    return this.http.put(`http://localhost:8080/foodOrderStatus/${id}`,null);
  }

  getMenuByStaff(id:any){
    return this.http.get(`http://localhost:8080/getMenubyStaffid/${id}`)
  }

  getMenuById(id:any){
    return this.http.get(`http://localhost:8080/getmenu/${id}`)
  }

  getMenubyBranchManager(id:any){
    return this.http.get(`http://localhost:8080/getmenubymanager/${id}`)
  }

  updateMenu(id:any,data:any){
    return this.http.put(`http://localhost:8080/updatemenu/${id}`,data)
  }

  getFoodOrderById(id:any){
    return this.http.get(`http://localhost:8080/getfoodOrder/${id}`)
  }
}
