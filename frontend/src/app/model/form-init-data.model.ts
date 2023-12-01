import {GenreOptionItemModel} from "./genre-option-item.model";
import {RatingOptionItemModel} from "./rating-option-item.model";

export interface FormInitDataModel {
  genreList: GenreOptionItemModel[];
  ratingList: RatingOptionItemModel[];
}
