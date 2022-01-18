import { TestBed } from '@angular/core/testing';

import { ConsumidorService } from './consumidor.service';

describe('ConsumidorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConsumidorService = TestBed.get(ConsumidorService);
    expect(service).toBeTruthy();
  });
});
