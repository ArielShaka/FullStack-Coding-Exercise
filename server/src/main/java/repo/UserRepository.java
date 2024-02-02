package repo;

import model.User;

import java.io.IOException;
import java.util.List;

public interface UserRepository {

    List<User> getUsers() throws IOException;
}
