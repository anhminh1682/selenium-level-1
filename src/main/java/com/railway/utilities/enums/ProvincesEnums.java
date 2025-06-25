package com.railway.utilities.enums;

public enum ProvincesEnums {
    SAI_GON("Sài Gòn"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    PHAN_THIET("Phan Thiết"),
    QUANG_NGAI("Quảng Ngãi"),
    NHA_TRANG("Nha Trang"),
    ;

    private final String displayName;

    ProvincesEnums(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
