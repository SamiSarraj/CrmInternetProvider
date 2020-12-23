import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Topics} from '../ticket-create/topics';
import {HelpDiskTopic} from '../../models/help-disk-topic.model';
import {APIService} from '../../services/api.service';
import {HelpDiskList} from '../../models/help-disk-list';
import {Router} from '@angular/router';

@Component({
  selector: 'app-help-disk-list',
  templateUrl: './help-disk-list.component.html',
  styleUrls: ['./help-disk-list.component.css']
})
export class HelpDiskListComponent implements OnInit {
  public success = false;
  public fail = false;
  public topic: Topics[];
  public helpDisk = new Array<HelpDiskList>();
  public topico = new HelpDiskTopic();
  public hidden = true;

  pickFieldForm = new FormGroup( {
    topic1: new FormControl(),
    /*content: new FormControl(),*/
  });
  constructor(private apiService: APIService, private router: Router) { }

  ngOnInit() {
    this.topic = [
      {id: 1, name: 'Internet service'},
      {id: 2, name: 'Internet Packages'},
      {id: 3, name: 'Payment'},
      {id: 4, name: 'Crm Website'},
      {id: 5, name: 'Customer Service'},
    ];
  }
pickField() {
  this.topico.topic = this.pickFieldForm.controls['topic1'].value;
  this.apiService.getAllHelpDisk(this.topico.topic).subscribe(data => this.helpDisk = data,
    err => {
      console.log(err);
      this.fail = true;
    });
  this.hidden = false;
}
  public goToHelpDisk(helpDiskId) {
    this.router.navigate(['/user/help-disk/details', {id: helpDiskId}]);
  }
}
