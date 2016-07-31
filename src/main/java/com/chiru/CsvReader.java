package com.chiru;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvReader {
    
    private static volatile Logger logger = 
            LoggerFactory.getLogger(CsvReader.class);
    
    public List<String> read(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            logger.error("not exist: {}", file.getPath());
            return new ArrayList<>();
        }
        
        BufferedReader br = null;
        List<String> csvLine = new ArrayList<>();
        
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Shift-JIS"));
            String line;
            while ((line = br.readLine()) != null) {
                logger.debug("---line: {}", line);
                csvLine.add(line);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return csvLine;
    }
}
