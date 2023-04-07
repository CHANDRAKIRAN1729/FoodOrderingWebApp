import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { FoodappservicesService } from './Services/foodappservices.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FoodApp';
  userDetails:any;
  userName:any;
  userEmail:any;
  role:any;
  id:any;
  link:any;
  constructor(private service:FoodappservicesService,private router:Router){}
  logout(){
    if(localStorage.getItem("role")==="admin"){
      localStorage.removeItem("role");
      localStorage.removeItem("id");
      localStorage.removeItem("name");
      localStorage.removeItem("email");
      window.alert("Admin Logged out Successfully");
      this.router.navigate([("home")])
    }
    else if(localStorage.getItem("role")==="manager"){
      localStorage.removeItem("role");
      localStorage.removeItem("id");
      localStorage.removeItem("name");
      localStorage.removeItem("email");  
       window.alert("Branch Manager Logged out Successfully");
       this.router.navigate([("home")])
    }
    else if(localStorage.getItem("role")==="staff"){
      localStorage.removeItem("role");
      localStorage.removeItem("id");
      localStorage.removeItem("name");
      localStorage.removeItem("email");
      window.alert("Staff Logged out Successfully");
      this.router.navigate([("home")])
    }
  }

  isLoggedIn(){
   var myrole=localStorage.getItem("role");
    if(myrole==="admin" || myrole==="manager" || myrole==="staff" ){
      return false;
    }
    return true;
  }

  isAdmin(){
    var myrole=localStorage.getItem("role")
    if(myrole==="admin"){
      return true;
    }
    return false;
  }

  isManager(){
    var myrole=localStorage.getItem("role")
    if(myrole==="manager"){
      return true;
    }
    return false;
  }

  isStaff(){
    var myrole=localStorage.getItem("role")
    if(myrole==="staff"){
      return true;
    }
    return false;
  }

  getDetails(){
   this.role =localStorage.getItem("role");
    this.userName=localStorage.getItem("name")
  this.userEmail=localStorage.getItem("email");
  this.id=localStorage.getItem("id");
  this.link=`/edit-${this.role}`;
  }
  
}
