import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatTipoComercioComponent } from './cat-tipo-comercio.component';

describe('CatTipoComercioComponent', () => {
  let component: CatTipoComercioComponent;
  let fixture: ComponentFixture<CatTipoComercioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatTipoComercioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatTipoComercioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
