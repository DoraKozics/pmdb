import {Component, OnInit} from '@angular/core';
import {MovieService} from "../service/movie.service";
import {MovieListItemModel} from "../model/movie-list-item.model";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {

  movies: MovieListItemModel[] = [];

  constructor(private movieService: MovieService) {
  }

  ngOnInit() {
    this.movieService.getMovies().subscribe({
      next: value => this.movies = value
    })
  }

}
