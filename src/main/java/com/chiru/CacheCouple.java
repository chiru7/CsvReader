package com.chiru;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheCouple {
    
    private static final String OPTION_COUPLE_FILE_PATH = "./csv/sample.csv";
    
    private static final int COL_PARENT_CODE = 0;
    private static final int COL_PARENT_GROUP_CODE = 1;
    private static final int COL_CHILD_CODE = 2;
    private static final int COL_CHILD_GROUP_CODE = 3;
    
    private static volatile Logger logger = LoggerFactory.getLogger(CsvReader.class);
    
    private static CacheCouple instance;
    
    @Getter
    private List<OptionCouple> optionCouples = new ArrayList<>();
    
    private void setOptionCouples(List<OptionCouple> optionCouples) {
        this.optionCouples = Collections.unmodifiableList(optionCouples);
    }
    
    private CacheCouple(){}
    
    public static CacheCouple getInstance() throws IOException {
        logger.debug("getInstance()");
        File file = new File(OPTION_COUPLE_FILE_PATH);
        if (!file.exists()) {
            logger.error("file not exist. path: {}", OPTION_COUPLE_FILE_PATH);
            throw new FileNotFoundException("file not exist.");
        }
        return getInstance(file);
    }
    
    private static CacheCouple getInstance(File file) throws IOException {
        logger.debug("getInstance(File file)");
        if (instance == null) {
            instance = new CacheCouple();
            try {
                instance.load();
            } catch (Exception ex) {
                instance = null;
                throw ex;
            }
        }
        return instance;
    }
    
    private void load() throws IOException {
        logger.debug("load()");
        CsvReader reader = new CsvReader();
        setOptionCouples(makeOptionCouples(reader.read(OPTION_COUPLE_FILE_PATH)));
    }
    
    private List<OptionCouple> makeOptionCouples(List<String> lines) throws IOException {
        List<OptionCouple> couples = new ArrayList<>();
        OptionCouple couple = null;
        for (String line : lines) {
            
            String[] ary = line.split(",", -1);
            if (ary.length < 4) {
                throw new IOException("length < 4");
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
