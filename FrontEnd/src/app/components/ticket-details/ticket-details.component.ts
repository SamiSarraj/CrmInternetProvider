import { Component, OnInit } from '@angular/core';
import {TicketDetails} from '../../models/ticket-details.model';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../core/auth.service';
import {APIService} from '../../services/api.service';
import {FormControl, FormGroup} from '@angular/forms';
import {TicketComment} from '../../models/ticket-comment.model';
import {State} from './state';

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css']
})
export class TicketDetailsComponent implements OnInit {
  public ticket = new TicketDetails();
  public ticketId: number;
  public comment = new TicketComment();
  public error = false;
  public success = false;
  public state: State[];
  addCommentForm = new FormGroup({
    comment: new FormControl()});
  addCommentForm2 = new FormGroup({
    comment: new FormControl(),
  state: new FormControl()});

  constructor(private route: ActivatedRoute,
              private authService: AuthService,
              private apiService: APIService,
              private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.ticketId = +params.id;
      this.getOneTicket();
    });
    this.state = [
      {id: 1, name: 'Completed'},
      {id: 2, name: 'Resolving'},
      {id: 3, name: 'Need Attention'},
      {id: 4, name: 'Failed'},
    ];
  }
  getOneTicket() {
    this.apiService.getOneTicket(this.ticketId).subscribe(data => this.ticket = data,
      err => console.error(err));
  }
  addComment() {
    this.comment.comments = this.addCommentForm.controls['comment'].value;
    this.comment.ticketId = this.ticketId;
    this.apiService.addComment(this.comment).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }
  addComment2() {
    this.comment.comments = this.addCommentForm2.controls['comment'].value;
    this.comment.ticketId = this.ticketId;
    this.comment.state = this.addCommentForm2.controls['state'].value;
    this.apiService.addComment(this.comment).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }
  employee(): boolean {
    return this.authService.isAuthenticated() && this.authService.isEmpolyee();
  }
  public goToUser(employeeId) {
    this.router.navigate(['getProfile/', {id: employeeId}]);
  }
}
