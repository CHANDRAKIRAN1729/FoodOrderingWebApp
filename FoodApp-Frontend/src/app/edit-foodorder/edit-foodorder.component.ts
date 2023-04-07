import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ItemsServicesService } from '../Services/items-services.service';

@Component({
  selector: 'app-edit-foodorder',
  templateUrl: './edit-foodorder.component.html',
  styleUrls: ['./edit-foodorder.component.css']
})
export class EditFoodorderComponent implements OnInit {
  	result:any;
    gst:any;
    status:any;
  constructor(private service:ItemsServicesService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    let id=this.route.snapshot.params['id'];
    this.service.getFoodOrderById(id).subscribe((res)=>{
      this.result=res;
      this.result=this.result.data;
      this.gst=this.result.billingAmount *(18/100)
      if(this.result.status==true){
        this.status="Completed";
      }
      else{
        this.status="Preparing"
      }
      console.log(this.result);

    })
  }

}
