import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatConsumidorComponent } from './cat-consumidor.component';

describe('CatConsumidorComponent', () => {
  let component: CatConsumidorComponent;
  let fixture: ComponentFixture<CatConsumidorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatConsumidorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatConsumidorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
