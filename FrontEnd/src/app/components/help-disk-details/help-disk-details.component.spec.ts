import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HelpDiskDetailsComponent } from './help-disk-details.component';

describe('HelpDiskDetailsComponent', () => {
  let component: HelpDiskDetailsComponent;
  let fixture: ComponentFixture<HelpDiskDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HelpDiskDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HelpDiskDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
