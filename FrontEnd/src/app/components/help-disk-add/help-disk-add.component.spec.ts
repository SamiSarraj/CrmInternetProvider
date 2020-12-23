import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HelpDiskAddComponent } from './help-disk-add.component';

describe('HelpDiskAddComponent', () => {
  let component: HelpDiskAddComponent;
  let fixture: ComponentFixture<HelpDiskAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HelpDiskAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HelpDiskAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
