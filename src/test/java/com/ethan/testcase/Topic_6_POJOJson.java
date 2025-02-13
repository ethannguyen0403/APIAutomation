package com.ethan.testcase;

import Model.BookingBody.BookingBody;
import Model.BookingBody.BookingDates;
import Model.LoginPOJO;
import Model.RegisterUserPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Topic_6_POJOJson {
    @Test
    public void Demo_POJO_001() {
        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("anhtester4", "Demo@123");

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

        String token = response.getBody().path("token");
        System.out.println(token);
    }

    @Test
    public void Demo_POJO_002() {
        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        RegisterUserPOJO registerUserPOJO = new RegisterUserPOJO();
        registerUserPOJO.setUsername("myduyen4");
        registerUserPOJO.setPassword("Demo@123");
        registerUserPOJO.setFirstName("Lê Thị");
        registerUserPOJO.setLastName("Mỹ Duyên");
        registerUserPOJO.setEmail("myduyen04@email.com");
        registerUserPOJO.setPhone("0123456789");
        registerUserPOJO.setUserStatus(1);

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(registerUserPOJO));

        Response response = request.when().post("/register");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }

    @Test
    public void Demo_POJO_003(){
        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        //Khởi tạo 2 class POJO
        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();

        //Set giá trị cho các fields không chứa cấp bậc
        bookingBody.setFirstname("Anh");
        bookingBody.setLastname("Tester");
        bookingBody.setTotalprice(100);
        bookingBody.setDepositpaid(true);
        bookingBody.setAdditionalneeds("Technology");

        //Set giá trị cho 2 fields con từ class POJO phụ
        bookingDates.setCheckin("2023-12-15");
        bookingDates.setCheckout("2023-12-30");

        //Set giá trị cho field Cha với 2 thông số từ fields Con
        bookingBody.setBookingdates(bookingDates);

        Gson gson = new Gson();

        //Convert POJO to JSON
        request.body(gson.toJson(bookingBody));

        Response response = request.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
