import { Component, OnInit } from '@angular/core';
import {TicketCreate} from '../../models/ticket-create.model';
import {APIService} from '../../services/api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-ticket-all',
  templateUrl: './ticket-all.component.html',
  styleUrls: ['./ticket-all.component.css']
})
export class TicketAllComponent implements OnInit {

  public tickets = new Array<TicketCreate>();
  constructor(private apiService: APIService,  private router: Router) { }

  ngOnInit() {
    this.getAllTickets();
  }
  public getAllTickets() {
    this.apiService.getAllTickets().subscribe(data => this.tickets = data,
      err => console.error(err));
  }
  public goToTicket(ticketId) {
    this.router.navigate(['ticket/details', {id: ticketId}]);
  }
}
