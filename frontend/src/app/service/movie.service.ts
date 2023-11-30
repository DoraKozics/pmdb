import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MovieListItemModel} from "../model/movie-list-item.model";
import {MovieDetailsModel} from "../model/movie-details.model";

@Injectable({providedIn: "root"})
export class MovieService {

  private BASE_URL: string = 'http://localhost:8080/api/movies';

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
}
