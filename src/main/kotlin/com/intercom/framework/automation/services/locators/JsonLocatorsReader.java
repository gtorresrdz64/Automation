package com.intercom.framework.automation.services.locators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.framework.automation.settings.TASSettings;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonLocatorsReader {

    private File jsonLocatorsFile;

    public File setJsonTestDataFile(String path,String testcaseName){
        return new File(path+testcaseName+".json");
    }

    public Map getLocatorsMap() throws IOException {
        if(!jsonLocatorsFile.exists()) {
            return new HashMap<>();
        }

        return new ObjectMapper().readValue(jsonLocatorsFile, HashMap.class);
    }
}
