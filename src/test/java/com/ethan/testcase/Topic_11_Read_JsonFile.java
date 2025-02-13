package com.ethan.testcase;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import common.BaseTest;
import helpers.JsonHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Topic_11_Read_JsonFile extends BaseTest {
    @Test
    public void testLoginUser() {
        //Read Json file path
        String filePath = "src/test/resources/testdata/Login.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api").accept("application/json").contentType("application/json").body(new File(filePath));

        Response response = request.when().post("/login");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testRegisterUser() {
        //Read Json file path
        String filePath = "src/test/resources/testdata/RegisterUser.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api").accept("application/json").contentType("application/json").body(new File(filePath));

        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testUpdateValueInJson_001() {
        JsonHelper.updateValueJsonFile("src/test/resources/testdata/TestJsonFile01.json", "additionalneeds", "Update New Value");
    }

    @Test
    public void testUpdateValueInJson_002() {
        JsonHelper.updateValueJsonFile("src/test/resources/testdata/TestJsonFile01.json", "bookingdates", "checkin", "2024-12-31");
    }

    @Test
    public void testUpdatePropertiesInJson_003() {
        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile01.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Create array value
            JsonArray objectArray = new JsonArray();
            objectArray.add("Support");
            objectArray.add("ERP");

            //Add array value to key "department"
            jsonObject.add("department", objectArray);

            //Add simple key:value
            jsonObject.addProperty("key1", "Value for Key1");

            //Add key:{object}
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("name", "Anh Tester");
            objectMap.put("age", 27);
            JsonElement jsonElement = gson.toJsonTree(objectMap);
            jsonObject.add("student", jsonElement);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to old file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile01Edited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRemoveInJson_004(){
        Reader reader;
        String filePath = "src/test/resources/testdata/TestJsonFile02.json";

        try {
            reader = Files.newBufferedReader(Paths.get(filePath));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            jsonObject.remove("age");

            //Xác định vị trí của property cần xoá
            JsonObject positionObject = jsonObject
                    .get("department").getAsJsonObject()
                    .get("position").getAsJsonObject();

            //Xoá key "years" trong cấu trúc propert
            positionObject.remove("years");

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File("src/test/resources/testdata/TestJsonFile02Edited.json");
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
