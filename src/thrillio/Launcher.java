package thrillio;

import thrillio.entities.Bookmark;
import thrillio.entities.User;
import thrillio.managers.BookmarkManager;
import thrillio.managers.UserManager;

public class Launcher {

	public static User[] users;
	public static Bookmark[][] bookmarks;

	private static void loadData() {
		System.out.println("1. Loading Data........");
		DataStore.loadData();

		users = UserManager.getInstance().getUser();
		bookmarks = BookmarkManager.getInstance().getBookmark();

	     //	System.out.println("Printing Data.........");
		// printUserData();
		// printBookmarkData();

	}

	private static void printBookmarkData() {

		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}

	}

	private static void printUserData() {
		for (User user : users)
			System.out.println(user);
	}

	private static void start() {
	//	System.out.println("\n2. Bookmarking..................");
		for (User user : users) {
			View.browse(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		start();
	}

}
