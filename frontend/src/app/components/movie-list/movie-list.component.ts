import {Component, OnInit} from '@angular/core';
import {MovieService} from "../../service/movie.service";
import {MovieListItemModel} from "../../model/movie-list-item.model";
import {Router} from "@angular/router";
import {Subject} from "rxjs";
import {MovieDetailsModel} from "../../model/movie-details.model";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies: MovieListItemModel[] = [];

  constructor(private movieService: MovieService, private router: Router) {
  }

  ngOnInit() {
    this.movieService.getMovies().subscribe({
      next: value => this.movies = value
    })
  }

  onDetailsClick = (id: number) => {
    this.router.navigate(['details/' + id]);
  }

  onDeleteClick = (id: number) => {
    this.movieService.deleteMovie(id).subscribe({
      next: () => location.reload()
    });
  }

  onEditClick = (id: number) => {
    this.movieService.movieId = id;
    this.router.navigate(['form']);
  }
}
