package com.w2a.RestTestingFramework.testcases.paypal;

import com.w2a.RestFramework.API.paypal.orderAPI;
import com.w2a.RestFrameworkSetup.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetOrderTest extends BaseTest {


    @Test
    public void getOrder() {

        String accessToken = orderAPI.getAccessToken();
        Response response = orderAPI.getOrder(accessToken);
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
    }

}