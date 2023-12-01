import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
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
    this.form = this.formBuilder.group({
      title: [null, Validators.required],
      director: [null, Validators.required],
      year: [null, [Validators.required, Validators.max(2023), Validators.min(1900)]],
      genres: [null, [Validators.required, this.customGenreValidator]],
      rating: [null, Validators.required],
      posterUrl: [null]
    })
  }

  ngOnInit(): void {
    this.movieService.fetchFormInitData().subscribe(
      value => {
        this.ratings = value.ratingList;
        this.genres = value.genreList;
      }
    );
  }

  onSubmitClick = () => {
    this.movieService.sendMovieData(this.form.value).subscribe(
      () => {this.router.navigate(['movies'])})
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
}
