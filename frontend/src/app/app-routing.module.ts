import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MovieListComponent} from "./movie-list/movie-list.component";
import {MovieFormComponent} from "./movie-form/movie-form.component";

const routes: Routes = [
  {path: '', component: MovieListComponent},
  {path: 'movies/:id', component: MovieListComponent},
  {path: 'movies', component: MovieListComponent},
  {path: 'form', component: MovieFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
