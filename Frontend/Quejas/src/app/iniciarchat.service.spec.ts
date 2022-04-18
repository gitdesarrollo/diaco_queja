import { TestBed } from '@angular/core/testing';

import { IniciarchatService } from './iniciarchat.service';

describe('IniciarchatService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IniciarchatService = TestBed.get(IniciarchatService);
    expect(service).toBeTruthy();
  });
});
