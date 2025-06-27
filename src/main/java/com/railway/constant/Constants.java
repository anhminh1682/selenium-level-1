package com.railway.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {

    public static class URL {
        public static final String RAILWAY_URL = "http://saferailway.somee.com/";
    }

    public static class MyDateTimeFormat {
        public static final DateTimeFormatter HH_mm_ss = DateTimeFormatter.ofPattern("HH_mm_ss");
        public static final DateTimeFormatter M_d_yyyy = DateTimeFormatter.ofPattern("M/d/yyyy");
    }

    public static class Account {
        public static final String VALID_USERNAME = "vffaebxr@sharklasers.com";
        public static final String VALID_PASSWORD = "minh12345";
        public static final String VALID_PID = "27183714777";

        public static final String INVALID_PASSWORD = "abc1234";
        public static final String INACTIVE_USERNAME_REGISTER = "minh" + LocalDateTime.now().format(MyDateTimeFormat.HH_mm_ss) + "@gmail.com";
        public static final String INACTIVE_USERNAME_LOGIN = "minh@gmail.com";
    }

    public static class RegisterMessage {
        // Register message
        public static final String REGISTER_VALID_INFOR_HEADING = "Thank you for registering your account";
        public static final String ERROR_REGISTER_WITH_INVALID_INFO = "There're errors in the form. Please correct the errors and try again.";
        public static final String ERROR_REGISTER_WITH_INVALID_PASSWORD_LENGTH = "Invalid password length.";
        public static final String ERROR_REGISTER_WITH_INVALID_PID_LENGTH = "Invalid ID length.";
    }

    public static class LoginMessage {
        // Login message
        public static final String ERROR_MESSAGE_LOGIN_WITH_INVALID_FIELDS = "Invalid username or password. Please try again.";
        public static final String ERROR_MESSAGE_LOGIN_WITH_BLANK_FIELD = "There was a problem with your login and/or errors exist in your form.";
        public static final String WARNING_MESSAGE_LOGIN_INVALID_TOO_MUCH = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
    }

    public static class ChangePasswordMessage {
        // Change Password message
        public static final String CHANGE_PASSWORD_SUCCESSFUL_MESSAGE = "Your password has been updated";
    }

    public static class ResetPasswordMessage {
        // Reset Password message
        public static final String ERROR_MESSAGE_INCORRECT_RESET_TOKEN_ABOVE = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
        public static final String ERROR_MESSAGE_INVALID_RESET_TOKEN_NEXT_TO_FIELD = "The password reset token is invalid.";
        public static final String ERROR_MESSAGE_COULD_NOT_RESET_PASSWORD = "Could not reset password. Please correct the errors and try again.";
        public static final String ERROR_MESSAGE_CONFIRM_PASSWORD_NEXT_TO_FIELD = "The password confirmation did not match the new password.";
    }

    public static class PageTitles {
        // Page titles
        public static final String LOGIN_PAGE_TITLE = "Safe Railway - Login";
        public static final String MY_TICKET_PAGE_TITLE = "Safe Railway - My Ticket";
        public static final String CHANGE_PASSWORD_PAGE_TITLE = "Safe Railway - Change Password";
        public static final String BOOK_TICKET_PAGE_TITLE = "Safe Railway - Book Ticket";
        public static final String BOOK_TICKET_SUCCESS_PAGE_TITLE = "Safe Railway - Success";
        public static final String RESET_PASSWORD_PAGE_TITLE = "Safe Railway - Password Reset";
        public static final String REGISTER_PAGE_TITLE = "Safe Railway - Register an Account";
        public static final String HOME_PAGE_TITLE = "Safe Railway";
        public static final String THANK_REGISTER_PAGE_TITLE = "Safe Railway - Thanks for registering";
    }

    public static class TabMenu {
        public static final String LOGIN_TAB = "Login";
        public static final String REGISTER_TAB = "Register";
        public static final String LOG_OUT_TAB = "Log out";
        public static final String BOOK_TICKET_TAB = "Book ticket";
        public static final String MY_TICKET_TAB = "My ticket";
        public static final String CHANGE_PASSWORD_TAB = "Change password";
        public static final String TIMETABLE_TAB = "Timetable";
    }

    public static class BookTicket {
        public static final String DEPART_DATE_NAME_SELECT_BOX = "Date";
        public static final String DEPART_FROM_NAME_SELECT_BOX = "DepartStation";
        public static final String ARRIVE_AT_NAME_SELECT_BOX = "ArriveStation";
        public static final String SEAT_TYPE_NAME_SELECT_BOX = "SeatType";
        public static final String TICKET_AMOUNT_NAME_SELECT_BOX = "TicketAmount";
    }

    public static class BookTicketSuccess {
        public static final String DEPART_DATE_COLUMN_NAME = "Depart Date";
        public static final String DEPART_STATION_COLUMN_NAME = "Depart Station";
        public static final String SEAT_TYPE_COLUMN_NAME = "Seat Type";
        public static final String ARRIVE_STATION_COLUMN_NAME = "Arrive Station";
        public static final String AMOUNT_COLUMN_NAME = "Amount";
    }

    public static class BookTicketMessage {
        public static final String SUCCESSFUL_MESSAGE = "Ticket booked successfully!";
    }

    public static class MailSlurp {
        public static final String API_KEY = "03cb16e7a290b8f1606c32e65039b57d0038f6b1d88f48ea6808bce4070baaeb";
        public static final String REGEX_GET_LINK_FROM_EMAIL = "https?://[^\"\\s]*(confirmationCode|resetToken)=[^\"\\s]+";
    }
}
