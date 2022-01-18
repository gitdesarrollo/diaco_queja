import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatProveedorComponent } from './cat-proveedor.component';

describe('CatProveedorComponent', () => {
  let component: CatProveedorComponent;
  let fixture: ComponentFixture<CatProveedorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatProveedorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatProveedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
