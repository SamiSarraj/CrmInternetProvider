import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketCustomerComponent } from './ticket-customer.component';
import {APIService} from '../../services/api.service';
import {TicketCreate} from '../../models/ticket-create.model';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

describe('TicketCustomerComponent', () => {
  let component: TicketCustomerComponent;
  let fixture: ComponentFixture<TicketCustomerComponent>;
  let service: APIService;
  let httpMock: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketCustomerComponent ]
    })
    .compileComponents();
  }));
  service = TestBed.get(APIService);
  httpMock = TestBed.get(HttpTestingController);
  beforeEach(() => {
    fixture = TestBed.createComponent(TicketCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  afterEach(() => {
    httpMock.verify();
  });
let t: Date;
t = new Date(2012, 0, 1);
  it('Should retrive tickets form data base', () => {
const tickets: TicketCreate[] = [
  {title: 'lolo', content: 'dasdas', importance: 'not so', topic: 'ehhhm', id: 1, state: 'string', created: t},
  {title: 'lolo', content: 'dasdas', importance: 'not so', topic: 'ehhhm', id: 2, state: 'string', created: t},
  ];
service.getAllCustomerTickets().subscribe(data => {
expect(data.length).toBe(2);
expect(data).toEqual(tickets);
});
const request = httpMock.expectOne(`${service.API_URL}/ticket/allCustomerTickets`);
expect(request.request.method).toBe('GET');
request.flush(tickets);
  });
});
