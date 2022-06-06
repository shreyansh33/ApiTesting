package TrialTest;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ApiTest1 {

    String baseUri="http://www.dummy.restapiexample.com/";
    Map<String,String> mapvalue=new HashMap<String,String>();

    @Test
    public void testcase_01()
    {
     /* Method1:---
      Response response= given().baseUri(baseUri).when().get("api/v1/employees").andReturn();
      */
        given().baseUri(baseUri).when().get("api/v1/employees").then().assertThat().statusCode(200).assertThat().body("status",equalTo("success")).assertThat().body("data[0].id",equalTo(1)).assertThat().body("data[0].employee_name",equalTo("Tiger Nixon"));


    }
    @Test
    public void testcase_02_breakingSteps()
    {
        RequestSpecification requestSpecification=given().baseUri(baseUri);
        Response response= requestSpecification.when().get("api/v1/employees");
        ValidatableResponse validatableResponse=response.then();
        validatableResponse.assertThat().statusCode(200).assertThat().body("status",equalTo("success")).assertThat().body("data[0].id",equalTo(1));



    }
    @Test
    public void testcase_03_getRequest_getSingleEmployeeDetail()
    {
      RequestSpecification requestSpecification= given().baseUri(baseUri);
      Response response= requestSpecification.when().get("api/v1/employee/1");
           ValidatableResponse validatableResponse=response.then();
                validatableResponse.assertThat().statusCode(200).assertThat().body("status",equalTo("success")).assertThat().body("data[0].id",equalTo(1)).assertThat().body( "data[0].employee_name",equalTo("Tiger Nixon"));
    }
    @Test
    public void testcase_03_getRequest_getIncorrectSingleEmployeeDetail()
    {
        RequestSpecification requestSpecification= given().baseUri(baseUri);
        Response response= requestSpecification.when().get("api/v1/employee/111");
        ValidatableResponse validatableResponse=response.then();
        validatableResponse.assertThat().statusCode(200).assertThat().body("status",equalTo("success")).assertThat().body("data[0].id",equalTo(1)).assertThat().body( "data[0].employee_name",equalTo("Tiger Nixon"));

    }
    @Test
    public void testcase_04_postRequest()
    {
        String dynamic_name=getRandomString(8);

        mapvalue.put("Content-Type","application/json");
      RequestSpecification requestSpecification=given().baseUri(baseUri).headers(mapvalue);
      String s="{\"name\":\""+dynamic_name+"\",\"salary\":\"123\",\"age\":\"23\"}";
        System.out.println(s);
    Response response=requestSpecification.when().body(s).post(	"api/v1/create");
             ValidatableResponse validatableResponse=response.then();
             validatableResponse.assertThat().statusCode(200).assertThat().body("status",equalTo("success"));
    int id=response.jsonPath().getInt("data.id");
        System.out.println(id);
    String salary=response.jsonPath().getString("data.salary");
    //Map<String,String> hm_data=response.jsonPath().getMap("data");

        RequestSpecification requestSpecification1=given().baseUri(baseUri);
        Response response1=requestSpecification.when().get("api/v1/employee/"+id);
        String responsevalue=response1.jsonPath().get("status");
        System.out.println(responsevalue);
        ValidatableResponse validatableResponse1=response1.then();
        validatableResponse.assertThat().statusCode(200).assertThat().body("status",equalTo("success")).assertThat().body("data.id",equalTo(id));
    }
    //on the value of n t will generate the string of that many characters.
    public  String getRandomString(int n)
    {
        int lowerLimit=97;
        int upperLimit=122;
        Random random=new Random();
        //Create a String Buffer to store the result
        StringBuffer stringBuffer=new StringBuffer(n);
        for(int i=0;i<n;i++)
        {
            int nextRandomCharacter=lowerLimit+(int)(random.nextFloat()*(upperLimit-lowerLimit+1));
            stringBuffer.append((char) nextRandomCharacter);
        }
        return stringBuffer.toString();
    }
    @Test
    public void testcase_06_postRequest_negative()
    {

    }
}

