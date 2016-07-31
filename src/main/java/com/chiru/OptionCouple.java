package com.chiru;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

public class OptionCouple {
    
    @Getter
    private final String code;
    
    @Getter
    private final String groupCode;
    
    @Getter
    private List<Option> children;
    
    public OptionCouple(String code, String groupCode) {
        this.code = code;
        this.groupCode = groupCode;
        this.children = new ArrayList<>();
    }
    
    public void addChild(Option child) {
        this.children.add(child);
    }
    
    public boolean isBreak(String code, boolean isUnmodifiable) {
        boolean result = !this.code.equals(code);
        if (result && isUnmodifiable) {
            unmodifiable();
        }
        return result;
    }
    
    public void unmodifiable() {
        this.children = Collections.unmodifiableList(this.children);
    }
}
