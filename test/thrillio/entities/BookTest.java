package thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import thrillio.constants.BookGenre;
import thrillio.managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		
		// Test 1 : Book is  Philoshy -- return false
		
	    Book book =	BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
	    
	    Boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
	    
	    assertFalse("Book should not be in Philosphy genre",isKidFriendlyEligible);
	    
		// Test 2 : Book is Self Help -- return false
	}

}
