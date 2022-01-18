import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { BitacoraConcVirtComponent } from './bitacora-conc-virt.component';

describe('BitacoraConcVirtComponent', () => {
  let component: BitacoraConcVirtComponent;
  let fixture: ComponentFixture<BitacoraConcVirtComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ BitacoraConcVirtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BitacoraConcVirtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
