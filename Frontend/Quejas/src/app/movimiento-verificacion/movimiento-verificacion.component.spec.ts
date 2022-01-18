import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoVerificacionComponent } from './movimiento-verificacion.component';

describe('MovimientoVerificacionComponent', () => {
  let component: MovimientoVerificacionComponent;
  let fixture: ComponentFixture<MovimientoVerificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimientoVerificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimientoVerificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
