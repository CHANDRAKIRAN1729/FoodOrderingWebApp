import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthServiceService } from '../Services/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class ManagerStaffGuard implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean{
      if(this.auth.isLogged() && (this.auth.isLoggedManager() || this.auth.isLoggedStaff)){
        return true;
      }
      else{
        this.route.navigate([("home")])
        return false;
      }
  }
  constructor(private auth:AuthServiceService,private route:Router){}

}
