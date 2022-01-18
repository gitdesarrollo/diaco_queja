import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomparecenciaComponent } from './incomparecencia.component';

describe('IncomparecenciaComponent', () => {
  let component: IncomparecenciaComponent;
  let fixture: ComponentFixture<IncomparecenciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncomparecenciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncomparecenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
