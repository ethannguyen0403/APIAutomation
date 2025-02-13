package com.ethan.testcase;

import Model.Data.UserPOJO_Lombok_Builder;
import Model.RegisterUserPOJO_Lombok;
import com.ethan.global.ConfigsGlobal;
import com.ethan.global.TokenGlobal;
import com.google.gson.Gson;
import common.BaseTest;
import helpers.PropertiesHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Topic_10_Read_Properties extends BaseTest {
    @Test
    public void testReadFile() {
        //Gọi hàm loadAllFiles trước tiên để load tất cả các file properties vào chung bộ nhớ
        PropertiesHelper.loadAllFiles();

        //Sau đó gọi hàm getValue để lấy giá trị theo tên key
        System.out.println("URI: " + PropertiesHelper.getValue("URI"));
        System.out.println("USERNAME: " + PropertiesHelper.getValue("USERNAME"));
        System.out.println("PASSWORD: " + PropertiesHelper.getValue("PASSWORD"));
    }
    @Test
    public void testUpdateUser_PATCH() {

        RegisterUserPOJO_Lombok registerUserPOJO_lombok = UserPOJO_Lombok_Builder.getUserData();

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().patch("/user/43");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
