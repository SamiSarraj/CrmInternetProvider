import { Component, OnInit } from '@angular/core';
import {TicketCreate} from '../../models/ticket-create.model';
import {APIService} from '../../services/api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ticket-customer',
  templateUrl: './ticket-customer.component.html',
  styleUrls: ['./ticket-customer.component.css']
})
export class TicketCustomerComponent implements OnInit {
  public tickets = new Array<TicketCreate>();
  constructor(private apiService: APIService,  private router: Router) { }

  ngOnInit() {
    this.getAllTickets();
  }
  public getAllTickets() {
    this.apiService.getAllCustomerTickets().subscribe(data => this.tickets = data,
      err => console.error(err));
  }
  public goToTicket(ticketId) {
    this.router.navigate(['/ticket/details', {id: ticketId}]);
  }
}
