package thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import thrillio.constants.MovieGenre;
import thrillio.managers.BookmarkManager;

class MovieTest {

	@Test
	void testIsKidFriendlyEligible() {
//		Test 1 : if movies comes under Horrer -- return false

		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		
		Boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();
		
		assertFalse("movies should not be in Horrer and Thriller",isKidFriendlyEligible);

//		Test 2 : if  movies comes under Triller -- return false
	}

}
