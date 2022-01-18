import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaMainServPubComponent } from './tabla-main-serv-pub.component';

describe('TablaMainServPubComponent', () => {
  let component: TablaMainServPubComponent;
  let fixture: ComponentFixture<TablaMainServPubComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaMainServPubComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaMainServPubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
