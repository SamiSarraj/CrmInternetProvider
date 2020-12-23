import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NetPlansListCustomerComponent } from './net-plans-list-customer.component';

describe('NetPlansListCustomerComponent', () => {
  let component: NetPlansListCustomerComponent;
  let fixture: ComponentFixture<NetPlansListCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NetPlansListCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetPlansListCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
