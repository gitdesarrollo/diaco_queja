import { TestBed } from '@angular/core/testing';

import { MovimExpService } from './movim-exp.service';

describe('MovimExpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MovimExpService = TestBed.get(MovimExpService);
    expect(service).toBeTruthy();
  });
});
