package thrillio;

import thrillio.constants.KidFriendlyStatus;
import thrillio.constants.UserType;
import thrillio.controllers.BookmarkController;
import thrillio.entities.Bookmark;
import thrillio.entities.User;
import thrillio.partner.Shareable;

public class View {

	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + "is Browsing items........");
		int bookmarkCount = 0;

		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking!.....
				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					Boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						bookmarkCount++;

						BookmarkController.getInstance().saveUserBookmark(user, bookmark);

						System.out.println("new item Bookmarked ....  " + bookmark);
					}
				}

				if (user.getUserType().equals(UserType.CHIEF_EDITOR) || user.getUserType().equals(UserType.EDITOR)) {

					// marking as Kid friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
						if (kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}
					}

					// Sharing !!!
					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
							&& bookmark instanceof Shareable) {
                       boolean isShare = getShareDecision();
                       if(isShare) {
                    	   BookmarkController.getInstance().share(user, bookmark);
                       }
					}
				}

			}
		}

		/*
		 * public static void bookmark(User user, Bookmark[][] bookmarks) {
		 * System.out.println("\n" + user.getEmail() + "is Bookmarking....."); for (int
		 * i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) { int typeOffset = (int)
		 * Math.random()*DataStore.BOOKMARKS_TYPES_COUNT; int bookmarkOffset = (int)
		 * Math.random()*DataStore.BOOKMARK_COUNT_PER_TYPE;
		 * 
		 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
		 * 
		 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
		 * 
		 * System.out.println(bookmark); } }
		 */
	}

	private static Boolean getShareDecision() {
		return Math.random() < 0.5 ? true :false;
		
	}

	private static String getKidFriendlyStatusDecision(Bookmark bookmark) {
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: (Math.random() > 0.4 && Math.random() < 0.8) ? KidFriendlyStatus.REJECT : KidFriendlyStatus.UNKNOWN;

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;

	}
}