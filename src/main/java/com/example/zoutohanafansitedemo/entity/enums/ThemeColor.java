package com.example.zoutohanafansitedemo.entity.enums;

import java.util.Arrays;

public enum ThemeColor {
    GREEN("#449872", "緑"),
    PINK("#FF6C84", "ピンク"),
    YELLOW("#F5B84C", "黄色"),
    BLUE("#00BCD4", "水色"),
    BLACK("#393939", "黒"),
    GRAY("#A0B0B7", "灰色");


    private final String dbValue;
    private final String label;

    ThemeColor(String dbValue, String label) {
        this.dbValue = dbValue;
        this.label = label;
    }

    public String getDbValue() {
        return dbValue;
    }

    public String getLabel() {
        return label;
    }

//    データベースの文字列から Enum を取得
    public static ThemeColor fromString(String dbValue) {
        return Arrays.stream(ThemeColor.values())
                .filter(e -> e.dbValue.equals(dbValue))
                .findFirst()
                .orElse(null);
    }
}
