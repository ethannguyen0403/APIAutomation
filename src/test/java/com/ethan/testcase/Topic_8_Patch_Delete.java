package com.ethan.testcase;

import Model.LoginPOJO;
import Model.PatchUserPOJO;
import com.ethan.global.TokenGlobal;
import com.google.gson.Gson;
import common.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Topic_8_Patch_Delete extends BaseTest {
    @Test
    public void Demo_Patch_Delete_001(){
        //Chuẩn bị data cho edit user
        PatchUserPOJO patchUserPOJO = new PatchUserPOJO();
        patchUserPOJO.setFirstName("Bối");
        patchUserPOJO.setLastName("Bối");
        patchUserPOJO.setEmail("boiboi1@email.com");
        patchUserPOJO.setPhone("0123456789");
        patchUserPOJO.setUserStatus(1);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(patchUserPOJO));

        Response response = request.when().patch("/user/43");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }

    @Test
    public void Demo_Patch_Delete_002(){
        //Chuẩn bị thông tin username để delete
        String username = "";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .queryParam("username", username);

        Response response = request.when().delete("/user");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
