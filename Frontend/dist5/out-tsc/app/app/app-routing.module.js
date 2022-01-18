import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
var routes = [
    { path: 'quejaini', loadChildren: function () { return import('./quejaini/quejaini.component').then(function (m) { return m.QuejainiComponent; }); }
    }
    //
    // {path: 'quejacon/:dato/:id', component: QuejaconComponent},
    // {path: 'quejaweb', component: QuejaextComponent}
    // {path: 'quejaini', component: QuejainiComponent},
    // {path: 'quejacon/:dato/:id', component: QuejaconComponent},
    // {path: 'quejaweb', component: QuejaextComponent}
];
var AppRoutingModule = /** @class */ (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = tslib_1.__decorate([
        NgModule({
            imports: [RouterModule.forRoot(routes, { useHash: true })],
            exports: [RouterModule]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());
export { AppRoutingModule };
//# sourceMappingURL=app-routing.module.js.map