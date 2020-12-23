import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomersComponent } from './customers.component';
import {TicketCreate} from '../../models/ticket-create.model';
import {APIService} from '../../services/api.service';
import {HttpTestingController} from '@angular/common/http/testing';
import {Customer} from '../../models/customer.model';

describe('CustomersComponent', () => {
  let component: CustomersComponent;
  let fixture: ComponentFixture<CustomersComponent>;
  let service: APIService;
  let httpMock: HttpTestingController;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomersComponent ]
    })
    .compileComponents();
  }));
  service = TestBed.get(APIService);
  httpMock = TestBed.get(HttpTestingController);
  beforeEach(() => {
    fixture = TestBed.createComponent(CustomersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  afterEach(() => {
    httpMock.verify();
  });
  let t: Date;
  t = new Date(2012, 0, 1);
  it('Should retrive tickets form data base', () => {
    const customers: Customer[] = [
      {firstName: 'lolo', lastName: 'dasdas', email: 'not so', country: 'ehhhm', mobile: 1, city: 'string', address: 'sads', dateOfBirth: t,
        internetMainUse: 'dsadas', sex: 'test', username: 'test', password: 'test', joined: t, userInformation: 'test'},
      {firstName: 'lolo', lastName: 'dasdas', email: 'not so', country: 'ehhhm', mobile: 1, city: 'string', address: 'sads', dateOfBirth: t,
        internetMainUse: 'dsadas', sex: 'test', username: 'test', password: 'test', joined: t, userInformation: 'test'},
    ];
    service.getAllCustomers().subscribe(data => {
      expect(data.length).toBe(2);
      expect(data).toEqual(customers);
    });
    const request = httpMock.expectOne(`${service.API_URL}/user/allCustomers`);
    expect(request.request.method).toBe('GET');
    request.flush(customers);
  });
});
