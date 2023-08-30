package com.w2a.RestFramework.API;

import com.w2a.RestFrameworkSetup.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CreateCustomerAPI extends BaseTest {
    public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(Hashtable<String ,String> data) {
        Response response = given().auth().basic(config.getProperty("validSecretKey"),"").
                formParam("email",data.get("email"))
                .formParam("description",data.get("description") )
                .post(config.getProperty("CustomerAPIEndPoint"));

        return response;
    }
    public static Response sendPostRequestToCreateCustomerAPIWithInValidAuthKey(Hashtable<String ,String>data) {
        Response response = given().auth().basic(config.getProperty("Invalidsecretkey"),"").
                formParam("email",data.get("email"))
                .formParam("description",data.get("description") )
                .post(config.getProperty("CustomerAPIEndPoint"));

        return response;
    }
}
