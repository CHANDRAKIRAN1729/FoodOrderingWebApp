import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor() { }

  isLoggedAdmin(){
      if(localStorage.getItem("role")==="admin"){
        return true;
      }
      return false;
  }

  isLoggedManager(){
    if(localStorage.getItem("role")==="manager"){
      return true;
    }
    return false;
}

isLoggedStaff(){
  if(localStorage.getItem("role")==="staff"){
    return true;
  }
  return false;
}

    isLogged(){
      let myrole=localStorage.getItem("role");
      if(myrole==="admin" || myrole==="manager" || myrole==="staff"){
        return true;
      }
      return false;
    }

}
