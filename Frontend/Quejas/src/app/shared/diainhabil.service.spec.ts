import { TestBed } from '@angular/core/testing';

import { DiainhabilService } from './diainhabil.service';

describe('DiainhabilService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DiainhabilService = TestBed.get(DiainhabilService);
    expect(service).toBeTruthy();
  });
});
