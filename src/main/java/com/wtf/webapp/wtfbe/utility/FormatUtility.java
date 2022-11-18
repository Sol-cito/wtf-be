package com.wtf.webapp.wtfbe.utility;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@UtilityClass
public class FormatUtility {

    public String getTodayDateWithTimeAsString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public String getTodayDateAsString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public String stringToJson(String target) {
        target = target.replace("\"", "");
        HashMap<String, String> urlMap = new HashMap<>() {{
            put("=", ":");
            put("&", ",");
            put("%20", ""); // space
            put("%7B", "{");
            put("%7D", "}");
            put("%26", ","); // &
            put("%3D", ":"); // =
            put("%22", ""); // "
        }};
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < target.length(); i++) {
            if (i < target.length() - 2) {
                String urlKey = "" + target.charAt(i)
                        + target.charAt(i + 1)
                        + target.charAt(i + 2);
                if (urlMap.containsKey(urlKey)) {
                    sb.append(urlMap.get(urlKey));
                    i += 2;
                    continue;
                }
            }
            if (urlMap.containsKey("" + target.charAt(i))) {
                sb.append(urlMap.get("" + target.charAt(i)));
            } else {
                sb.append(target.charAt(i));
            }
        }
        sb.append("}");
        String res = sb.toString();
        sb = new StringBuilder();

        boolean isAlphaStarted = false;
        for (int i = 0; i < res.length(); i++) {
            if ((65 <= res.charAt(i) && res.charAt(i) <= 90) || (97 <= res.charAt(i) && res.charAt(i) <= 122)) {
                if (!isAlphaStarted) {
                    sb.append("\"");
                    isAlphaStarted = true;
                }
            } else {
                if (isAlphaStarted) {
                    sb.append("\"");
                    isAlphaStarted = false;
                }
            }
            sb.append(res.charAt(i));
        }
        return sb.toString();
    }

    public String getFileExtension(String fullFileName) {
        StringBuilder sb = new StringBuilder();
        int pointer = fullFileName.length() - 1;
        while (fullFileName.charAt(pointer) != '.') {
            sb.append(fullFileName.charAt(pointer));
            pointer--;
        }
        return sb.reverse().toString();
    }
}
