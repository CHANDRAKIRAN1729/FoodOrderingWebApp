import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-edit-admin',
  templateUrl: './edit-admin.component.html',
  styleUrls: ['./edit-admin.component.css']
})
export class EditAdminComponent implements OnInit {
  result:any;
  selectedAdmin:any;
  constructor(private service:ItemsServicesService,private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.service.getAdmin(id).subscribe((user)=>{
      this.result=user;
        this.selectedAdmin=this.result.data;
        console.log(this.result);
    })
  }

  editAdmin(form:NgForm){
    this.service.editAdmin(form.value,this.selectedAdmin.id).subscribe((data)=>{
      this.result=data;
      window.alert(this.result.message)
      this.router.navigate([("admin")]);
    })
  }

}
