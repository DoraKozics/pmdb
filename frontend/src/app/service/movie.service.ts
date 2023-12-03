import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {MovieListItemModel} from "../model/movie-list-item.model";
import {MovieDetailsModel} from "../model/movie-details.model";
import {FormInitDataModel} from "../model/form-init-data.model";
import {MovieFormModel} from "../model/movie-form.model";

const BASE_URL: string = 'http://localhost:8080/api/movies';

@Injectable({providedIn: "root"})
export class MovieService {

  movieId?: number;
  resetForm = new Subject();

  constructor(private http: HttpClient) {
  }

  getMovies = (): Observable<MovieListItemModel[]> => {
    return this.http.get<MovieListItemModel[]>(BASE_URL);
  }

  getMovieById = (id: number): Observable<MovieDetailsModel> => {
    return this.http.get<MovieDetailsModel>(`${BASE_URL}/${id}`);
  }

  deleteMovie = (id: number): Observable<any> => {
    return this.http.delete(BASE_URL + '/' + id);
  }

  fetchFormInitData = (): Observable<FormInitDataModel> => {
    return this.http.get<FormInitDataModel>(BASE_URL + '/formData');
  }

  sendMovieData = (data: MovieFormModel): Observable<any> => {
    return this.http.post(BASE_URL, data);
  }

  updateMovieData = (data: MovieFormModel): Observable<any> => {
    return this.http.put(BASE_URL, data);
  }
}
