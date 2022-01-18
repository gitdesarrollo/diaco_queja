import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatPaisesComponent } from './cat-paises.component';

describe('CatPaisesComponent', () => {
  let component: CatPaisesComponent;
  let fixture: ComponentFixture<CatPaisesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatPaisesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatPaisesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
