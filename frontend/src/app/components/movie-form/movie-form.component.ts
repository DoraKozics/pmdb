import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {GenreOptionItemModel} from "../../model/genre-option-item.model";
import {RatingOptionItemModel} from "../../model/rating-option-item.model";
import {MovieService} from "../../service/movie.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.css']
})
export class MovieFormComponent implements OnInit {

  form!: FormGroup;
  genres: GenreOptionItemModel[] = [];
  ratings: RatingOptionItemModel[] = [];

  constructor(private formBuilder: FormBuilder,
              private movieService: MovieService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.movieService.fetchFormInitData().subscribe(
      value => {
        this.ratings = value.ratingList;
        this.genres = value.genreList;
        console.log(value)
      }
    );
  }

  onSubmitClick = () => {
    // this.movieService.createMovie();
    this.router.navigate(['movies']);
  }

}
