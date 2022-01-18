import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatPuestoComponent } from './cat-puesto.component';

describe('CatPuestoComponent', () => {
  let component: CatPuestoComponent;
  let fixture: ComponentFixture<CatPuestoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatPuestoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatPuestoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
