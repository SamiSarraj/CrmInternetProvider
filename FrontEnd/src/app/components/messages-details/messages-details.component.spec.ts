import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagesDetailsComponent } from './messages-details.component';

describe('MessagesDetailsComponent', () => {
  let component: MessagesDetailsComponent;
  let fixture: ComponentFixture<MessagesDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessagesDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessagesDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
