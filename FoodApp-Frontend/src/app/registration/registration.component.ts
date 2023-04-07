import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthServiceService } from '../Services/auth-service.service';
import { FoodappservicesService } from '../Services/foodappservices.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user:any;
  result:any;
  error:any;
  constructor(private service:FoodappservicesService,private router:Router,private auth:AuthServiceService) { }

  ngOnInit(): void {
  }

  regUser(form:NgForm){
    this.result=form.value;
    if(this.result.role==="admin"){
    this.service.reg(form.value).subscribe((res)=>{
      this.user=res;
      window.alert(this.user.message);
      this.router.navigate([("")]);
    },(err)=>{
      this.error=err;
      window.alert(this.error.error.message); 
      this.router.navigate([("")]);
    })}
    else if(this.result.role==="manager"){
      if(localStorage.getItem("role")==="admin"){
        this.service.regManager(this.result).subscribe((res)=>{
          this.user=res;
          window.alert(this.user.message)
          this.router.navigate([("")]);
        },(err)=>{
          this.error=err;
      window.alert(this.error.error.message); 
      this.router.navigate([("")]);
        })
        }
      else{
        window.alert("Admin not logged in to save the BranchManager");
        this.router.navigate([("")]);
      }
    }
    else{
      if(localStorage.getItem("role")==="manager"){
        this.service.regStaff(this.result).subscribe((res)=>{
          this.user=res;
          window.alert(this.user.message) 
          this.router.navigate([("")]);        
        },(err)=>{
          this.error=err;
      window.alert(this.error.error.message); 
      this.router.navigate([("")]);
        })
    }
    else{
      window.alert("BranchManager not logged in to save the Staff");
      this.router.navigate([("")]);
    }
  }

}

  isLoggedAdmin(){
    return this.auth.isLoggedAdmin();
  }

  isLoggedManager(){
    return this.auth.isLoggedManager();
  }

  isLogged(){
    return this.auth.isLogged();
  }
}
