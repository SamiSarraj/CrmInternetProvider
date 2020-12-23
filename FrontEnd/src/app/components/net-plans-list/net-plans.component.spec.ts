import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NetPlansComponent } from './net-plans.component';

describe('NetPlansComponent', () => {
  let component: NetPlansComponent;
  let fixture: ComponentFixture<NetPlansComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NetPlansComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetPlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
