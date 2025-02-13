package common;

import Model.LoginPOJO;
import com.ethan.global.ConfigsGlobal;
import com.ethan.global.TokenGlobal;
import com.google.gson.Gson;
import helpers.PropertiesHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseTest {
    @BeforeSuite
    public void setupSuite(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeTest
    public void loginUser() {
        LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ConfigsGlobal.ACCEPT_JSON)
                .contentType(ConfigsGlobal.CONTENT_TYPE_JSON)
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TokenGlobal.TOKEN = response.getBody().path("token");
        System.out.println("Token Global: " + TokenGlobal.TOKEN);
    }
}
