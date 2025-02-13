package com.ethan.testcase;

import Model.Data.UserPOJO_Lombok_Builder;
import Model.RegisterUserPOJO_Lombok;
import com.ethan.global.ConfigsGlobal;
import com.ethan.global.TokenGlobal;
import com.google.gson.Gson;
import common.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class Topic_12_Validate_Json_schema extends BaseTest {
    @Test
    public void validateJsonSchema_GetBookById() {
        InputStream GetBookIdSchema = getClass().getClassLoader()
                .getResourceAsStream("GetBookIdSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.URI)
                .when()
                .get("/book/11")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookIdSchema));
    }

    @Test
    public void validateJsonSchema_GetAllBook() {
        InputStream GetBookAllSchema = getClass().getClassLoader()
                .getResourceAsStream("GetBookAllSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.URI)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookAllSchema));
    }

    @Test
    public void validateJsonSchema_CreateUser() {
        InputStream CreateUserSchema = getClass().getClassLoader()
                .getResourceAsStream("CreateUserSchema.json");

        RegisterUserPOJO_Lombok registerUserPOJO_lombok = UserPOJO_Lombok_Builder.getUserDataCreate();

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().post("/user");
        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(CreateUserSchema));
    }

}
