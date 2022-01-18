import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SegundaAudienciaComponent } from './segunda-audiencia.component';

describe('SegundaAudienciaComponent', () => {
  let component: SegundaAudienciaComponent;
  let fixture: ComponentFixture<SegundaAudienciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SegundaAudienciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SegundaAudienciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
