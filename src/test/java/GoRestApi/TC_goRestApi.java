package GoRestApi;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class TC_goRestApi {
    String baseUri = "https://gorest.co.in/";
    String accessToken="2d4052c48105cf94434fd26fccf01b54e7fad49102f97c737066d6c191c4a8ed";

    @Test
    public void get_tc_01() {
        RequestSpecification requestSpecification = given().baseUri(baseUri).auth().oauth2(accessToken);
        Response response = requestSpecification.when().get("public/v2/users");
        int id=response.jsonPath().getInt("id[0]");
        System.out.println(id);
        System.out.println(response.asString());
        ValidatableResponse validatableResponse= response.then();

        //validatableResponse.statusCode(200).body("id[0]",equalTo(3828));
    validatableResponse.assertThat().statusCode(200).assertThat().body("id[0]",equalTo(id)).assertThat().body("gender[0]",equalTo("female"));
    }

}
