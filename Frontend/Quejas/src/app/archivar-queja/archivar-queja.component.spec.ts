import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchivarQuejaComponent } from './archivar-queja.component';

describe('ArchivarQuejaComponent', () => {
  let component: ArchivarQuejaComponent;
  let fixture: ComponentFixture<ArchivarQuejaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArchivarQuejaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArchivarQuejaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
