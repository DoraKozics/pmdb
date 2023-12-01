export interface MovieFormModel {
  id?: number,
  title: string,
  director: string,
  year: number,
  genres: Array<string>;
  rating: string;
  posterUrl?: string;
}
