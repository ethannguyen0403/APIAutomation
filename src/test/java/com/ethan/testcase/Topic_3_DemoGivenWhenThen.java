package com.ethan.testcase;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Topic_3_DemoGivenWhenThen {
    @Test
    public void Demo_Given_001(){
        given().baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .basePath("/users")
                .when().get()
                .then().statusCode(200);
    }
    @Test
    public void Demo_Given_002(){
        //Khai bao doi tuong request
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/users");

        //Add header voi (key,value)
//        request.header("accept","application/json");
        request.accept("application/json");

        Response response = request.when().get();
        response.prettyPrint();

        response.then().statusCode(200);
    }
    @Test
    public void Demo_Given_003(){
        //Khai bao doi tuong request
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/user");
        request.accept("application/json");

        request.queryParam("username","anhtester");

        Response response = request.when().get();
        response.prettyPrint();

        response.then().statusCode(200);
    }
}
