import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  result:any;
  selectedUser:any;
  constructor(private service:ItemsServicesService,private router:Router,private route:ActivatedRoute) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.service.getUser(id).subscribe((user)=>{
      this.result=user;
        this.selectedUser=this.result;
        console.log(this.result);
      
    })
  }

  editUser(form:NgForm){
    this.service.editUser(form.value,this.selectedUser.data.id).subscribe((data)=>{
      console.log(data);
      
    })
  }

}
