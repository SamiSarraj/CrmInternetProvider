import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagesDetailsSentComponent } from './messages-details-sent.component';

describe('MessagesDetailsSentComponent', () => {
  let component: MessagesDetailsSentComponent;
  let fixture: ComponentFixture<MessagesDetailsSentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessagesDetailsSentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessagesDetailsSentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
