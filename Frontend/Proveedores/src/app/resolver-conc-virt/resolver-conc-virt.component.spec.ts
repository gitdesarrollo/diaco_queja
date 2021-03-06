import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ResolverConcVirtComponent } from './resolver-conc-virt.component';

describe('ResolverConcVirtComponent', () => {
  let component: ResolverConcVirtComponent;
  let fixture: ComponentFixture<ResolverConcVirtComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ResolverConcVirtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResolverConcVirtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
