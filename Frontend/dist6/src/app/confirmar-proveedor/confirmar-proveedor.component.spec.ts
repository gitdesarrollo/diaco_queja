import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmarProveedorComponent } from './confirnar-proveedor.component';

describe('ConfirmarProveedorComponent', () => {
  let component: ConfirmarProveedorComponent;
  let fixture: ComponentFixture<ConfirmarProveedorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmarProveedorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmarProveedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
