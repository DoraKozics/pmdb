import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {MovieListItemModel} from "../model/movie-list-item.model";
import {MovieDetailsModel} from "../model/movie-details.model";
import {FormInitDataModel} from "../model/form-init-data.model";
import {MovieFormModel} from "../model/movie-form.model";

@Injectable({providedIn: "root"})
export class MovieService {

  private BASE_URL: string = 'http://localhost:8080/api/movies';
  movieId?: number;
  resetForm = new Subject();

  constructor(private http: HttpClient) {
  }

  getMovies = (): Observable<MovieListItemModel[]> => {
    return this.http.get<MovieListItemModel[]>(this.BASE_URL);
  }

  getMovieById = (id: number): Observable<MovieDetailsModel> => {
    return this.http.get<MovieDetailsModel>(`${this.BASE_URL}/${id}`);
  }

  deleteMovie = (id: number): Observable<any> => {
    return this.http.delete(this.BASE_URL + '/' + id);
  }

  fetchFormInitData = (): Observable<FormInitDataModel> => {
    return this.http.get<FormInitDataModel>(this.BASE_URL + '/formData');
  }

  sendMovieData = (data: MovieFormModel): Observable<any> => {
    return this.http.post(this.BASE_URL, data);
  }

  updateMovieData = (data: MovieFormModel): Observable<any> => {
    return this.http.put(this.BASE_URL, data);
  }
}
