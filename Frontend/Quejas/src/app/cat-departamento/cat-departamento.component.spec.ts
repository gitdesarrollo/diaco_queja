import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatDepartamentoComponent } from './cat-departamento.component';

describe('CatDepartamentoComponent', () => {
  let component: CatDepartamentoComponent;
  let fixture: ComponentFixture<CatDepartamentoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatDepartamentoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatDepartamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
