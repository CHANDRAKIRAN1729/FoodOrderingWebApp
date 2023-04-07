import { Component, OnInit } from '@angular/core';
import {FormArray,FormGroup,FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { FoodappservicesService } from '../Services/foodappservices.service';
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  userForm:any;
  userObj:any;
  result:any;
  error:any;
  constructor(private router:Router,private service:FoodappservicesService) {
   }

  ngOnInit(): void {
    this.userForm=new FormGroup({
      branch:new FormControl(""),
      items:new FormArray([
        new FormGroup({
          availability:new FormControl(""),
          name:new FormControl(""),
          description:new FormControl(""),
          price:new FormControl(""),
          quantity:new FormControl("")
        })
      ]),
    });
  }
  addItem(){
    const control=<FormArray>this.userForm.controls['items'];
    control.push(
      new FormGroup({
        availability:new FormControl(""),
        name:new FormControl(""),
        description:new FormControl(""),
        price:new FormControl(""),
        quantity:new FormControl("")
      })
    );
  }

  removeItem(index:any){
    const control=<FormArray>this.userForm.controls['items'];
    control.removeAt(index);
  }

  addMenu(){
    const emp=this.userForm.value;
    this.service.addMenu(emp).subscribe((result)=>{
      this.result=result
      window.alert(this.result.message); 
      this.router.navigate([("menus")])
    },(error)=>{
      this.error=error;
      window.alert(this.error.message);
    })
  }
}



