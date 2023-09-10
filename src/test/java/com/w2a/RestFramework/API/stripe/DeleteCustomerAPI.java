package com.w2a.RestFramework.API.stripe;

import com.w2a.RestFrameworkSetup.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class DeleteCustomerAPI extends BaseTest{
    public static Response sendDeleteRequestToDeleteCustomerAPIWithValidId(Hashtable<String ,String> data) {
        Response response = given().auth().basic(config.getProperty("validSecretKey"),"").
                formParam("email",data.get("email"))
                .formParam("description",data.get("description") )
                .delete(config.getProperty("CustomerAPIEndPoint")+"/"+data.get("id"));

        return response;
    }
}
