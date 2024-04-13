package api.user;

import api.Specifications;
import api.user.responses.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserService {
    private final String URL;
    public final static String schemaUserData = "schemas/userDataSchema.json";
    public UserService(String url){
        this.URL = url;
    }
    public List<UserData> getUserList(){
        Specifications.initSpecification(Specifications.requestSpec(URL), Specifications.responseSpec200(schemaUserData));
        return given()
                .when()
                .get( "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
    }
}
