package util;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class MockedUser {
   private static final User user1 = new User(1, "John", "Doe", "johndoe", 30);
   private static final User user2 = new User(2, "Alice", "Smith", "alice_smith", 25);
   private static final User user3 = new User(2, "Ariel", "Shaka", "alice_smith", 25);

    public static List<User> mockedListOfUsers() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }
}
