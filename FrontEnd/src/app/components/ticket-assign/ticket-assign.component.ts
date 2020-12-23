import { Component, OnInit } from '@angular/core';
import {TicketAsignList} from '../../models/ticket-asign-list.model';
import {Router} from '@angular/router';
import {APIService} from '../../services/api.service';
import {Importance} from './importance';
import {Topics} from '../ticket-create/topics';
import {TicketEmployee} from '../../models/ticket-employee.model';
import {FormControl, FormGroup} from '@angular/forms';
import {TicketAsign} from '../../models/ticket-asign.model';

@Component({
  selector: 'app-ticket-assign',
  templateUrl: './ticket-assign.component.html',
  styleUrls: ['./ticket-assign.component.css']
})
export class TicketAssignComponent implements OnInit {
  public ticketAsign = new TicketAsign();
  public tickets = new Array<TicketAsignList>();
  public employees = new Array<TicketEmployee>();
  public error = false;
  public success = false;
  importance: Importance[];
  topics: Topics[];
  asignTicketForm = new FormGroup( {
    employeeName : new FormControl(),
    ticketId : new FormControl()
  });
  constructor(private router: Router, private apiService: APIService) { }

  ngOnInit() {
    this.importance = [
      {id: 1, name: 'High'},
      {id: 2, name: 'Medium'},
      {id: 3, name: 'low'}
    ];
    this.topics = [
      {id: 1, name: 'Internet service'},
      {id: 2, name: 'Internet Packages'},
      {id: 3, name: 'Payment'},
      {id: 4, name: 'Crm Website'},
      {id: 5, name: 'Customer Service'},
    ];
    this.getAllEmployeeAsignTicket();
    this.getAllUnassignedTickets();
  }
  public getAllEmployeeAsignTicket() {
    this.apiService.getAllEmployeeAsignTicket().subscribe(
      data => this.employees = data,
      err => console.error(err)
    );
  }
  public getAllUnassignedTickets() {
    this.apiService.getAllUnassignedTickets().subscribe(
      data => this.tickets = data,
      err => console.log(err)
    );
  }
asignTicket(): void {
this.ticketAsign.employeeName = this.asignTicketForm.controls['employeeName'].value;
this.ticketAsign.idTicket = this.asignTicketForm.controls['ticketId'].value;
this.apiService.asignTicket(this.ticketAsign).subscribe(data => this.success = true,
  err => {
    console.log(err);
    this.error = true;
  });
}
}
