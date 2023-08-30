package com.w2a.RestFramework.Utilities;

import com.w2a.RestFrameworkSetup.BaseTest;
import org.json.JSONObject;

public class TestUtil extends BaseTest {


    public static boolean jsonHasKey(String json,String key) {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.has(key);
    }
    public static String getJsonKeyValue(String json,String key){
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.get(key).toString();
    }

}
