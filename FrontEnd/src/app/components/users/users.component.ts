import { Component, OnInit } from '@angular/core';
import {Customer} from '../../models/customer.model';
import {APIService} from '../../services/api.service';
import {FormControl, FormGroup} from '@angular/forms';
import {User} from '../../models/user.model';
import {CountNew} from '../chart-js/count-new';
import {UserList} from '../../models/user-list.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users = new Array<UserList>();
  public amountTickets  = new CountNew();
  public user = new User();
  public success = false;
  public fail = false;
  public username: string;
  constructor(private apiService: APIService) { }
  updateUserForm = new FormGroup({
    role: new FormControl()
  });

  ngOnInit() {
    this.getAllEmployeesWithTickets();
  }
  public getAllEmployeesWithTickets() {
    this.apiService.getAllEmployeesWithTickets().subscribe(data => this.users = data,
      err => console.error(err));
  }
  updateUser(): void {
    this.user.role = this.updateUserForm.controls['role'].value;
    this.user.username = this.username;
    this.apiService.updateUser(this.user).subscribe(data => this.success = true,
      err => {
        console.log(err);
      });
  }
  getUserUsername(username) {
    this.username = username;
  }
}
