import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { MagicLinkComponent } from './magic-link.component';

describe('MagicLinkComponent', () => {
  let component: MagicLinkComponent;
  let fixture: ComponentFixture<MagicLinkComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ MagicLinkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MagicLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
