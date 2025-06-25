package com.example.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {

    public static class MyDateTimeFormat {
        public static DateTimeFormatter HH_mm_ss = DateTimeFormatter.ofPattern("HH_mm_ss");
    }

    public static class Account {
        public static String VALID_USERNAME = "vffaebxr@sharklasers.com";
        public static String VALID_PASSWORD = "minh12345";
        public static String VALID_PID = "27183714777";

        public static String INVALID_PASSWORD = "abc1234";
        public static String IN_ACTIVE_USERNAME = "minh" + LocalDateTime.now().format(MyDateTimeFormat.HH_mm_ss) + "@gmail.com";
    }

    public static class RegisterMessage {
        // Register message
        public static String REGISTER_VALID_INFOR_HEADING = "Thank you for registering your account";
        public static String ERROR_REGISTER_WITH_INVALID_INFO = "There're errors in the form. Please correct the errors and try again.";
        public static String ERROR_REGISTER_WITH_INVALID_PASSWORD_LENGTH = "Invalid password length.";
        public static String ERROR_REGISTER_WITH_INVALID_PID_LENGTH = "Invalid ID length.";
    }

    public static class LoginMessage {
        // Login message
        public static String ERROR_MESSAGE_LOGIN_WITH_INVALID_FIELDS = "Invalid username or password. Please try again.";
        public static String ERROR_MESSAGE_LOGIN_WITH_BLANK_FIELD = "There was a problem with your login and/or errors exist in your form.";
        public static String WARNING_MESSAGE_LOGIN_INVALID_TOO_MUCH = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
    }

    public static class ChangePasswordMessage {
        // Change Password message
        public static String CHANGE_PASSWORD_SUCCESSFUL_MESSAGE = "Your password has been updated";
    }

    public static class ResetPasswordMessage {
        // Reset Password message
        public static String ERROR_MESSAGE_INCORRECT_RESET_TOKEN_ABOVE = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
        public static String ERROR_MESSAGE_INVALID_RESET_TOKEN_NEXT_TO_FIELD = "The password reset token is invalid.";
    }

    public static class PageTitles {
        // Page titles
        public static String LOGIN_PAGE_TITLE = "Safe Railway - Login";
        public static String MY_TICKET_PAGE_TITLE = "Safe Railway - My Ticket";
        public static String CHANGE_PASSWORD_PAGE_TITLE = "Safe Railway - Change Password";
        public static String BOOK_TICKET_PAGE_TITLE = "Safe Railway - Book Ticket";
        public static String RESET_PASSWORD_PAGE_TITLE = "Safe Railway - Password Reset";
        public static String REGISTER_PAGE_TITLE = "Safe Railway - Register an Account";
    }
}
