package thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import thrillio.managers.BookmarkManager;

class WebLinkTest {

	@Test
	void testIsKidFriendlyEligible() {

		// Test 1 : if Porn is in URL -- return false

		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com");

		Boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For porn present in URL, - isKidFriendlyEligible retrun false ", isKidFriendlyEligible);

		// Test 2 : porn is in title -- return false

		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger porn, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For porn present in title, - isKidFriendlyEligible retrun false ", isKidFriendlyEligible);

		// Test 3 : Adult in host -- return false

		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger , Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.adultjavaworld.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse("For adult present in host, - isKidFriendlyEligible retrun false ", isKidFriendlyEligible);

		// Test 4 : Adult in URL but not in host -- true

		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger , Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult-part-2.html",
				"http://www.javaworld.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue("For adult present in url not in host, - isKidFriendlyEligible retrun true ", isKidFriendlyEligible);

		// Test 5 : Adult in Title only -- true
		
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger adult, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue("For adult present in title, - isKidFriendlyEligible retrun true ", isKidFriendlyEligible);

	}

}
