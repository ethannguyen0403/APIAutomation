package com.ethan.testcase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Topic_4_DemoVerify {
    @Test
    public void Verify_Then_Or_Assert_001() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api").accept("application/json");

        int id = 1;

        Response response = request.when().get("/book/"+ id);
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().contentType("application/json");

        response.then().body("response.name", containsString("Phương Nam"));
        response.then().body("response.price", equalTo(12000));
        response.then().body("response.image[0].path", containsString("public/images/1avh0VncWc"));
    }

    @Test
    public void Verify_Then_Or_Assert_002() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api").accept("application/json");

        int id = 1;

        Response response = request.when().get(String.format("/book/%d", id));
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code is incorrect");
        Assert.assertEquals(response.getContentType(), "application/json", "Content Type is incorrect.");

        ResponseBody body = response.getBody();
        Assert.assertTrue(body.asString().contains("Success"), "Message Success is not exist.");
    }

    @Test
    public void Verify_Then_Or_Assert_003() {
        RequestSpecification request = given();
        request.baseUri("https://restful-booker.herokuapp.com").accept("application/json");

        int id = 1;

        Response response = request.when().get(String.format("/booking/%d", id));
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code is incorrect");
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8", "Content Type is incorrect.");

        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.getString("firstname");
        Assert.assertTrue(name.contains("Jim"), "Name is not exist");
    }
}
