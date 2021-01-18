package thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.hamcrest.core.IsInstanceOf;

import thrillio.Dao.BookmarkDao;
import thrillio.entities.Book;
import thrillio.entities.Bookmark;
import thrillio.entities.Movie;
import thrillio.entities.User;
import thrillio.entities.UserBookmark;
import thrillio.entities.WebLink;
import thrillio.utils.HttpConnect;
import thrillio.utils.IOUtils;

public class BookmarkManager {

	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profilUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imbdRating) {

		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfilUrl(profilUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImbdRating(imbdRating);

		return movie;

	}

	public Book createBook(long id, String title, String profilUrl, int publicationYear, String publisher,
			String[] author, String genre, double amazonRating) {

		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setProfilUrl(profilUrl);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthor(author);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public WebLink createWebLink(long id, String title, String url, String host) {

		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);
		return weblink;

	}

	public Bookmark[][] getBookmark() {
		return dao.getBookmark();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) throws URISyntaxException {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);

		if (bookmark instanceof WebLink) {
			try {
				String url = ((WebLink) bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((WebLink) bookmark).getUrl());
					if (webpage != null) {
						IOUtils.write(webpage, bookmark.getId());
					}
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();

			}
		}

		dao.saveUserBookmark(userBookmark);

	}

	public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		System.out.println(
				"kid friedly status: " + kidFriendlyStatus + " ,  Marked By " + user.getEmail() + " " + bookmark);

	}

	public void share(User user, Bookmark bookmark) {

		bookmark.setSharedBy(user);
		System.out.println("Data is to be Shared");

		if (bookmark instanceof Book) {

			System.out.println(((Book) bookmark).getItemData());
		} else if (bookmark instanceof WebLink) {
			System.out.println(((WebLink) bookmark).getItemData());
		}
	}

}
