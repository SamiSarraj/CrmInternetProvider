import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NetPackagesComponent } from './net-packages.component';

describe('NetPackagesComponent', () => {
  let component: NetPackagesComponent;
  let fixture: ComponentFixture<NetPackagesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NetPackagesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NetPackagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
