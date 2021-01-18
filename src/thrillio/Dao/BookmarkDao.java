package thrillio.Dao;

import thrillio.DataStore;
import thrillio.entities.Bookmark;
import thrillio.entities.UserBookmark;

public class BookmarkDao {

	public Bookmark[][] getBookmark() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
		
	}
	
}
