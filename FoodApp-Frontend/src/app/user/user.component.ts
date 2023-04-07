import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { FoodappservicesService } from '../Services/foodappservices.service';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  result:any;
  id:any;
  error:any;
  constructor(private service:ItemsServicesService,private register:FoodappservicesService) { }

  ngOnInit(): void {
    this.service.getUsers().subscribe((data)=>
    {
      this.result=data;
      this.result=this.result.data;
    })
  }

  addUser(form:NgForm){
    this.register.regUser(form.value).subscribe((data)=>{
    },(err)=>{
      this.error=err;
      window.alert(this.error.error.message);
    })
  }

}
