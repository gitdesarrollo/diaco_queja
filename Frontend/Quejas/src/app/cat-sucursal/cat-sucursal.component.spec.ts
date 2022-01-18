import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatSucursalComponent } from './cat-sucursal.component';

describe('CatSucursalComponent', () => {
  let component: CatSucursalComponent;
  let fixture: ComponentFixture<CatSucursalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatSucursalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatSucursalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
