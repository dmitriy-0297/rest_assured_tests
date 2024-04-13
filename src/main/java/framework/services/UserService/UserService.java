package framework.services.UserService;

import framework.ReaderProperties;
import framework.Specifications;
import framework.services.UserService.requests.UserData;
import io.restassured.RestAssured;

import java.util.List;

public class UserService {
    private final String URL = ReaderProperties.getInstance().getValue("url");
    private final static String schemaUserData = "schemas/userDataSchema.json";

    public List<UserData> getUserList(){
        Specifications.initSpecification(Specifications.requestSpec(URL), Specifications.responseSpec200(schemaUserData));
        return RestAssured.given().log().all()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
    }
}
