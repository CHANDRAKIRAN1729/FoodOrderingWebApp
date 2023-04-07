import { Component, OnInit } from '@angular/core';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-get-menu',
  templateUrl: './get-menu.component.html',
  styleUrls: ['./get-menu.component.css']
})
export class GetMenuComponent implements OnInit {
  items:any;
  id:any;
  constructor(private service:ItemsServicesService) {
    this.id=localStorage.getItem("id");
   }

  ngOnInit(): void {
    this.service.getMenu().subscribe((data)=>
    {
      this.items=data;
      this.items=this.items.data;
      
    })
  }

  isManager(){
    if(localStorage.getItem("role")==="manager")
    {
      return true;
    }
    return false;
  }

}
