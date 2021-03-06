package thrillio.entities;

import java.util.Arrays;

import thrillio.constants.MovieGenre;

public class Movie extends Bookmark {
	private int releaseYear;
	private String[] cast;
	private String[] directors;
	private String genre;
	private double imbdRating;

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String[] getCast() {
		return cast;
	}

	public void setCast(String[] cast) {
		this.cast = cast;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getImbdRating() {
		return imbdRating;
	}

	public void setImbdRating(double imbdRating) {
		this.imbdRating = imbdRating;
	}

	@Override
	public String toString() {
		return "Movie [releaseYear=" + releaseYear + ", cast=" + Arrays.toString(cast) + ", directors="
				+ Arrays.toString(directors) + ", genre=" + genre + ", imbdRating=" + imbdRating + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if(genre.equals(MovieGenre.THRILLERS) || genre.equals(MovieGenre.HORROR)) {
			return false;
		}
		return true;
	}

}
