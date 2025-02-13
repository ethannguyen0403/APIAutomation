package com.ethan.testcase;

import com.ethan.global.ConfigsGlobal;
import com.ethan.global.TokenGlobal;
import common.BaseTest;
import helpers.SystemHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Topic_13_Practice_Category extends BaseTest {
    int idCategory;
    @Test(priority = 1)
    public void Test_Add_New_Category() {
        String dataFile = "src/test/resources/testdata/CreateCategory.json";
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_JSON)
                .contentType(ConfigsGlobal.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response response = request.post("/category");
        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        idCategory = Integer.parseInt(jsonPath.get("response.id").toString());

    }

    @Test(priority = 2)
    public void Test_Get_New_Category() {
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_JSON)
                .contentType(ConfigsGlobal.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        Response response = request.get(String.format("/category/%s",idCategory));
        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"),"Ethan Testing Category","Failed! Category Name display incorrect.");
    }
    @Test(priority = 3)
    public void Test_Update_Category() {
        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_JSON)
                .contentType(ConfigsGlobal.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body("{\n" +
                        "  \"name\": \"Ethan Testing Category Updated\"\n" +
                        "}");

        Response response = request.put(String.format("/category/%s",idCategory));
        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"),"Ethan Testing Category Updated","Failed! Category Name display incorrect.");
    }
}
