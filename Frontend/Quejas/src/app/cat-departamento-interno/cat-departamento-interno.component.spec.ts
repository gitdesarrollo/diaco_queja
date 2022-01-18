import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatDepartamentoInternoComponent } from './cat-departamento-interno.component';

describe('CatDepartamentoInternoComponent', () => {
  let component: CatDepartamentoInternoComponent;
  let fixture: ComponentFixture<CatDepartamentoInternoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatDepartamentoInternoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatDepartamentoInternoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
