import { Component, OnInit } from '@angular/core';
import {Message} from '../../models/message.model';
import {APIService} from '../../services/api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
public messagesChecked = new Array<Message>();
public messagesNotChecked = new Array<Message>();
public messageId: number;
  constructor(private apiService: APIService, private router: Router) { }

  ngOnInit() {
    this.getAllChecked();
    this.getAllNotChecked();
  }
getAllChecked() {
    this.apiService.getAllCheckedMessages().subscribe(data => this.messagesChecked = data,
      err => console.log(err));
}
getAllNotChecked() {
  this.apiService.getAllNotCheckedMessages().subscribe(data => this.messagesNotChecked = data,
    err => console.log(err));
}
public goToMessage(messageId) {
    this.router.navigate(['/messages/details', {id: messageId}]);
}
}
