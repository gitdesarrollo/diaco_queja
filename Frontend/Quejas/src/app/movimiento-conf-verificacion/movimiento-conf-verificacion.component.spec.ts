import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoConfVerificacionComponent } from './movimiento-conf-verificacion.component';

describe('MovimientoConfVerificacionComponent', () => {
  let component: MovimientoConfVerificacionComponent;
  let fixture: ComponentFixture<MovimientoConfVerificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimientoConfVerificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimientoConfVerificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
