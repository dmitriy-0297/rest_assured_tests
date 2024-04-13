import framework.services.UserService.UserService;
import framework.services.UserService.requests.UserData;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserTest {
    private final static String URL = "https://reqres.in/";

    // Check that all email domains match '@reqres.in'
    @Test
    public void checkUsersDomainEmailsTest(){
        UserService userService = new UserService();
        List<UserData> users = userService.getUserList();

        final String expectedEmailDomain = "@reqres.in";
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(users.stream().allMatch(v -> v.getEmail().endsWith(expectedEmailDomain)))
                    .withFailMessage("Not all email domains belong to a domain " + expectedEmailDomain)
                    .isTrue();
        });
    }
}
