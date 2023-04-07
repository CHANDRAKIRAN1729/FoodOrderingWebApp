import { Component, OnInit } from '@angular/core';
import { FoodappservicesService } from '../Services/foodappservices.service';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  admins_menu:any
  admins:any;
  error:any;
  id:any;
  constructor(private service:FoodappservicesService,private service2:ItemsServicesService) {
    this.id=localStorage.getItem("id");
   }

  ngOnInit(): void {
    this.service2.getAdmins().subscribe((result)=>{
      this.admins=result;
      //this.admins_menu=this.admins.menu;
      this.admins=this.admins.data;
      this.admins_menu=this.admins.menu;      
    },(err)=>{
      this.error=err;
      window.alert(this.error.message);
    })

  }
  isAdmin(p:any){
    if(p==this.id){
      return true;
    }
    return false;
  }

}
