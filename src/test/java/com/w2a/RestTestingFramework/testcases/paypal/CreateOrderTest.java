package com.w2a.RestTestingFramework.testcases.paypal;

import com.w2a.RestFramework.API.paypal.orderAPI;
import com.w2a.RestFrameworkSetup.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateOrderTest extends BaseTest {


   @Test
    public void createOrder() {

        String accessToken = orderAPI.getAccessToken();
        Response response = orderAPI.createOrder(accessToken);
        Assert.assertEquals(response.jsonPath().get("status").toString(), "CREATED");
        response.prettyPrint();
    }

}