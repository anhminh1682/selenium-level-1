package com.example.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {
    public static DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("HH_mm_ss");

    public static String validUserName = "nguyenanhminh168202@gmail.com";
    public static String validPassword = "minh12345";
    public static String validPID = "27183714777";

//    public static String invalidUserName = "minhtest@gmail.com";
    public static String invalidPassword = "abc1234";

    public static String inActiveUsername = "minh" + LocalDateTime.now().format(myTimeFormat) + "@gmail.com";

    // Register message
    public static String registerValidInfoHeading = "Thank you for registering your account";
    public static String errorRegisterWithInvalidInfo = "There're errors in the form. Please correct the errors and try again.";

    // Login message
    public static String errorMessageLoginWithInvalidFields = "Invalid username or password. Please try again.";
    public static String errorMessageLoginWithBlankField = "There was a problem with your login and/or errors exist in your form.";
    public static String warningMessageLoginInvalidTooMuch = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

    // Change Password message
    public static String changePasswordSuccessfulMessage = "Your password has been updated";

    // Page titles
    public static String loginPageTitle = "Safe Railway - Login";
    public static String myTicketPageTitle = "Safe Railway - My Ticket";
    public static String changePasswordPageTitle = "Safe Railway - Change Password";
    public static String bookTicketPageTitle = "Safe Railway - Book Ticket";
}
