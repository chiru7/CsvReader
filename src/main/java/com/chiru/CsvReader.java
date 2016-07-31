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
    
    private static final int COL_PARENT_CODE = 0;
    private static final int COL_PARENT_GROUP_CODE = 1;
    private static final int COL_CHILD_CODE = 2;
    private static final int COL_CHILD_GROUP_CODE = 3;
    
    /**
     * ロガー
     */
    private static volatile Logger logger = 
            LoggerFactory.getLogger(CsvReader.class);
    
    public List<OptionCouple> reading(String path) {
        
        List<OptionCouple> list = null;
        try {
            list = makeOptionCouples(read(path));
        } catch (IOException ex) {
            logger.error("{}", ex);
        }
        
        for (OptionCouple couple : list) {
            logger.debug("couple ----- getCode: {}, getGroupCode: {}", couple.getCode(), couple.getGroupCode());
            for (Option child : couple.getChildren()) {
                logger.debug("child --- getCode: {}, getGroupCode: {}", child.getCode(), child.getGroupCode());
            }
        }
        
        Define define = new Define(list);
        
        try {
            for (OptionCouple couple : define.getOptionCouples()) {
                logger.debug("couple ----- getCode: {}, getGroupCode: {}", couple.getCode(), couple.getGroupCode());
                for (Option child : couple.getChildren()) {
                    logger.debug("child --- getCode: {}, getGroupCode: {}", child.getCode(), child.getGroupCode());
                }
            }
        } catch (Exception ex) {
            logger.error("{}", ex);
        }
        
        return list;
    }
    
    private List<String> read(String path) throws IOException {
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
    
    private List<OptionCouple> makeOptionCouples(List<String> lines) {
        List<OptionCouple> couples = new ArrayList<>();
        
        OptionCouple couple = null;
        for (String line : lines) {
            
            String[] ary = line.split(",", -1);
            if (ary.length < 4) {
                
            }
            String optionCode = ary[COL_PARENT_CODE];
            String optionGroupCode = ary[COL_PARENT_GROUP_CODE];
            if (couple == null || couple.isBreak(optionCode, true)) {
                couple = new OptionCouple(optionCode, optionGroupCode);
                couples.add(couple);
            }
            
            String childOptionCode = ary[COL_CHILD_CODE];
            String childOptionGroupCode = ary[COL_CHILD_GROUP_CODE];
            Option child = new Option(childOptionCode, childOptionGroupCode);
            couple.addChild(child);
        }
        return couples;
    }
}
