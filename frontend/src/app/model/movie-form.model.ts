export interface MovieFormModel {
  title: string,
  director: string,
  year: number,
  genres: Array<string>;
  rating: string;
  posterUrl?: string;
}
