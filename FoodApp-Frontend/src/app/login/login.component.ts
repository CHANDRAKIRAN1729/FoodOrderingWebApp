import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { FoodappservicesService } from '../Services/foodappservices.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 user:any;
 error:any;
 username:any;
 usermail:any;
 userDetails:any=[];
  constructor(private service:FoodappservicesService,private router:Router) { }

  ngOnInit(): void {
  }

  loginUser(form:NgForm){
    this.service.login(form.value).subscribe((res)=>{
      this.user=res;
      window.alert(this.user.message)
      if(res){        
        localStorage.setItem("id",this.user.data.id);
        localStorage.setItem("role",this.user.data.role);
        localStorage.setItem("name",this.user.data.name);
        localStorage.setItem("email",this.user.data.email);
        
      }
      this.router.navigate([("home")]);
    },(err)=>{
      this.error=err;      
      window.alert(this.error.error.message);
    })
  }

  

}
