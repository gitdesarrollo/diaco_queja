import { TestBed } from '@angular/core/testing';

import { CompermanenteService } from './compermanente.service';

describe('CompermanenteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CompermanenteService = TestBed.get(CompermanenteService);
    expect(service).toBeTruthy();
  });
});
