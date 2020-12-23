import { Component, OnInit } from '@angular/core';
import {Message} from '../../models/message.model';
import {APIService} from '../../services/api.service';
import {Compose} from '../../models/compose.model';
import {User} from '../../models/user.model';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {
  public message = new Message();
  public sender = new Compose();
  public employees = new Array<User>();
  public success = false;
  public fail = false;
  createMessageForm = new FormGroup( {
    receptor: new FormControl(),
    subject: new FormControl(),
    content: new FormControl(),
  });
  constructor(private apiService: APIService) { }

  ngOnInit() {
this.getAllEmpolyees();
this.getUserFullName();
  }
getUserFullName() {
    this.apiService.getUserFullName().subscribe(data => this.sender = data,
      err => console.log(err));
}
getAllEmpolyees() {
    this.apiService.getAllEmployees().subscribe(data => this.employees = data,
      err => console.log(err));
}
  sendMessage(): void {
    this.message.usernameRecpetor = this.createMessageForm.controls['receptor'].value;
    this.message.subject = this.createMessageForm.controls['subject'].value;
    this.message.content = this.createMessageForm.controls['content'].value;
    this.apiService.createMessage(this.message).subscribe(data => this.success = true,
    err => {
      console.log(err);
      this.fail = true;
    });
  }
}
