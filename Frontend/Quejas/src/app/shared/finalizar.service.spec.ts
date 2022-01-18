import { TestBed } from '@angular/core/testing';

import { FinalizarService } from './finalizar.service';

describe('FinalizarService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FinalizarService = TestBed.get(FinalizarService);
    expect(service).toBeTruthy();
  });
});
