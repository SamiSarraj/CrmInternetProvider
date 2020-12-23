import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Topics} from './topics';
import {Router} from '@angular/router';
import {APIService} from '../../services/api.service';
import {TicketCreate} from '../../models/ticket-create.model';

@Component({
  selector: 'app-ticket-create',
  templateUrl: './ticket-create.component.html',
  styleUrls: ['./ticket-create.component.css']
})
export class TicketCreateComponent implements OnInit {
  Topics: Topics[];
  public ticket = new TicketCreate();
  public error = false;
  public success = false;
  addTicketForm = new FormGroup( {
    title: new FormControl(),
    content: new FormControl(),
    topic: new FormControl(),
    importance: new FormControl(),
    }
  );
  constructor(private router: Router, private apiService: APIService) {
  }

  ngOnInit() {
    this.Topics = [
      {id: 1, name: 'Internet service'},
      {id: 2, name: 'Internet Packages'},
      {id: 3, name: 'Payment'},
      {id: 4, name: 'Crm Website'},
      {id: 5, name: 'Customer Service'},
    ];
  }
  reset() {
    this.addTicketForm.reset();
  }
  addTicket(): void {
    this.ticket.title = this.addTicketForm.controls['title'].value;
    this.ticket.content = this.addTicketForm.controls['content'].value;
    this.ticket.importance = this.addTicketForm.controls['importance'].value;
    this.ticket.topic = this.addTicketForm.controls['topic'].value;
    this.apiService.addTicket(this.ticket).subscribe(data => this.success = true,
      err => { console.log(err);
        this.error = true;
  });
}
}
