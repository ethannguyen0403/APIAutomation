package com.ethan.testcase;

import Model.PatchUserPOJO;
import Model.RegisterUserPOJO_Lombok;
import com.ethan.global.TokenGlobal;
import com.google.gson.Gson;
import common.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Topic_9_Lombok_DataFaker extends BaseTest {
    @Test
    public void Demo_Lombok_DataFake_001(){
        Faker dataFaker = new Faker(new Locale("vi"));

        RegisterUserPOJO_Lombok registerUserPOJOLombok = new RegisterUserPOJO_Lombok();
        registerUserPOJOLombok.setFirstName(dataFaker.name().firstName());
        registerUserPOJOLombok.setLastName(dataFaker.name().lastName());
        registerUserPOJOLombok.setEmail(dataFaker.internet().emailAddress());
        registerUserPOJOLombok.setPhone(dataFaker.phoneNumber().cellPhone().replace(" ",""));
        registerUserPOJOLombok.setUserStatus(1);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJOLombok));

        Response response = request.when().patch("/user/43");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }

}
