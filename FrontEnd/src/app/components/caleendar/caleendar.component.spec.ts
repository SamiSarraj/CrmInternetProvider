import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaleendarComponent } from './caleendar.component';

describe('CaleendarComponent', () => {
  let component: CaleendarComponent;
  let fixture: ComponentFixture<CaleendarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaleendarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaleendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
