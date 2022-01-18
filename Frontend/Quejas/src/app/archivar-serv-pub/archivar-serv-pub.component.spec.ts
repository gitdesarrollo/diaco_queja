import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchivarServPubComponent } from './archivar-serv-pub.component';

describe('ArchivarServPubComponent', () => {
  let component: ArchivarServPubComponent;
  let fixture: ComponentFixture<ArchivarServPubComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArchivarServPubComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArchivarServPubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
