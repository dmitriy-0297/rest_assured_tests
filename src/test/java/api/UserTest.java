package api;

import api.user.UserService;
import api.user.responses.UserData;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class UserTest {
    private final static String URL = "https://reqres.in/";

    // Проверить, что все домены emails соответствуют '@reqres.in'
    @Test
    public void checkUserEmailTest(){
        UserService userService = new UserService(URL);
        List <UserData> users = userService.getUserList();

        final String expectedEmailDomain = "@reqres.in";
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(users.stream().allMatch(v -> v.getEmail().endsWith(expectedEmailDomain)))
                    .withFailMessage("Не все домены 'email' принадлежат домену " + expectedEmailDomain)
                    .isTrue();
        });
    }
}
