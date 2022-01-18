import * as tslib_1 from "tslib";
import { MatPaginatorIntl } from '@angular/material';
var PaginatorEspañol = /** @class */ (function (_super) {
    tslib_1.__extends(PaginatorEspañol, _super);
    function PaginatorEspañol() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.itemsPerPageLabel = 'Items por Página';
        _this.nextPageLabel = 'Siguiente';
        _this.previousPageLabel = 'Previa';
        _this.firstPageLabel = "Primera Página";
        _this.lastPageLabel = "Última Página";
        _this.getRangeLabel = function (page, pageSize, length) {
            if (length === 0 || pageSize === 0) {
                return '0 de ' + length;
            }
            length = Math.max(length, 0);
            var startIndex = page * pageSize;
            //Si el índice de inicio excede la longitud de la lista, no intente arreglar el índice final hasta el final
            var endIndex = startIndex < length ? Math.min(startIndex + pageSize, length) : startIndex + pageSize;
            return startIndex + 1 + ' - ' + endIndex + ' de ' + length;
        };
        return _this;
    }
    return PaginatorEspañol;
}(MatPaginatorIntl));
export { PaginatorEspañol };
//# sourceMappingURL=paginator-español.js.map