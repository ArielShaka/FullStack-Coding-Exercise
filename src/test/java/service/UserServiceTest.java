package service;

import repo.JsonUserRepository;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import util.MockedUser;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;


    @BeforeEach
    public void setUp() {

        JsonUserRepository userRepo = Mockito.mock(JsonUserRepository.class);

        when(userRepo.getUsers()).thenReturn(MockedUser.mockedListOfUsers());

        userService = new UserService(userRepo);

    }

    @Test
    public void testGetUsersWithReversedFirstNames()  {

        List<User> usersWithReversedFirstNames = userService.getUsersWithReversedFirstNames();
        System.out.println("usersWithReversedFirstNames = " + usersWithReversedFirstNames);

        Assertions.assertEquals("nhoj", usersWithReversedFirstNames.get(0).getFirstname());
        Assertions.assertEquals("ecila", usersWithReversedFirstNames.get(1).getFirstname());
    }

    @Test
    public void testIsPalindrome() {

        String palindrome = "level";
        Assertions.assertTrue(userService.isPalindrome(palindrome));

        String nonPalindrome = "hello";
        Assertions.assertFalse(userService.isPalindrome(nonPalindrome));
    }

    @Test
    public void testReverseString() {

        String textToReverse = "Car";
        String reversedText = userService.reverseString(textToReverse);

        Assertions.assertEquals("raC", reversedText);
    }

    @Test
    public void testFindNthLargestNumber() {

        List<Integer> numbers = Arrays.asList(10, 5, 8, 2, 15);
        int nthLargestNumber = 3;

        int response = userService.findNthLargestNumber(numbers, nthLargestNumber);

        assertEquals(8, (int) response);
    }

    @Test
    public void testAddZeroToNumber() {

        int number = 100;
        String paddedNumber = userService.addZeroToNumber(number);

        assertEquals("00100", paddedNumber);
    }
}
