import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFoodOrderComponent } from './add-food-order/add-food-order.component';
import { AdminComponent } from './admin/admin.component';
import { BranchManagerComponent } from './branch-manager/branch-manager.component';
import { EditAdminComponent } from './edit-admin/edit-admin.component';
import { EditFoodorderComponent } from './edit-foodorder/edit-foodorder.component';
import { EditItemComponent } from './edit-item/edit-item.component';
import { EditManagerComponent } from './edit-manager/edit-manager.component';
import { EditMenuComponent } from './edit-menu/edit-menu.component';
import { EditStaffComponent } from './edit-staff/edit-staff.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { FoodOrderComponent } from './food-order/food-order.component';
import { GetMenuComponent } from './get-menu/get-menu.component';
import { GetitemsComponent } from './getitems/getitems.component';
import { AdminManagerGuard } from './Guards/admin-manager.guard';
import { AdminGuard } from './Guards/admin.guard';
import { LoginGuard } from './Guards/login.guard';
import { ManagerStaffGuard } from './Guards/manager-staff.guard';
import { ManagerGuard } from './Guards/manager.guard';
import { StaffGuard } from './Guards/staff.guard';
import { UserGuard } from './Guards/user.guard';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { RegistrationComponent } from './registration/registration.component';
import { StaffComponent } from './staff/staff.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {path:"login",component:LoginComponent,canActivate:[LoginGuard]},
  {path:"registration",component:RegistrationComponent},
  {path:"items",component:GetitemsComponent,canActivate:[UserGuard]},
  {path:"menus",component:GetMenuComponent,canActivate:[UserGuard]},
  {path:"foodOrders",component:FoodOrderComponent,canActivate:[ManagerStaffGuard]},
  {path:"users",component:UserComponent,canActivate:[StaffGuard]},
  {path:"edit-user/:id",component:EditUserComponent,canActivate:[StaffGuard]},
  {path:"branchManagers",component:BranchManagerComponent,canActivate:[AdminGuard]},
  {path:"edit-manager/:id",component:EditManagerComponent,canActivate:[AdminGuard]},
  {path:"staff",component:StaffComponent,canActivate:[ManagerGuard]},
  {path:"home",component:HomeComponent},
  {path:"edit-item/:id",component:EditItemComponent,canActivate:[ManagerStaffGuard]},
  {path:"add-menu",component:MenuComponent,canActivate:[ManagerStaffGuard]},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path:"edit-staff/:id",component:EditStaffComponent,canActivate:[ManagerGuard]},
  {path:"admin",component:AdminComponent,canActivate:[AdminGuard]},
  {path:"edit-admin/:id",component:EditAdminComponent,canActivate:[AdminGuard]},
  {path:"create-foodorder",component:AddFoodOrderComponent,canActivate:[StaffGuard]},
  {path:"editmenu/:id",component:EditMenuComponent},
  {path:"invoice/:id",component:EditFoodorderComponent,canActivate:[StaffGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
