package utils;

import com.ethan.global.ConfigsGlobal;
import com.ethan.global.TokenGlobal;
import io.qameta.allure.restassured.AllureRestAssured;
import utils.LogUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {
    public static RequestSpecification getRequestSpecBuilder() {
        return new RequestSpecBuilder().
                setBaseUri(ConfigsGlobal.URI).
                addHeader("Authorization", "Bearer " + TokenGlobal.TOKEN).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                addFilter(new AllureRestAssured()).
//                addFilter(new RequestLoggingFilter()).
//                addFilter(new ResponseLoggingFilter()).
                        log(LogDetail.BODY).
                build();
    }

    public static ResponseSpecification getResponseSpecBuilder() {
        return new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.BODY).
                build();
    }

    public static RequestSpecification getRequestNotAuthSpecBuilder() {
        return new RequestSpecBuilder().
                setBaseUri(ConfigsGlobal.URI).
                setContentType(ContentType.JSON).
                setAccept(ContentType.JSON).
                addFilter(new AllureRestAssured()).
//                addFilter(new RequestLoggingFilter()).
                //addFilter(new ResponseLoggingFilter()).
                        log(LogDetail.BODY).
                build();
    }
}
