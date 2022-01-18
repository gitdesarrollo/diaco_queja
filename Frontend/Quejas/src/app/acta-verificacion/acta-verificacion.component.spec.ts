import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActaVerificacionComponent } from './acta-verificacion.component';

describe('ActaVerificacionComponent', () => {
  let component: ActaVerificacionComponent;
  let fixture: ComponentFixture<ActaVerificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActaVerificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActaVerificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
