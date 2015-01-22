package ru.roman.pammcontr.model;

/** @author Roman 20.01.13 22:36 */
public enum WordCategory {

    COMMON,
    BASIC,
    IT,

    ;

    public static WordCategory valueOf(Long wordLandId) {
        for (WordCategory item : values()) {
            if (item.ordinal() == wordLandId) {
                return item;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown ordinal %s", wordLandId));
    }

    public Long getOrdinal() {
        return (long)ordinal();
    }
}
