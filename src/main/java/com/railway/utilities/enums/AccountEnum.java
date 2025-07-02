package com.railway.utilities.enums;

import com.railway.constant.Constants;
import com.railway.utilities.MailSlurp;

import java.time.LocalDateTime;

public enum AccountEnum {

    VALID_ACCOUNT_LOGIN("vffaebxr@sharklasers.com", "minh12345"),
    BLANK_USERNAME_LOGIN("", "minh12345"),
    INVALID_PASSWORD_LOGIN("vffaebxr@sharklasers.com", "abc1234"),
    INACTIVE_USERNAME_LOGIN("minh@gmail.com", "minh12345"),

    VALID_ACCOUNT_REGISTER("minh" + LocalDateTime.now().format(Constants.MyDateTimeFormat.HH_mm_ss) + "@gmail.com", "minh12345", "minh12345", "27183714777"),
    INVALID_CONFIRM_PASSWORD_REGISTER("vffaebxr@sharklasers.com", "minh12345", "abc1234", "27183714777"),
    VALID_EMAIL_AND_BLANK_OTHER_FIELDS_REGISTER("vffaebxr@sharklasers.com", "", "", ""),

    BLANK_TOKEN_RESET_PASSWORD("minh12345", "minh12345", false),
    INVALID_CONFIRM_PASSWORD_RESET_PASSWORD("minh12345", "abc1234", true),

    VALID_CHANGE_PASSWORD("minh12345", "minh12345", "minh12345")
    ;

    private String username;
    private String password;
    private String confirmPassword;
    private String newPassword;
    private boolean hasToken;
    private String pid;

    AccountEnum(String username, String password) {
        this.username = username;
        this.password = password;
    }

    AccountEnum(String password, String confirmPassword, boolean token) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.hasToken = token;
    }

    AccountEnum(String currentPassword, String newPassword, String confirmPassword) {
        this.password = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    AccountEnum(String username, String password, String confirmPassword, String pid) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.pid = pid;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getPID() {
        return this.pid;
    }

    public boolean getToken() {
        return this.hasToken;
    }
}
