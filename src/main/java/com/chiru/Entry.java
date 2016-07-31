package com.chiru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Entry {
    
    /**
     * ロガー
     */
    private static volatile Logger logger = 
            LoggerFactory.getLogger(Entry.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        CsvReader cr = new CsvReader();
        cr.reading("./csv/sample.csv");
    }
}
