import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {APIService} from '../../services/api.service';
import {UserProfile} from '../../models/user-profile.model';
import {Rating} from './rating';
import {FormControl, FormGroup} from '@angular/forms';
import {ProfileRatingComments} from '../../models/profile-rating-comments';

@Component({
  selector: 'app-profile-show',
  templateUrl: './profile-show.component.html',
  styleUrls: ['./profile-show.component.css']
})
export class ProfileShowComponent implements OnInit {
  public user = new  UserProfile();
  public userUsername: string;
  public employeeId: number;
  public error = false;
  public success = false;
  public commentAndRating = new ProfileRatingComments();
  rating: Rating[];
  addCommentAndRatingForm = new FormGroup({
    comment: new FormControl(),
    rating: new FormControl()
  });
  constructor(private route: ActivatedRoute,
              private apiService: APIService) { }
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.employeeId = +params.id;
      this.getUserInformation();
    });
    this.rating = [
      {id: 1, rate: 1},
      {id: 2, rate: 2},
      {id: 3, rate: 3},
      {id: 4, rate: 4},
      {id: 5, rate: 5},
    ];
  }
getUserInformation() {
    if (!isNaN(this.employeeId)) {
      this.apiService.getUserInformation(this.employeeId).subscribe(data => this.user = data,
        err => console.error(err));
    }  else {
        this.apiService.getMyUserInformation().subscribe(data => this.user = data,
          err => console.error(err));
      }
    }
  addCommentAndRating() {
    this.commentAndRating.content = this.addCommentAndRatingForm.controls['comment'].value;
    this.commentAndRating.rates = this.addCommentAndRatingForm.controls['rating'].value;
    this.commentAndRating.usernameEmployee = this.user.username;
    this.apiService.addCommentAndRating(this.commentAndRating).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }
}
