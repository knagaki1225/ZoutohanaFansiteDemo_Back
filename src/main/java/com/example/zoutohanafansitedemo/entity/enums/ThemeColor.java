package com.example.zoutohanafansitedemo.entity.enums;

import java.util.Arrays;

public enum ThemeColor {
    RED("DB4437","レッド"),
    ORANGE("FF5722","オレンジ"),
    YELLOW("FF9800","イエロー"),
    LIME("4CAF50","ライム"),
    GREEN("009688","グリーン"),
    TURQUOISE("00BCD4","ターコイズ"),
    LIGHTBLUE("03A9F4","ライトブルー"),
    BLUE("4285F4","ブルー"),
    DARKBLUE("3F51B5","ダークブルー"),
    PURPLE("673AB7","パープル"),
    GRAY("607D8B","グレー"),
    ELEPHANT("9E9E9E","エレファント");

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
