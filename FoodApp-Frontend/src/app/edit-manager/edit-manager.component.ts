import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-edit-manager',
  templateUrl: './edit-manager.component.html',
  styleUrls: ['./edit-manager.component.css']
})
export class EditManagerComponent implements OnInit {
  result:any;
  selectedManager:any;
  constructor(private service:ItemsServicesService,private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.service.getManager(id).subscribe((user)=>{
      this.result=user;
        this.selectedManager=this.result.data;
        console.log(this.result);
    })
  }

  editManager(form:NgForm){
    this.service.editManager(form.value,this.selectedManager.id).subscribe((data)=>{
      this.result=data;
      window.alert(this.result.message)
      this.router.navigate([("branchManagers")]);
    })
  }

}
