package com.wtf.webapp.wtfbe.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtility {

    public boolean isEmpty(String target) {
        return target == null || target.length() == 0;
    }
}
