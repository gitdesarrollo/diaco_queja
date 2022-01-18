import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatTipoColaComponent } from './cat-tipo-cola.component';

describe('CatTipoColaComponent', () => {
  let component: CatTipoColaComponent;
  let fixture: ComponentFixture<CatTipoColaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatTipoColaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatTipoColaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
