package com.ethan.testcase;

import com.ethan.global.ConfigsGlobal;
import com.ethan.global.TokenGlobal;
import common.BaseTest;
import helpers.JsonHelper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;
import utils.LogUtils;
import utils.SpecUtils;

import java.io.File;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Topic_18_Allure_Report extends BaseTest {
    int CATEGORY_ID;
    String CATEGORY_NAME;

    @Test(priority = 1)
    public void testAddNewCategory() {
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Faker faker = new Faker(new Locale("vi"));
        CATEGORY_NAME = faker.job().title();
        LogUtils.info("GENERATE CATEGORY NAME: " + CATEGORY_NAME);

        JsonHelper.updateValueJsonFile(dataFile, "name", CATEGORY_NAME);

        Response response = APIUtils.post("/category", new File(dataFile));

        APIUtils.verifyStatusCode(response, 200);

        CATEGORY_ID = Integer.parseInt(APIUtils.getResponseKeyValue(response, "response.id"));
        CATEGORY_NAME = APIUtils.getResponseKeyValue(response, "response.name");

        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);
        LogUtils.info("CATEGORY_NAME: " + CATEGORY_NAME);

    }

    @Test(priority = 2)
    public void getCategoryById() {
        LogUtils.info("CATEGORY_ID: " + CATEGORY_ID);

        Response response = APIUtils.get("/category" + "/" + CATEGORY_ID);

        response.then().statusCode(200);

        Assert.assertEquals(APIUtils.getResponseKeyValue(response, "response.name"), CATEGORY_NAME, "The Category Name not match.");

    }
}
