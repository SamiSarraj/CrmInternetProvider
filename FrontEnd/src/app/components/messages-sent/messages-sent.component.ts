import { Component, OnInit } from '@angular/core';
import {Message} from '../../models/message.model';
import {APIService} from '../../services/api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-messages-sent',
  templateUrl: './messages-sent.component.html',
  styleUrls: ['./messages-sent.component.css']
})
export class MessagesSentComponent implements OnInit {
  public messagesSent = new Array<Message>();
  constructor(private apiService: APIService, private router: Router) { }

  ngOnInit() {
    this.getAllSent();
  }
  getAllSent() {
    this.apiService.getAllSentMessages().subscribe(data => this.messagesSent = data,
      err => console.log(err));
  }
  goToMessage(messageId) {
    this.router.navigate(['/messages/sent/details', {id: messageId}]);
  }
}
