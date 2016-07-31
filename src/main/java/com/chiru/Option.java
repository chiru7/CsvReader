package com.chiru;

import lombok.Getter;

public class Option {
    
    @Getter
    private final String code;
    
    @Getter
    private final String groupCode;
    
    public Option(String code, String groupCode) {
        this.code = code;
        this.groupCode = groupCode;
    }
}
