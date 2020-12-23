import { Component, OnInit } from '@angular/core';
import {Message} from '../../models/message.model';
import {ActivatedRoute} from '@angular/router';
import {AuthService} from '../../core/auth.service';
import {APIService} from '../../services/api.service';

@Component({
  selector: 'app-messages-details-sent',
  templateUrl: './messages-details-sent.component.html',
  styleUrls: ['./messages-details-sent.component.css']
})
export class MessagesDetailsSentComponent implements OnInit {
  public message = new Message();
  public messageId: number;
  constructor(private route: ActivatedRoute,
              private authService: AuthService,
              private apiService: APIService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.messageId = +params.id;
      this.getOneSentMessage();
    });
  }
  getOneSentMessage() {
    this.apiService.getOneMessage(this.messageId).subscribe(data => this.message = data,
      err => console.error(err));
  }
}
