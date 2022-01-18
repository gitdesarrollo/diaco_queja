import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import { QuejaconComponent } from './quejacon/quejacon.component';
import { QuejaextComponent } from './quejaext/quejaext.component';
import { QuejainiComponent } from './quejaini/quejaini.component';

const routes: Routes = [
    // {path: 'quejaini', loadChildren: './quejaini/quejaini.component', data: {preload:true} },
    // {path: 'quejacon/:dato/:id', loadChildren: './quejacon/quejacon.component', data: {preload:true} },
    // {path: 'quejaweb', loadChildren: './quejaext/quejaext.component', data: {preload:true}}
	{path: 'quejaini', component: QuejainiComponent},
	{path: 'quejacon/:dato/:id', component: QuejaconComponent},
	{path: 'quejaweb', component: QuejaextComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
