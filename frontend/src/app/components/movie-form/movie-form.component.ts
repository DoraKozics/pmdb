import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {GenreOptionItemModel} from "../../model/genre-option-item.model";
import {RatingOptionItemModel} from "../../model/rating-option-item.model";
import {MovieService} from "../../service/movie.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MovieDetailsModel} from "../../model/movie-details.model";

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
  detailsToUpdate: MovieDetailsModel | null = null;

  constructor(private formBuilder: FormBuilder,
              private movieService: MovieService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

    this.form = this.formBuilder.group({
      title: [null, Validators.required],
      director: [null, Validators.required],
      year: [null, [Validators.required, Validators.max(2023), Validators.min(1900)]],
      genres: [null, [Validators.required, this.customGenreValidator]],
      rating: [null, Validators.required],
      posterUrl: [null]
    })

    this.activatedRoute.params.subscribe(() => {
      this.movieId = this.movieService.movieId;
    })
  }

  ngOnInit(): void {
    this.movieService.fetchFormInitData().subscribe(
      value => {
        this.ratings = value.ratingList;
        this.genres = value.genreList;

        if (this.movieId) {
          this.movieService.getMovieById(this.movieId).subscribe((dataToUpdate) => {
            this.detailsToUpdate = {
              id: dataToUpdate.id,
              title: dataToUpdate.title,
              director: dataToUpdate.director,
              year: dataToUpdate.year,
              genres: dataToUpdate.genres,
              rating: dataToUpdate.rating,
              posterUrl: dataToUpdate.rating
            };

            this.form.patchValue({
              title: [dataToUpdate.title],
              director: [dataToUpdate.director],
              year: [dataToUpdate.year],
              genres: [dataToUpdate.genres],
              rating: [dataToUpdate.rating],
              posterUrl: [dataToUpdate.posterUrl]
            });

            this.movieService.movieId = undefined;
            this.movieId = undefined;
          })
        }
      }
    )
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
    this.movieService.sendMovieData(this.form.value).subscribe(
      () => {
        this.router.navigate(['movies'])
      })
  }

  onSubmitChangesClick() {
    this.movieService.updateMovieData(this.form.value).subscribe(
      (value) => {
        console.log(value)
        this.router.navigate(['movies']);
        this.form.reset();
      }
    )
  }
}
