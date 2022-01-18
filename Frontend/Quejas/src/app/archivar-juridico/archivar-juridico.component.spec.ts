import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchivarJuridicoComponent } from './archivar-juridico.component';

describe('ArchivarJuridicoComponent', () => {
  let component: ArchivarJuridicoComponent;
  let fixture: ComponentFixture<ArchivarJuridicoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArchivarJuridicoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArchivarJuridicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
