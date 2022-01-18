import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaginaConstruccionComponent } from './pagina-construccion.component';

describe('PaginaConstruccionComponent', () => {
  let component: PaginaConstruccionComponent;
  let fixture: ComponentFixture<PaginaConstruccionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaginaConstruccionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaginaConstruccionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
