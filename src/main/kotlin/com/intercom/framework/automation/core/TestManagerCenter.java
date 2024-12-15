package com.intercom.framework.automation.core;

import com.intercom.framework.automation.services.locators.JsonLocatorsReader;
import com.intercom.framework.automation.services.testdata.CSVTestDataReader;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TestManagerCenter {

    private int count;
    private static JsonLocatorsReader jsonLocatorsReader;

    private static CSVTestDataReader csvTestDataReader;

    public TestManagerCenter(){
        jsonLocatorsReader = new JsonLocatorsReader();
        csvTestDataReader = new CSVTestDataReader();
    }

    public static void setAnnexedFiles(String path,String testcase) throws IOException {
            path= path.replace('.','/');
            jsonLocatorsReader.setJsonTestDataFile(path+"/locators/",testcase);
            csvTestDataReader.setMapFromCSV(path+"/testdata/"+testcase);
    }

    public static Map getTestData(){
        return csvTestDataReader.getTestDataMap();
    }

    public static Map getLocators() throws IOException {
        return jsonLocatorsReader.getLocatorsMap();
    }

}
