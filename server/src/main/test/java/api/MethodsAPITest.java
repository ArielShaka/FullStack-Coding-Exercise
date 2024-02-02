package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import service.UserService;
import util.MockedUser;

import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class MethodsAPITest {
    @Mock
    private UserService userService;

    private MethodsAPI methodsAPI;

    @BeforeEach
    public void setUp() {

        userService = mock(UserService.class);
        methodsAPI = new MethodsAPI();
        methodsAPI.userService = userService;
    }

    @Test
    public void testUsersWithReversedNames() {

        when(userService.getUsersWithReversedFirstNames()).thenReturn(MockedUser.mockedListOfUsers());

        Response response = methodsAPI.usersWithReversedNames();

        assertEquals(200, response.getStatus());
        assertNotNull(response);
    }

    @Test
    public void testReverseString() {

        when(userService.reverseString("hello")).thenReturn("olleh");

        Response response = methodsAPI.reverseString("hello");

        assertEquals(200, response.getStatus());
        assertEquals("olleh", response.getEntity());
    }

    @Test
    public void testIsPalindrome() {

        when(userService.isPalindrome("level")).thenReturn(true);

        Response response = methodsAPI.isPalindrome("level");

        assertEquals(200, response.getStatus());
        assertEquals(true, response.getEntity());
    }

    @Test
    public void testIsPalindromeNonPalindrome() {

        when(userService.isPalindrome("hello")).thenReturn(false);

        Response response = methodsAPI.isPalindrome("hello");

        assertEquals(200, response.getStatus());
        assertEquals(false, response.getEntity());
    }

    @Test
    public void testPaddnumberwithzeroes() {

        when(userService.addZeroToNumber(100)).thenReturn("00100");

        Response response = methodsAPI.padNumberWithZeroes(100);

        assertEquals(200, response.getStatus());
        assertEquals("00100", response.getEntity());
    }

    @Test
    public void testPadNumberWithZeroesInvalidNumber() {

        when(userService.addZeroToNumber(100000)).thenThrow(new IllegalArgumentException("Input number must be between 0 and 99999 (inclusive)"));

        Response response = methodsAPI.padNumberWithZeroes(100000);

        assertEquals(400, response.getStatus());
        assertEquals("Input number must be between 0 and 99999 (inclusive)", response.getEntity());
    }

    @Test
    public void testFindNthLargestNumberValidInput() {

        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
        int nthLargestNumber = 2;
        int expected = 7;

        when(userService.findNthLargestNumber(numbers, nthLargestNumber)).thenReturn(expected);

        Response response = methodsAPI.findNthLargestNumber(numbers, nthLargestNumber);

        assertEquals(200, response.getStatus());
        assertEquals(expected, response.getEntity());
    }

    @Test
    public void testFindNthLargestNumberInvalidInput() {

        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
        int nthLargestNumber = 10;

        when(userService.findNthLargestNumber(numbers, nthLargestNumber))
                .thenThrow(new IllegalArgumentException("Invalid value for n"));

        Response response = methodsAPI.findNthLargestNumber(numbers, nthLargestNumber);

        assertEquals(400, response.getStatus());
        assertEquals("Invalid value for n", response.getEntity());
    }

}
