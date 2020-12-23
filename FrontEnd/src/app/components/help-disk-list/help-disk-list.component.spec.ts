import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HelpDiskListComponent } from './help-disk-list.component';

describe('HelpDiskListComponent', () => {
  let component: HelpDiskListComponent;
  let fixture: ComponentFixture<HelpDiskListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HelpDiskListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HelpDiskListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
