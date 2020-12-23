import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagesSentComponent } from './messages-sent.component';

describe('MessagesSentComponent', () => {
  let component: MessagesSentComponent;
  let fixture: ComponentFixture<MessagesSentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessagesSentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessagesSentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
