import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {HttpClientModule,HTTP_INTERCEPTORS} from '@angular/common/http';
import { GetitemsComponent } from './getitems/getitems.component';
import { RegistrationComponent } from './registration/registration.component';
import { GetMenuComponent } from './get-menu/get-menu.component';
import { FoodOrderComponent } from './food-order/food-order.component';
import { BranchManagerComponent } from './branch-manager/branch-manager.component';
import { StaffComponent } from './staff/staff.component';
import { UserComponent } from './user/user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { EditItemComponent } from './edit-item/edit-item.component';
import { EditManagerComponent } from './edit-manager/edit-manager.component';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { EditStaffComponent } from './edit-staff/edit-staff.component';
import { AdminComponent } from './admin/admin.component';
import { EditAdminComponent } from './edit-admin/edit-admin.component';
import { AddFoodOrderComponent } from './add-food-order/add-food-order.component';
import { EditMenuComponent } from './edit-menu/edit-menu.component';
import { EditFoodorderComponent } from './edit-foodorder/edit-foodorder.component'


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    GetitemsComponent,
    RegistrationComponent,
    GetMenuComponent,
    FoodOrderComponent,
    BranchManagerComponent,
    StaffComponent,
    UserComponent,
    EditUserComponent,
    EditItemComponent,
    EditManagerComponent,
    HomeComponent,
    MenuComponent,
    EditStaffComponent,
    AdminComponent,
    EditAdminComponent,
    AddFoodOrderComponent,
    EditMenuComponent,
    EditFoodorderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers:[],
  bootstrap: [AppComponent]
})
export class AppModule { }
