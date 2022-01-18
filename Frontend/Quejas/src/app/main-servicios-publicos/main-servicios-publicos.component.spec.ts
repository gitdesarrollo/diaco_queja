import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainServiciosPublicosComponent } from './main-servicios-publicos.component';

describe('MainServiciosPublicosComponent', () => {
  let component: MainServiciosPublicosComponent;
  let fixture: ComponentFixture<MainServiciosPublicosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainServiciosPublicosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainServiciosPublicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
