package com.wtf.webapp.wtfbe.utility;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class CommonUtility {
    public boolean isEmpty(MultipartFile target) {
        return target == null || target.getSize() == 0;
    }

    public boolean isEmpty(String target) {
        return target == null || target.length() == 0;
    }
}
