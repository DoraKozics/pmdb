import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {GenreOptionItemModel} from "../../model/genre-option-item.model";
import {RatingOptionItemModel} from "../../model/rating-option-item.model";
import {MovieService} from "../../service/movie.service";
import {Router} from "@angular/router";
import {MovieFormModel} from "../../model/movie-form.model";

@Component({
  selector: 'app-movie-form',
  templateUrl: './movie-form.component.html',
  styleUrls: ['./movie-form.component.css']
})
export class MovieFormComponent implements OnInit {

  form!: FormGroup;
  genres: GenreOptionItemModel[] = [];
  ratings: RatingOptionItemModel[] = [];
  movieId: number | undefined;

  constructor(private formBuilder: FormBuilder,
              private movieService: MovieService,
              private router: Router) {

    this.form = this.formBuilder.group({
      title: [null, Validators.required],
      director: [null, Validators.required],
      year: [null, [Validators.required, Validators.max(2023), Validators.min(1900)]],
      genres: [null, [Validators.required, this.customGenreValidator]],
      rating: [null, Validators.required],
      posterUrl: [null]
    })

    this.movieService.resetForm.subscribe(
      () => {
        this.movieId = undefined;
        this.form.reset();
      },
    );
  }

  ngOnInit(): void {
    this.movieService.fetchFormInitData().subscribe(
      value => {
        this.ratings = value.ratingList;
        this.genres = value.genreList;
        this.movieId = this.movieService.movieId;

        if (this.movieId) {
          this.getMovieDetails(this.movieId);
        }
      }
    )
  }

  getMovieDetails = (id: number) => {
    this.movieService.getMovieById(id).subscribe(
      (response) => {
        if (response) {
          this.form.patchValue({
            title: response.title,
            director: response.director,
            year: response.year,
            genres: response.genres,
            rating: response.rating,
            posterUrl: response.posterUrl
          });
        }
      })
  }

  customGenreValidator = (control: FormControl): { tooMany: boolean } | null => {
    let result = null;
    if (control.value) {
      let selectedValues: string[] = control.value;
      if (selectedValues.length > 3) {
        result = {tooMany: true};
      }
    }
    return result;
  }

  onSubmitClick = () => {
    const data = {...this.form.value};
    if (this.movieId) {
      this.updateMovieData(data, this.movieId);
    } else {
      this.createMovie(data);
    }
  }

  createMovie(data: MovieFormModel) {
    this.movieService.sendMovieData(data).subscribe(
      () => {
        this.router.navigate(['movies'])
      })
  }

  updateMovieData(data: MovieFormModel, movieId: number) {
    data.id = movieId;
    this.movieService.updateMovieData(data).subscribe(
      () => {
        this.router.navigate(['movies']);
      }
    )
  }
}
