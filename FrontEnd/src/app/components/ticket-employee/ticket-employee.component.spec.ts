import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketEmployeeComponent } from './ticket-employee.component';

describe('TicketEmployeeComponent', () => {
  let component: TicketEmployeeComponent;
  let fixture: ComponentFixture<TicketEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
