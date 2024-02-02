package repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import util.UserJsonHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonUserRepository  implements UserRepository {

    private final UserJsonHandler userJsonHandler;

    private static final Logger logger = Logger.getLogger(JsonUserRepository.class.getName());


    public JsonUserRepository(UserJsonHandler userJsonHandler) {
        this.userJsonHandler = userJsonHandler;
    }

    @Override
    public List<User> getUsers()  {
        String jsonString = userJsonHandler.getUsers();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            User[] usersArray = objectMapper.readValue(jsonString, User[].class);

            return Arrays.asList(usersArray);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred while mapping JSON to User object", e);
        }
        return Collections.emptyList();
    }

}
