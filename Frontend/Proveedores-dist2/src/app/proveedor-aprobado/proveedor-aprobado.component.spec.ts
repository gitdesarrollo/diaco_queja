import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProveedorAprobadoComponent } from './proveedor-aprobado.component';

describe('ProveedorAprobadoComponent', () => {
  let component: ProveedorAprobadoComponent;
  let fixture: ComponentFixture<ProveedorAprobadoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProveedorAprobadoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProveedorAprobadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
