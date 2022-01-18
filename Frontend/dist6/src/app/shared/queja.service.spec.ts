import { TestBed } from '@angular/core/testing';

import { QuejaService } from './queja.service';

describe('QuejaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuejaService = TestBed.get(QuejaService);
    expect(service).toBeTruthy();
  });
});
