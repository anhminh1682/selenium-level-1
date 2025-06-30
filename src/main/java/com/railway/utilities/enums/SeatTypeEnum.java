package com.railway.utilities.enums;

public enum SeatTypeEnum {
    HARD_SEAT("Hard seat"),
    SOFT_SEAT("Soft seat"),
    SOFT_SEAT_WITH_AIR_CONDITIONER("Soft seat with air conditioner"),
    HARD_BED("Hard bed"),
    SOFT_BED("Soft bed"),
    SOFT_BED_WITH_AIR_CONDITIONER("Soft bed with air conditioner"),
            ;

    private final String seatTypeName;

    SeatTypeEnum(String seatTypeName) {
        this.seatTypeName = seatTypeName;
    }

    public String getSeatTypeName() {
        return seatTypeName;
    }

    public static SeatTypeEnum fromSeatType(String seatTypeName) {
        for (SeatTypeEnum seatType : values()) {
            if(seatType.getSeatTypeName().equals(seatTypeName)) return seatType;
        }

        throw new IllegalArgumentException("No enum found for seat type name: " + seatTypeName);
    }
}
