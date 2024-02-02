package api;

import repo.JsonUserRepository;
import service.UserService;
import util.UserJsonHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("methods")
public class MethodsAPI {

    public static UserService userService;

    public MethodsAPI() {
        userService = new UserService(new JsonUserRepository(new UserJsonHandler()));
    }


    @GET
    @Path("/userswithreversednames")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response usersWithReversedNames() {
        try {
            return Response.ok(userService.getUsersWithReversedFirstNames()).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/reversestring")
    public static Response reverseString(@FormParam("string") String string) {
        try {
            return Response.ok(userService.reverseString(string)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/ispalindrome")
    public static Response isPalindrome(@FormParam("string") String string) {
        try {
            return Response.ok(userService.isPalindrome(string)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/padnumberwithzeroes")
    public static Response padNumberWithZeroes(@FormParam("number") int number) {
        try {
            return Response.ok(userService.addZeroToNumber(number)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }

    }

    @POST
    @Path("/findnthlargestnumber")
    public static Response findNthLargestNumber(@FormParam("numbers") List<Integer> numbers,
                                                @FormParam("nthlargestnumber") int nthLargestNumber) {

        try {
            return Response.ok(userService.findNthLargestNumber(numbers, nthLargestNumber)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
