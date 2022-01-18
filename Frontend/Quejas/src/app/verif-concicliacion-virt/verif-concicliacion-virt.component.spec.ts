import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifConcicliacionVirtComponent } from './verif-concicliacion-virt.component';

describe('VerifConcicliacionVirtComponent', () => {
  let component: VerifConcicliacionVirtComponent;
  let fixture: ComponentFixture<VerifConcicliacionVirtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerifConcicliacionVirtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifConcicliacionVirtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
