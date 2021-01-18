package thrillio.Dao;

import thrillio.DataStore;
import thrillio.entities.User;

public class UserDao {

	public User[] getUser() {
	     	return DataStore.getUsers();
	}
}
