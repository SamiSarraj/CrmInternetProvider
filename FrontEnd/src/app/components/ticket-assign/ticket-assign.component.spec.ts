import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketAssignComponent } from './ticket-assign.component';

describe('TicketAssignComponent', () => {
  let component: TicketAssignComponent;
  let fixture: ComponentFixture<TicketAssignComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketAssignComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketAssignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
