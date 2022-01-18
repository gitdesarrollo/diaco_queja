import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatTipoConsumidorComponent } from './cat-tipo-consumidor.component';

describe('CatTipoConsumidorComponent', () => {
  let component: CatTipoConsumidorComponent;
  let fixture: ComponentFixture<CatTipoConsumidorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatTipoConsumidorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatTipoConsumidorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
