import { TestBed } from '@angular/core/testing';

import { CatAreaService } from './cat-area.service';

describe('CatAreaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CatAreaService = TestBed.get(CatAreaService);
    expect(service).toBeTruthy();
  });
});
