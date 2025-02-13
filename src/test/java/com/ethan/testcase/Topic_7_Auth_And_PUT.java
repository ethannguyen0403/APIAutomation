package com.ethan.testcase;

import Model.LoginPOJO;
import Model.RegisterUserPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Topic_7_Auth_And_PUT {

    //Khai báo biến toàn tục TOKEN để lưu trữ từ hàm Login
    String TOKEN = "";

    @BeforeMethod
    public void loginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("anhtester", "Demo@123");

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TOKEN = response.getBody().path("token");
        System.out.println(TOKEN);
    }

    @Test
    public void Demo_Auth_001() {
        RequestSpecification httpRequest = given().auth().basic("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void Demo_Auth_002() {
        RegisterUserPOJO registerUserPOJO = new RegisterUserPOJO();
        registerUserPOJO.setUsername("myduyen7");
        registerUserPOJO.setPassword("Demo@123");
        registerUserPOJO.setFirstName("Lê Thị");
        registerUserPOJO.setLastName("Mỹ Duyên");
        registerUserPOJO.setEmail("myduyen7@email.com");
        registerUserPOJO.setPhone("0123456789");
        registerUserPOJO.setUserStatus(1);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(gson.toJson(registerUserPOJO));

        Response response = request.when().put("/user/43");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
