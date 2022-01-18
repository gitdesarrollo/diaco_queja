import { TestBed } from '@angular/core/testing';

import { TipoConsumidorService } from './tipo-consumidor.service';

describe('TipoConsumidorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TipoConsumidorService = TestBed.get(TipoConsumidorService);
    expect(service).toBeTruthy();
  });
});
