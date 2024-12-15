package com.intercom.framework.automation.services.testdata;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CSVTestDataReader {

    Map<String, String> resultMap;

    public void setMapFromCSV(final String filePath) throws IOException {

        if(new File(filePath+ ".csv").exists()) {
            Stream<String> lines = Files.lines(Paths.get(filePath + ".csv"));
            resultMap = lines.map(line -> line.split(","))
                    .collect(Collectors.toMap(line -> line[0], line -> line[1]));
            lines.close();
        }
    }

    public Map<String, String> getTestDataMap(){
        return resultMap;
    }

}
