package com.ronial.utils;

import com.ronial.constants.RegexConstant;

import java.util.regex.Pattern;

public class RegexUtils {
    public static boolean isValidPhone(String phone) {
        return Pattern.matches(RegexConstant.PHONE_REGEX, phone);
    }

    public static boolean isValidEmail(String email) {
        return Pattern.matches(RegexConstant.EMAIL_REGEX, email);
    }
}
