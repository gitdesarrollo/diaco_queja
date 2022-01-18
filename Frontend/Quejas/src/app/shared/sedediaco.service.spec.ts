import { TestBed } from '@angular/core/testing';

import { SedediacoService } from './sedediaco.service';

describe('SedediacoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SedediacoService = TestBed.get(SedediacoService);
    expect(service).toBeTruthy();
  });
});
