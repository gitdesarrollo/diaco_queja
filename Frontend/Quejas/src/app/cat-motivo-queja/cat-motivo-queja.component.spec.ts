import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatMotivoQuejaComponent } from './cat-motivo-queja.component';

describe('CatMotivoQuejaComponent', () => {
  let component: CatMotivoQuejaComponent;
  let fixture: ComponentFixture<CatMotivoQuejaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatMotivoQuejaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatMotivoQuejaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
