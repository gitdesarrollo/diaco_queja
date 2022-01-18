import { TestBed } from '@angular/core/testing';

import { EtniaService } from './etnia.service';

describe('EtniaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EtniaService = TestBed.get(EtniaService);
    expect(service).toBeTruthy();
  });
});
