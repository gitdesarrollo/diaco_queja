import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleConcVirtComponent } from './detalle-conc-virt.component';

describe('DetalleConcVirtComponent', () => {
  let component: DetalleConcVirtComponent;
  let fixture: ComponentFixture<DetalleConcVirtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetalleConcVirtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleConcVirtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
