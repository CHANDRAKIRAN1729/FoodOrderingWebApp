import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FoodappservicesService {
  details:any;
  constructor(private http:HttpClient) { }

  login(data:any){
    return this.http.post("http://localhost:8080/login",data);
  }

  reg(data:any){
          console.log(data.role);
    return this.http.post(`http://localhost:8080/saveadmin`,data);
    }

  regManager(data:any){
    return this.http.post(`http://localhost:8080/savebranchManager/${localStorage.getItem("id")}`,data);  
  }

  regStaff(data:any){
    return this.http.post(`http://localhost:8080/savestaff/${localStorage.getItem("id")}`,data);
  }

  regUser(data:any){
    return this.http.post(`http://localhost:8080/saveuser`,data);
  }

  addItem(data:any){
    return this.http.post(`http://localhost:8080/saveitem`,data);
  }

  addMenu(data:any){
    return this.http.post(`http://localhost:8080/savemenu/${localStorage.getItem("id")}`,data);
  }

  createFoodOrder(data:any,id:any){
    return this.http.post(`http://localhost:8080/savefoodOrder/staff:${localStorage.getItem("id")}/user:${id}`,data);
  }

  sendBill(id:any){
    return this.http.post(`http://localhost:8080/mailfoodOrder/${id}`,null);
  }
}
