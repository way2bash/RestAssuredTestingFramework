package com.w2a.RestFramework.Rough;

import com.w2a.RestAssuredTestingFramework.pojo.Orders;
import com.w2a.RestAssuredTestingFramework.pojo.PurchaseUnits;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;




public class TestPayPal {
    static String access_token;
    static String client_id = "Aa0fUgnpEPu6_tmUHSFb2yHx1VonmcqUToX5Rg6hgvHBssIQKcz9xX5KkkX19icDbZ18GlJJpR0Q1cnh";
    static String secret = "EIpugpJweVM-lJbF2METSY6Em5ERmqo6sbOSg2CfGbqdVfeDItyXZ254nB19n-1PcOYRmkY9GjR0WLtQ";
    static String orderId;

    @Test(priority=1)
    public void getAuthKey() {
        RestAssured.baseURI = "https://api-m.sandbox.paypal.com/";
        Response response = given().param("grant_type", "client_credentials").auth().preemptive().basic(client_id, secret).post("v1/oauth2/token");
        response.prettyPrint();
       access_token = response.jsonPath().get("access_token").toString();
        System.out.println(access_token);
      //  Assert.assertEquals(response.jsonPath().get("status"),"CREATED");
    }



@Test(priority=2,dependsOnMethods = "getAuthKey")
public void createOrder() {
    ArrayList<PurchaseUnits> list = new ArrayList<PurchaseUnits>();
    list.add(new PurchaseUnits("USD","500.00"));
        Orders order = new Orders("CAPTURE",list);
            /*String jsonBody ="{\n" +
              "  \"intent\": \"CAPTURE\",\n" +
              "  \"purchase_units\": [\n" +
              "    {\n" +
              "      \"reference_id\": \"d9f80740-38f0-11e8-b467-0ed5f89f718b\",\n" +
              "      \"amount\": {\n" +
              "        \"currency_code\": \"USD\",\n" +
              "        \"value\": \"100.00\"\n" +
              "      }\n" +
              "    }\n" +
              "  ]\n" +
              "} ";*/
    RestAssured.baseURI = "https://api-m.sandbox.paypal.com/";
    Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token).body(order).post("v2/checkout/orders");
    response.prettyPrint();
    Assert.assertEquals(response.jsonPath().get("status").toString(),"CREATED");
    orderId=response.jsonPath().get("id").toString();
}
@Test(priority=3,dependsOnMethods = {"getAuthKey","createOrder"})
    public void  getOrder(){
    RestAssured.baseURI = "https://api-m.sandbox.paypal.com/";
    Response response = given().contentType(ContentType.JSON).auth().oauth2(access_token).get("v2/checkout/orders"+"/"+orderId);
    response.prettyPrint();
    Assert.assertEquals(response.getStatusCode(),200);
}
}