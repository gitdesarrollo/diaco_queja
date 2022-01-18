import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifDatosComponent } from './verif-datos.component';

describe('VerifDatosComponent', () => {
  let component: VerifDatosComponent;
  let fixture: ComponentFixture<VerifDatosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifDatosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifDatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
