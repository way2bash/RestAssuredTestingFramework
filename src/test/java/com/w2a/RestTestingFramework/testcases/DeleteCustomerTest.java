package com.w2a.RestTestingFramework.testcases;

import com.w2a.RestFramework.API.CreateCustomerAPI;
import com.w2a.RestFramework.API.DeleteCustomerAPI;
import com.w2a.RestFramework.Utilities.DataUtil;
import com.w2a.RestFramework.Utilities.TestUtil;
import com.w2a.RestFrameworkSetup.BaseTest;
import com.w2a.RestTestingFramework.listeners.ExtentListeners;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

import static java.lang.System.*;

@Test(dataProviderClass = DataUtil.class,dataProvider="data")
public class DeleteCustomerTest extends BaseTest {
    public void deleteCustomer(Hashtable<String, String> data) {
        Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidId(data);
       ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        out.println(response.statusCode());
        /*String actual_id =response.jsonPath().get("id").toString();
        System.out.println("Geeting id from json path:"+actual_id);
        Assert.assertEquals(actual_id,data.get("id"),"Id not matching");
        */
        /*;
        System.out.println(jsonobject.has("id"));
        Assert.assertTrue(jsonobject.has("id"),"Id key is not present inthe JSON response");*/
       System. out.println("Presence check for object key:"+ TestUtil.jsonHasKey(response.asString(),"object"));
       System. out.println("Presence check for Deleted key :"+ TestUtil.jsonHasKey(response.asString(),"deleted"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(),"id"),"Id key is not presented");
        String actual_id = TestUtil.getJsonKeyValue(response.asString(),"id");
        System.out.println(actual_id);
        Assert.assertEquals(actual_id,data.get("id"),"Id not matching");
        System.out.println("object key value is:"+ TestUtil.getJsonKeyValue(response.asString(),"object"));
        System.out.println("Deleted key value  key value is:"+ TestUtil.getJsonKeyValue(response.asString(),"deleted"));
        Assert.assertEquals(response.statusCode(),200);
    }
}