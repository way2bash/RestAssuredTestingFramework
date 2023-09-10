package com.w2a.RestTestingFramework.testcases.stripe;
import com.w2a.RestFramework.API.stripe.CreateCustomerAPI;
import com.w2a.RestFramework.Utilities.DataUtil;
import com.w2a.RestFrameworkSetup.BaseTest;
import com.w2a.RestTestingFramework.listeners.ExtentListeners;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest {
    @Test(dataProviderClass = DataUtil.class,dataProvider="data")
    public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String ,String>data) {
        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
       /// ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(),200);

       /* Response response = given().auth().basic(config.getProperty("validSecretKey"),"").
                formParam("email",data.get("email"))
                .formParam("description",data.get("description") )
                .post(config.getProperty("CustomerAPIEndPoint"));

        response.prettyPrint();
        System.out.println(response.statusCode());*/

    }

    @Test(dataProviderClass = DataUtil.class,dataProvider="data")
    public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String,String>data) {
        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
        ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(),401);

       /* Response response = given().auth().basic(config.getProperty("Invalidsecretkey"), "")
                .formParam("email",data.get("email"))
                .formParam("description",data.get("description") )
                .post(config.getProperty("CustomerAPIEndPoint"));
        response.prettyPrint();
        System.out.println(response.statusCode());*/

    }



}
