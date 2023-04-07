import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { FoodappservicesService } from '../Services/foodappservices.service';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-getitems',
  templateUrl: './getitems.component.html',
  styleUrls: ['./getitems.component.css']
})
export class GetitemsComponent implements OnInit {
  items:any;
  id:any;
  constructor(private service:ItemsServicesService,private register:FoodappservicesService,private router:Router) { }

  ngOnInit(): void {
    this.service.getData().subscribe((data)=>
    {
      this.items=data;
      this.items=this.items.data;
    })
  }

  getById(){
    this.service.getItem(this.id).subscribe((data)=>{
      this.items=data;
      console.log(this.items);
            
    })
  }

  addItem(form:NgForm){
    this.register.addItem(form.value).subscribe((data)=>{
      this.items=data;
      console.log(this.items);
      this.router.navigate([""]);
    })
  }

}