package com.w2a.RestFrameworkSetup;

import com.w2a.RestFramework.Utilities.ExcelReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.basePath;

public class BaseTest  {
    public static Properties config = new Properties();
    private FileInputStream fis;

    public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
    @BeforeSuite
    public void setUp() {
    try{
        fis= new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
          config.load(fis);
      }catch (IOException e){
          e.printStackTrace();
      }
        RestAssured.baseURI =config.getProperty("baseURI");
        RestAssured.basePath=config.getProperty("basePath");

    }
    public void tearDown(){

    }
}
