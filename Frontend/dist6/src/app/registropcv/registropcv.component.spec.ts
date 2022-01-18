import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistropcvComponent } from './registropcv.component';

describe('RegistropcvComponent', () => {
  let component: RegistropcvComponent;
  let fixture: ComponentFixture<RegistropcvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegistropcvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistropcvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
