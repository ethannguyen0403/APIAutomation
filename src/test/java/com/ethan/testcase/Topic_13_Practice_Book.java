package com.ethan.testcase;

import Model.Data.BookPOJO;
import com.ethan.global.ConfigsGlobal;
import com.ethan.global.TokenGlobal;
import com.google.gson.Gson;
import common.BaseTest;
import helpers.JsonHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Topic_13_Practice_Book extends BaseTest {
    int idCategory;
    @Test(priority = 1)
    public void Test_Add_New_Category() {
        Faker faker = new Faker(new Locale("vi"));
        String dataFile = "src/test/resources/testdata/CreateCategory.json";
        JsonHelper.updateValueJsonFile(dataFile,"name",faker.job().title());
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

    @Test (priority = 2)
    public void Test_Add_Book(){
        Faker faker = new Faker(new Locale("vi"));

        BookPOJO bookPOJO = new BookPOJO();
        bookPOJO.setName(faker.book().title());
        bookPOJO.setCategory_id(idCategory);
        bookPOJO.setPrice(150000);
        bookPOJO.setRelease_date("2025-02-09");
        bookPOJO.setStatus(true);

        ArrayList<Integer> lstImageId = new ArrayList<>(Arrays.asList(1,3)) ;
        bookPOJO.setImage_ids(lstImageId);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_JSON)
                .contentType(ConfigsGlobal.CONTENT_TYPE_JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));

        Response response = request.post("/book");
        response.prettyPrint();
        response.then().statusCode(200);


    }
}
