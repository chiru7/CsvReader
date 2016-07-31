package com.chiru;

import java.util.Collections;
import java.util.List;
import lombok.Getter;

public class Define {
    
    @Getter
    private final List<OptionCouple> optionCouples;
    
    public Define(List<OptionCouple> optionCouples) {
        this.optionCouples = Collections.unmodifiableList(optionCouples);
    }
}
