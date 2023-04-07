import { Component, OnInit } from '@angular/core';
import { FoodappservicesService } from '../Services/foodappservices.service';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-branch-manager',
  templateUrl: './branch-manager.component.html',
  styleUrls: ['./branch-manager.component.css']
})
export class BranchManagerComponent implements OnInit {
  managers_menu:any
  managers:any;
  error:any;
  id:any;
  constructor(private service:FoodappservicesService,private service2:ItemsServicesService) {
    this.id=localStorage.getItem("id");
   }

  ngOnInit(): void {
    this.service2.getManagers(this.id).subscribe((result)=>{
      this.managers=result;
      //this.managers_menu=this.managers.menu;
      this.managers=this.managers.data;
      this.managers_menu=this.managers.menu;      
    },(err)=>{
      this.error=err;
      window.alert(this.error.message);
    })

  }

}
