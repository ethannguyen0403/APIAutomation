package com.ethan.testcase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Topic_5_DemoPostMethod {
    @Test
    public void Demo_Login() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \"anhtester\",\n" +
                        "  \"password\": \"Demo@123\"\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);
    }

    @Test
    public void Demo_Register() {
        String username = "ethanTest07";
        String email = "ethantester07@email.com";
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(String.format("{\n" +
                                "  \"username\": \"%s\",\n" +
                                "  \"firstName\": \"Ethan\",\n" +
                                "  \"lastName\": \"Tester\",\n" +
                                "  \"email\": \"%s\",\n" +
                                "  \"password\": \"Demo@123\",\n" +
                                "  \"phone\": \"0123456789\",\n" +
                                "  \"userStatus\": 1\n" +
                                "}",
                        username, email));

        Response response = request.when().post("/register");
        response.prettyPrint();

        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(jsonPath.get("response.username").equals(username), "Username is not correct");
        Assert.assertTrue(jsonPath.get("response.firstName").equals("Ethan"), "firstName is not correct");
        Assert.assertTrue(jsonPath.get("response.lastName").equals("Tester"), "lastName is not correct");
        Assert.assertTrue(jsonPath.get("response.email").equals(email), "email is not correct");
        Assert.assertTrue(jsonPath.get("response.phone").equals("0123456789"), "phone is not correct");
        Assert.assertTrue(jsonPath.get("response.userStatus").equals(1), "userStatus is not correct");
        Integer idUser = jsonPath.get("response.id");
        Assert.assertTrue(idUser > 0, "The ID is not exist");
    }
}
