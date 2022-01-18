import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoConfirmarComponent } from './movimiento-confirmar.component';

describe('MovimientoConfirmarComponent', () => {
  let component: MovimientoConfirmarComponent;
  let fixture: ComponentFixture<MovimientoConfirmarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimientoConfirmarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimientoConfirmarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
