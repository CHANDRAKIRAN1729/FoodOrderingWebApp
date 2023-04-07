import { Component, OnInit } from '@angular/core';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {
staff:any;
error:any;
id:any;
  constructor(private service:ItemsServicesService) { 
    this.id=localStorage.getItem("id");
  }

  
  ngOnInit(): void {
    this.service.getStaff(this.id).subscribe((result)=>{
      this.staff=result;
      //this.managers_menu=this.managers.menu;
      this.staff=this.staff.data;
    },(err)=>{
      this.error=err;
      window.alert(this.error.message);
    })

  }
}
