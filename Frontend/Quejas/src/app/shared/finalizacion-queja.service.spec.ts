import { TestBed } from '@angular/core/testing';

import { FinalizacionQuejaService } from './finalizacion-queja.service';

describe('FinalizacionQuejaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FinalizacionQuejaService = TestBed.get(FinalizacionQuejaService);
    expect(service).toBeTruthy();
  });
});
