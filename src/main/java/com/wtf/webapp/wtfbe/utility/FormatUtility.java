package com.wtf.webapp.wtfbe.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FormatUtility {
    public String stringToJson(String target) {
        return new StringBuilder().append("{\"")
                .append(
                        target.replace("=", "\":")
                                .replace("&", ",\"")
                                .replace("%22", "\"")
                                .replace("%7B", "{")
                                .replace("%7D", "}")
                )
                .append("}")
                .toString();
    }
}
