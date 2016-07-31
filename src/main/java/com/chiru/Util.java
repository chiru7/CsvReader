package com.chiru;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Util {
    
    private Util() {}
    
    @SafeVarargs
    public static <E> List<E> toConstList(E... data) {
        int length = (data == null) ? 0 : data.length;

        switch (length) {
        case 0:
            return Collections.emptyList();
        case 1:
            return Collections.singletonList(data[0]);
        default:
            return Collections.unmodifiableList(Arrays.asList(data));
        }
    }
}
