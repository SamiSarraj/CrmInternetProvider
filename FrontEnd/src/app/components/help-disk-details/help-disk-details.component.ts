import { Component, OnInit } from '@angular/core';
import {TicketDetails} from '../../models/ticket-details.model';
import {TicketComment} from '../../models/ticket-comment.model';
import {State} from '../ticket-details/state';
import {FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {AuthService} from '../../core/auth.service';
import {APIService} from '../../services/api.service';
import {HelpDiskComment} from '../../models/help-disk-comment';
import {HelpDiskDetails} from '../../models/help-disk-details.model';

@Component({
  selector: 'app-help-disk-details',
  templateUrl: './help-disk-details.component.html',
  styleUrls: ['./help-disk-details.component.css']
})
export class HelpDiskDetailsComponent implements OnInit {

  public comment = new HelpDiskComment();
  public helpDisk = new HelpDiskDetails();
  public helpDiskId: number;
  public error = false;
  public success = false;
  addCommentForm = new FormGroup({
    content: new FormControl()});

  constructor(private route: ActivatedRoute,
              private authService: AuthService,
              private apiService: APIService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.helpDiskId = +params.id;
      this.getOneHelpDisk();
    });
  }
  getOneHelpDisk() {
    this.apiService.getOneHelpDisk(this.helpDiskId).subscribe(data => this.helpDisk = data,
      err => console.error(err));
  }
  addComment() {
    this.comment.content = this.addCommentForm.controls['content'].value;
    this.comment.helpDiskId = this.helpDiskId;
    this.apiService.addCommentHelpDisk(this.comment).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }
}
