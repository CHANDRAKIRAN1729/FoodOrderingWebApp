import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-edit-menu',
  templateUrl: './edit-menu.component.html',
  styleUrls: ['./edit-menu.component.css']
})
export class EditMenuComponent implements OnInit {
  result:any;
  selectedUser:any;
  items:any;
  menu_id:any;
  userForm:any;
  userObj:any;
  error:any;
  constructor(private service:ItemsServicesService,private router:Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.service.getMenubyBranchManager(id).subscribe((user)=>{
      this.result=user;
      this.result=this.result.data;
      this.menu_id=this.result.id;
    })

    this.userForm=new FormGroup({
      items:new FormArray([
        new FormGroup({
          availability:new FormControl("",Validators.required),
          name:new FormControl("",Validators.required),
          description:new FormControl("",Validators.required),
          price:new FormControl("",Validators.required),
          quantity:new FormControl("",Validators.required)
        })
      ]),
    });
  }

  addItem(){
    const control=<FormArray>this.userForm.controls['items'];
    control.push(
      new FormGroup({
        availability:new FormControl("",Validators.required),
        name:new FormControl("",Validators.required),
        description:new FormControl("",Validators.required),
        price:new FormControl("",Validators.required),
        quantity:new FormControl("",Validators.required)
      })
    );
  }

  removeItem(index:any){
    const control=<FormArray>this.userForm.controls['items'];
    control.removeAt(index);
  }

  updateMenu(){
      this.service.updateMenu(this.menu_id,this.userForm.value).subscribe((res)=>{
        this.result=res;
        window.alert(this.result.message);
      })
  }
}
