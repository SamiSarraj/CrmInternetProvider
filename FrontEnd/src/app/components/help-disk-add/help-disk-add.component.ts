import { Component, OnInit } from '@angular/core';
import {Topics} from '../ticket-create/topics';
import {HelpDiskAdd} from '../../models/help-disk-add.model';
import {FormControl, FormGroup} from '@angular/forms';
import {APIService} from '../../services/api.service';

@Component({
  selector: 'app-help-disk-add',
  templateUrl: './help-disk-add.component.html',
  styleUrls: ['./help-disk-add.component.css']
})
export class HelpDiskAddComponent implements OnInit {
  public topic: Topics[];
  public helpDisk = new HelpDiskAdd();
  public error = false;
  public success = false;
  addHelpForm = new FormGroup( {
    topic1: new FormControl(),
    content: new FormControl(),
    title: new FormControl(),

    /*content: new FormControl(),*/
  });
  constructor(private apiService: APIService) { }

  ngOnInit() {
    this.topic = [
      {id: 1, name: 'Internet service'},
      {id: 2, name: 'Internet Packages'},
      {id: 3, name: 'Payment'},
      {id: 4, name: 'Crm Website'},
      {id: 5, name: 'Customer Service'},
    ];
  }
  addHelp(): void {
    this.helpDisk.content = this.addHelpForm.controls['content'].value;
    this.helpDisk.title = this.addHelpForm.controls['title'].value;
    this.helpDisk.topic = this.addHelpForm.controls['topic1'].value;
    this.apiService.addHelp(this.helpDisk).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });

  }
  reset() {
    this.addHelpForm.reset();
  }
}
