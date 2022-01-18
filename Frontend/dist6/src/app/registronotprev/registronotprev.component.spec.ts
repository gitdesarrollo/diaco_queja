import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistronotprevComponent } from './registronotprev.component';

describe('RegistronotprevComponent', () => {
  let component: RegistronotprevComponent;
  let fixture: ComponentFixture<RegistronotprevComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistronotprevComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistronotprevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
