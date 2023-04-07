import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';
@Component({
  selector: 'app-food-order',
  templateUrl: './food-order.component.html',
  styleUrls: ['./food-order.component.css']
})
export class FoodOrderComponent implements OnInit {
  items:any;
  result:any;
  constructor(private service:ItemsServicesService,private route:Router) { }

  ngOnInit(): void {
    this.service.getFoodOrder().subscribe((data)=>
    {
      this.items=data;
      this.items=this.items.data;
    })
  }

  foodOrderStatus(id:any){
      this.service.foodOrderStatus(id).subscribe((res)=>{
        this.result=res;
        window.alert(this.result.message);
        this.route.navigate([("foodOrders")]);
      })    
  }

}