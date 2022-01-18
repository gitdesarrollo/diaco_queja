import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovimientoConfJuridicoComponent } from './movimiento-conf-juridico.component';

describe('MovimientoConfJuridicoComponent', () => {
  let component: MovimientoConfJuridicoComponent;
  let fixture: ComponentFixture<MovimientoConfJuridicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovimientoConfJuridicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovimientoConfJuridicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
