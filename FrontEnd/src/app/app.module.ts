import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';                     /* trzeba to zaimplentowac zeby dziala routes*/
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {PageNotFoundComponent} from './components/not-found/not-found.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './core/auth.service';
import {Interceptor} from './core/inteceptor';
import {AuthStorage} from './core/auth-storage.service';
import { AddUserComponent } from './components/add-user/add-user.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { FooterComponent } from './components/footer/footer.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { CustomersComponent } from './components/customers/customers.component';
import { UsersComponent } from './components/users/users.component';
import { NetPlansComponent } from './components/net-plans-list/net-plans.component';
import { NetPackagesComponent } from './components/net-packages-list/net-packages.component';
import { NetPackagesCreateComponent } from './components/net-packages-create/net-packages-create.component';
import { TicketCreateComponent } from './components/ticket-create/ticket-create.component';
import { TicketAssignComponent } from './components/ticket-assign/ticket-assign.component';
import { TicketCustomerComponent } from './components/ticket-customer/ticket-customer.component';
import { TicketEmployeeComponent } from './components/ticket-employee/ticket-employee.component';
import { NetPlansListCustomerComponent } from './components/net-plans-list-customer/net-plans-list-customer.component';
import { MessagesComponent } from './components/messages/messages.component';
import { ComposeComponent } from './components/compose/compose.component';
import {MessagesDetailsComponent} from './components/messages-details/messages-details.component';
import { MessagesSentComponent } from './components/messages-sent/messages-sent.component';
import { MessagesDetailsSentComponent } from './components/messages-details-sent/messages-details-sent.component';
import {FullCalendarModule} from 'ng-fullcalendar';
import { CaleendarComponent } from './components/caleendar/caleendar.component';
import { HelpDiskListComponent } from './components/help-disk-list/help-disk-list.component';
import { HelpDiskAddComponent } from './components/help-disk-add/help-disk-add.component';
import { ChartJsComponent } from './components/chart-js/chart-js.component';
import { TicketDetailsComponent } from './components/ticket-details/ticket-details.component';
import { HelpDiskDetailsComponent } from './components/help-disk-details/help-disk-details.component';
import { NetPlansAsignComponent } from './components/net-plans-asign/net-plans-asign.component';
import { ProfileShowComponent } from './components/profile-show/profile-show.component';
import { ProfileEditComponent } from './components/profile-edit/profile-edit.component';
import { TicketAllComponent } from './components/ticket-all/ticket-all.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

const appRoutes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'admin/add-user', component: AddUserComponent},
  {path: 'admin/ticket/asign', component: TicketAssignComponent},
  {path: 'admin/ticket/list', component: TicketAllComponent},
  {path: 'net-packages/list', component: NetPackagesComponent},
  {path: 'user/net-packages/create', component: NetPackagesCreateComponent},
  {path: 'user/add-customer', component: AddCustomerComponent},
  {path: 'user/customers', component: CustomersComponent},
  {path: 'user/users', component: UsersComponent},
  {path: 'employee/myProfile', component: ProfileShowComponent},
  {path: 'getProfile', component: ProfileShowComponent},
  {path: 'user/modifyProfile', component: ProfileEditComponent},
  {path: 'user/net-plans/list', component: NetPlansComponent},
  {path: 'user/net-plans/asign', component: NetPlansAsignComponent},
  {path: 'customer/ticket/list', component: TicketCustomerComponent},
  {path: 'employee/ticket/list', component: TicketEmployeeComponent},
  {path: 'ticket/details', component: TicketDetailsComponent},
  {path: 'user/charts/chart-js', component: ChartJsComponent},
  {path: 'customer/ticket/create', component: TicketCreateComponent},
  {path: 'customer/net-plans/list', component: NetPlansListCustomerComponent},
  {path: 'messages/inbox', component: MessagesComponent},
  {path: 'messages/compose', component: ComposeComponent},
  {path: 'messages/details', component: MessagesDetailsComponent},
  {path: 'messages/sent/details', component: MessagesDetailsSentComponent},
  {path: 'messages/sent', component: MessagesSentComponent},
  {path: 'calendar', component: CaleendarComponent},
  {path: 'login', component: LoginComponent},
  {path: 'user/help-disk/list', component: HelpDiskListComponent},
  {path: 'user/help-disk/add-new', component: HelpDiskAddComponent},
  {path: 'user/help-disk/details', component: HelpDiskDetailsComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},           /*to jest redicrtowanie to strona tytlowa*/
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({       /*jakie sa moduly*/
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent,
    AddUserComponent,
    LoginComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
    AddCustomerComponent,
    CustomersComponent,
    UsersComponent,
    NetPlansComponent,
    NetPackagesComponent,
    TicketCreateComponent,
    TicketAssignComponent,
    TicketCustomerComponent,
    TicketEmployeeComponent,
    NetPackagesCreateComponent,
    NetPlansListCustomerComponent,
    MessagesComponent,
    ComposeComponent,
    MessagesDetailsComponent,
    MessagesSentComponent,
    MessagesDetailsSentComponent,
    CaleendarComponent,
    HelpDiskListComponent,
    HelpDiskAddComponent,
    ChartJsComponent,
    TicketDetailsComponent,
    HelpDiskDetailsComponent,
    NetPlansAsignComponent,
    ProfileShowComponent,
    ProfileEditComponent,
    TicketAllComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FullCalendarModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: false}
      /* true for debugging, metoda potrzebna do rotowanie */
    ),
    HttpClientModule
  ],
  providers: [AuthService, AuthStorage, { provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true },
    {provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
