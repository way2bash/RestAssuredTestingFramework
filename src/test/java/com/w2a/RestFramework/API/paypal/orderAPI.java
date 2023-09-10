package com.w2a.RestFramework.API.paypal;

import com.w2a.RestAssuredTestingFramework.pojo.Orders;
import com.w2a.RestAssuredTestingFramework.pojo.PurchaseUnits;
import com.w2a.RestFrameworkSetup.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class orderAPI  extends BaseTest {
     //static String access_token;
    static String client_id =config.getProperty("paypalClientId");
    static String secret =config.getProperty("paypalSecret");
    static String orderId;
    public static String getAccessToken(){
    String  access_token = given().param("grant_type", "client_credentials").auth().preemptive().basic(client_id, secret).post("v1/oauth2/token").
         jsonPath().get("access_token").toString();
        System.out.println(access_token);
       return access_token;
    }

    public static Response createOrder(String access_token){
        ArrayList<PurchaseUnits> list = new ArrayList<PurchaseUnits>();
        list.add(new PurchaseUnits("USD","500.00"));
        Orders order = new Orders("CAPTURE",list);
        Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token).body(order).post("v2/checkout/orders");
        response.prettyPrint();
        orderId=response.jsonPath().get("id").toString();
        System.out.println("id is"+orderId);
        return response;
       /* Assert.assertEquals(response.jsonPath().get("status").toString(),"CREATED");

*/
    }

    public static  Response getOrder(String access_token){
        System.out.println("Order id is : "+ orderId);
        Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token).get("v2/checkout/orders"+"/"+orderId);
      return response;

    }
}
