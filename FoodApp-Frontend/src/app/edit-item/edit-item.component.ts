import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {
  result:any;
  selectedItem:any;
  constructor(private service:ItemsServicesService,private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.service.getItem(id).subscribe((user)=>{
      this.result=user;
        this.selectedItem=this.result.data;
        console.log(this.result);
    })
  }

  editItem(form:NgForm){
    this.service.editItem(form.value,this.selectedItem.id).subscribe((data)=>{
      this.result=data;
      window.alert(this.result.message)
      this.router.navigate([("items")]);
    })
  }

}
