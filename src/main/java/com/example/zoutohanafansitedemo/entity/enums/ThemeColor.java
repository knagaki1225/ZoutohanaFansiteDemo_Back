package com.example.zoutohanafansitedemo.entity.enums;

import java.util.Arrays;

public enum ThemeColor {
    RED("FF0000","赤"),
    GREEN("00FF00", "緑"),
    BLUE("0000FF", "青");

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
