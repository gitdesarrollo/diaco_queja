import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfUsuarioConcPreviaComponent } from './conf-usuario-conc-previa.component';

describe('ConfUsuarioConcPreviaComponent', () => {
  let component: ConfUsuarioConcPreviaComponent;
  let fixture: ComponentFixture<ConfUsuarioConcPreviaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfUsuarioConcPreviaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfUsuarioConcPreviaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
