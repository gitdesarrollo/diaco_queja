import { TestBed } from '@angular/core/testing';
import { CatalogoService } from './catalogo.service';
describe('CatalogoService', function () {
    beforeEach(function () { return TestBed.configureTestingModule({}); });
    it('should be created', function () {
        var service = TestBed.get(CatalogoService);
        expect(service).toBeTruthy();
    });
});
//# sourceMappingURL=catalogo.service.spec.js.map