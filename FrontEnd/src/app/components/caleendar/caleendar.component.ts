import {Component, OnInit, ViewChild} from '@angular/core';
import { CalendarComponent } from 'ng-fullcalendar';
import { Options } from 'fullcalendar';
import {Observable, of} from 'rxjs';
import {CalendarModel} from '../../models/calendar.model';
import {FormControl, FormGroup} from '@angular/forms';
import {User} from '../../models/user.model';
import {APIService} from '../../services/api.service';

@Component({
  selector: 'app-caleendar',
  templateUrl: './caleendar.component.html',
  styleUrls: ['./caleendar.component.css']
})
export class CaleendarComponent implements OnInit {
  calendarOptions: Options;
  displayEvent: any;
  @ViewChild(CalendarComponent) ucCalendar: CalendarComponent;
  public calendar = new CalendarModel();
  public events = new Array<CalendarModel>();
  public error = false;
  public success = false;
  addEventForm = new FormGroup( {
    title: new FormControl(),
    start: new FormControl(),
    end: new FormControl(),
    allDay: new FormControl()
  });
  constructor(private apiService: APIService) { }

  ngOnInit() {
    this.getAllEvents();
    this.apiService.getAllEvents().subscribe(data => {
      this.calendarOptions = {
        editable: true,
        eventLimit: false,
        header: {
          left: 'prev,next today',
          center: 'title',
          right: 'month,agendaWeek,agendaDay,listMonth'
        },
        events: data
      };
    });
    /*this.getEvents().subscribe(data => {
      this.calendarOptions = {
        editable: true,
        eventLimit: false,
        header: {
          left: 'prev,next today',
          center: 'title',
          right: 'month,agendaWeek,agendaDay,listMonth'
        },
        events: {
          data
        }
      };
    });*/
    /*this.calendarOptions = {
      editable: true,
      eventLimit: false,
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,listMonth'
      },
      events: [
        this.events
       /* {
          title: 'dsadsa',
        },
        {
          title: 'costam',
          start: '2018-11-23T13:00:00'
        }
    ]
    };*/
  }
  clickButton(model: any) {
    this.displayEvent = model;
  }
  eventClick(model: any) {
    model = {
      event: {
        id: model.event.id,
        start: model.event.start,
        end: model.event.end,
        title: model.event.title,
        allDay: model.event.allDay
        // other params
      },
      duration: {}
    }
    this.displayEvent = model;
  }
  updateEvent(model: any) {
    model = {
      event: {
        id: model.event.id,
        start: model.event.start,
        end: model.event.end,
        title: model.event.title
        // other params
      },
      duration: {
        _data: model.duration._data
      }
    }
    this.displayEvent = model;
  }
  public getEvents(): Observable<any> {
    const dateObj = new Date();
    const yearMonth = dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);
    const data: any = this.events;
    return of(data);
  }
  reset() {
    this.addEventForm.reset();
  }
  addEvent(): void {
    this.calendar.title = this.addEventForm.controls['title'].value;
    this.calendar.start = this.addEventForm.controls['start'].value;
    this.calendar.end = this.addEventForm.controls['end'].value;
    this.calendar.allDay = this.addEventForm.controls['allDay'].value;
    this.apiService.addEvent(this.calendar).subscribe(data => this.success = true,
      err => {
        console.log(err);
        this.error = true;
      });
  }
  getAllEvents(): Observable<any> {
    this.apiService.getAllEvents().subscribe(data => this.events = data,
      err => console.error(err));
    return of(this.events);
  }
}
