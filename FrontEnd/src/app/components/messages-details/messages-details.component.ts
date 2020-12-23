import { Component, OnInit } from '@angular/core';
import {Message} from '../../models/message.model';
import {APIService} from '../../services/api.service';
import {AuthService} from '../../core/auth.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-messages-details',
  templateUrl: './messages-details.component.html',
  styleUrls: ['./messages-details.component.css']
})
export class MessagesDetailsComponent implements OnInit {
  public message = new Message();
  public messageId: number;
  constructor(private route: ActivatedRoute,
              private authService: AuthService,
              private apiService: APIService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.messageId = +params.id;
      this.getOneMessage();
    });
  }
getOneMessage() {
    this.apiService.getOneMessage(this.messageId).subscribe(data => this.message = data,
      err => console.error(err));
}
}
