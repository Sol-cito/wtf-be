package com.wtf.webapp.wtfbe.enums;

public enum InquiryCategory {
    MATCH("시합문의"),
    BUG_REPORT("버그개선"),
    ETC("기타");

    private final String category;

    InquiryCategory(String category) {
        this.category = category;
    }

    public String getCategoryValue() {
        return category;
    }
}
