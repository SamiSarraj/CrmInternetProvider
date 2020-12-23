import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NetPlansAsignComponent } from './net-plans-asign.component';

describe('NetPlansAsignComponent', () => {
  let component: NetPlansAsignComponent;
  let fixture: ComponentFixture<NetPlansAsignComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NetPlansAsignComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetPlansAsignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
