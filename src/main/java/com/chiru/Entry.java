package com.chiru;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Entry {
    
    private static volatile Logger logger = 
            LoggerFactory.getLogger(Entry.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.debug("start #####################################");
        try {
            CacheCouple cache = CacheCouple.getInstance();
            List<OptionCouple> couples = cache.getOptionCouples();
            cache.getOptionCouples().add(new OptionCouple("add", "add"));
            logger.debug("1st #####################################");
            for (OptionCouple couple : couples) {
                logger.debug("### couple getCode: {}, getGroupCode: {}", couple.getCode(), couple.getGroupCode());
                for (Option child : couple.getChildren()) {
                    logger.debug("##### child getCode: {}, getGroupCode: {}", child.getCode(), child.getGroupCode());
                }
            }
            logger.debug("1st #####################################");
        } catch (Exception ex) {
            logger.error("error: {}", ex);
        }
        
        try {
            CacheCouple cache = CacheCouple.getInstance();
            List<OptionCouple> couples = cache.getOptionCouples();
            
            logger.debug("2nd #####################################");
            for (OptionCouple couple : couples) {
                couple.addChild(new Option("add", "add"));
                logger.debug("### couple getCode: {}, getGroupCode: {}", couple.getCode(), couple.getGroupCode());
                for (Option child : couple.getChildren()) {
                    logger.debug("##### child getCode: {}, getGroupCode: {}", child.getCode(), child.getGroupCode());
                }
            }
            logger.debug("2nd #####################################");
        } catch (Exception ex) {
            logger.error("error: {}", ex);
        }
        
        try {
            CacheCouple cache = CacheCouple.getInstance();
            List<OptionCouple> couples = cache.getOptionCouples();
            logger.debug("3rd #####################################");
            for (OptionCouple couple : couples) {
                logger.debug("### couple getCode: {}, getGroupCode: {}", couple.getCode(), couple.getGroupCode());
                for (Option child : couple.getChildren()) {
                    logger.debug("##### child getCode: {}, getGroupCode: {}", child.getCode(), child.getGroupCode());
                }
            }
            logger.debug("3rd #####################################");
        } catch (Exception ex) {
            logger.error("error: {}", ex);
        }
    }
}
