package service;

import repo.JsonUserRepository;
import model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserService {

    private final JsonUserRepository userRepo;

    public UserService(JsonUserRepository userMapper) {
        this.userRepo = userMapper;
    }

    public List<User> getUsersWithReversedFirstNames()  {

        List<User> users = userRepo.getUsers();
        if (users.isEmpty()) {
            throw new IllegalStateException("User data is empty");
        }

        return users.stream()
                .map(this::reverseUserFirstName)
                .collect(Collectors.toList());
    }

    private User reverseUserFirstName(User user) {

        String reversedFirstName = reverseString(user.getFirstname().toLowerCase());
        boolean isPalindrome = isPalindrome(user.getFirstname().toLowerCase());

        user.setFirstname(reversedFirstName);
        user.setTheFirstNamePalindrome(isPalindrome);

        return user;
    }

    public boolean isPalindrome(String str) {

        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        String strToLowerCases = str.toLowerCase();
        String reversedStr = reverseString(strToLowerCases);

        return strToLowerCases.equals(reversedStr);
    }

    public String reverseString(String input) {

        return Optional.ofNullable(input)
                .filter(s -> !s.isEmpty())
                .map(s -> new StringBuilder(s).reverse().toString())
                .orElseThrow(() -> new IllegalArgumentException("Input string cannot be null or empty"));
    }

    public int findNthLargestNumber(List<Integer> numbers, int n) {

        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty");
        }

        if (n <= 0 || n > numbers.size()) {
            throw new IllegalArgumentException("Invalid value for n");
        }

        List<Integer> distinctSortedNumbers = numbers.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return distinctSortedNumbers.get(numbers.size() - n);
    }

    public String addZeroToNumber(int number) {

        if (number < 0 || number > 99999) {
            throw new IllegalArgumentException("Input number must be between 0 and 99999 (inclusive)");
        }
        return String.format("%05d", number);
    }

}
