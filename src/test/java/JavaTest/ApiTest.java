package JavaTest;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;
//In api testing we always first check the status code then we check the body.
public class ApiTest {
    String baseUri="http://www.dummy.restapiexample.com/";
    @Test
    public void testApi()
    {
    Response response= given().baseUri(baseUri).when().get("api/v1/employees11").andReturn();
    String body=response.asString();
    //Assertion for status code
      int statusCodeActual=response.getStatusCode();
      int statusCodeExpected=200;
      //Assertion1
      Assert.assertEquals(statusCodeActual,statusCodeExpected);
      //Asserton2
      if(body.contains("success"))
      {
          System.out.println("TestCase Passed");
          Assert.assertTrue("Tc passed",true);
      }
      else {
          System.out.println("TestCase Failed");
          Assert.assertTrue("Tc fail",false);
      }

    }
}
