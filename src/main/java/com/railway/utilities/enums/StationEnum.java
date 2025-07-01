package com.railway.utilities.enums;

public enum StationEnum {
    SAI_GON("Sài Gòn"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    PHAN_THIET("Phan Thiết"),
    QUANG_NGAI("Quảng Ngãi"),
    NHA_TRANG("Nha Trang"),
    ;

    private final String stationName;

    StationEnum(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public static StationEnum fromStationName(String stationName) {
        for (StationEnum station : values()) {
            if(station.getStationName().equals(stationName)) return station;
        }

        throw new IllegalArgumentException("No enum found for station name: " + stationName);
    }
}
