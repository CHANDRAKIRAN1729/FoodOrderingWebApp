import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-edit-staff',
  templateUrl: './edit-staff.component.html',
  styleUrls: ['./edit-staff.component.css']
})
export class EditStaffComponent implements OnInit {
  result:any;
  selectedStaff:any;
  constructor(private service:ItemsServicesService,private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.service.getStaffById(id).subscribe((user)=>{
      this.result=user;
        this.selectedStaff=this.result.data;
        console.log(this.result);
    })
  }

  editStaff(form:NgForm){
    this.service.editStaff(form.value,this.selectedStaff.id).subscribe((data)=>{
      this.result=data;
      window.alert(this.result.message)
      this.router.navigate([("staff")]);
    })
  }

}

