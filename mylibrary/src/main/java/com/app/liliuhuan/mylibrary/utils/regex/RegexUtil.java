package com.app.liliuhuan.mylibrary.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: liliuhuan
 * date：2018/6/27
 * version:1.0.0
 * description: RegexUtil${DES} 正则工具类
 */
public class RegexUtil {

    private static final String ID_CARD = "(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X|x)?";
    private static final String BANK_CARD = "([1-9]{1})(\\d{15}|\\d{18})";
    private static final String PHONE = "[1-9]{1}\\d{10}";
    private static final String PASSWORD = "[a-zA-Z0-9]{6,16}";

    public static boolean validateBankCard(String cardNumber) {
        return validateRegex(cardNumber, BANK_CARD);
    }

    public static boolean validatePhone(String phoneNumber) {
        return validateRegex(phoneNumber, PHONE);
    }

    public static boolean validatePassword(String pass) {
        return validateRegex(pass, PASSWORD);
    }

    public static boolean validateRegex(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
