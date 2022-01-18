import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GuiaRegistrosComponent } from './guia-registros.component';

describe('GuiaRegistrosComponent', () => {
  let component: GuiaRegistrosComponent;
  let fixture: ComponentFixture<GuiaRegistrosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GuiaRegistrosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GuiaRegistrosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
