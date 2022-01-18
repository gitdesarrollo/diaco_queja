import { TestBed } from '@angular/core/testing';

import { MotivoquejaService } from './motivoqueja.service';

describe('MotivoquejaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MotivoquejaService = TestBed.get(MotivoquejaService);
    expect(service).toBeTruthy();
  });
});
