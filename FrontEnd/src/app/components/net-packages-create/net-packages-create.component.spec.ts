import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NetPackagesCreateComponent } from './net-packages-create.component';

describe('NetPackagesCreateComponent', () => {
  let component: NetPackagesCreateComponent;
  let fixture: ComponentFixture<NetPackagesCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NetPackagesCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetPackagesCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
