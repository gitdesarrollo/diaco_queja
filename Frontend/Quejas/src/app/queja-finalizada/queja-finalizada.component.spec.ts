import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuejaFinalizadaComponent } from './queja-finalizada.component';

describe('QuejaFinalizadaComponent', () => {
  let component: QuejaFinalizadaComponent;
  let fixture: ComponentFixture<QuejaFinalizadaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuejaFinalizadaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuejaFinalizadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
