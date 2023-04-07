import { Component, OnInit } from '@angular/core';
import { ItemsServicesService } from '../Services/items-services.service';
import { NgForm,FormArray,FormGroup,FormControl, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { FoodappservicesService } from '../Services/foodappservices.service';
@Component({
  selector: 'app-add-food-order',
  templateUrl: './add-food-order.component.html',
  styleUrls: ['./add-food-order.component.css']
})
export class AddFoodOrderComponent implements OnInit {
  items:any;
  userForm:any;
  idForm:any;
  selectedItems:any=[];
  users:any;
  id:any=0;
  result:any;
  error:any;
  send_items:any;
  bills:any;
  menu_id:any;
  constructor(private service:ItemsServicesService,private route:Router,private save:FoodappservicesService) { }

  ngOnInit(): void {
    this.service.getMenuByStaff(localStorage.getItem("id")).subscribe((menus)=>{
      this.menu_id=menus;
      this.menu_id=this.menu_id.data.id;
      this.service.getMenuById(this.menu_id).subscribe((res)=>{
        this.items=res;
        this.items=this.items.data.items;
        for(let i=0;i<this.items.length;i++){
          if(this.items[i].quantity>5 && this.items[i].availability==true){
            this.selectedItems.push(this.items[i]);
          }
        }
        this.items=this.selectedItems;
      })    
    })
    this.userForm=new FormGroup({
      items:new FormArray([
        new FormGroup({
          id:new FormControl("",Validators.required),
          quantity:new FormControl("",Validators.required)
        })
      ]),
    });

    this.idForm=new FormGroup({
      user_id:new FormControl("",Validators.required)
    });


    this.service.getUsers().subscribe((res)=>{
        this.users=res;
        this.users=this.users.data;
    })
  }

  addItem(){
    const control=<FormArray>this.userForm.controls['items'];
    control.push(
      new FormGroup({
        id:new FormControl("",Validators.required),
        quantity:new FormControl("",Validators.required)
      })
    );
  }

  removeItem(index:any){
    const control=<FormArray>this.userForm.controls['items'];
    control.removeAt(index);
  }

  createFoodOrder(){
    console.log(this.userForm.value);
    this.id=this.idForm.value.user_id;
    this.send_items=this.userForm.value;
    this.send_items=this.send_items.items;
    console.log(this.id);
    console.log(this.idForm.value);
    
    this.save.createFoodOrder(this.send_items,this.id).subscribe((res)=>{
      this.result=res;
      window.alert(this.result.message);
      this.result=this.result.data;
      var id=this.result.id;
      this.save.sendBill(id).subscribe((bill)=>{
        this.bills=bill;
        this.route.navigate([(`/invoice`),(id)])
        window.alert(this.bills.message)

      })
      this.route.navigate([("foodOrders")])
    },(err)=>{
      this.error=err;
      window.alert(this.error.message);
      this.route.navigate([("create-foodorder")]);
    })
    
  }
}
