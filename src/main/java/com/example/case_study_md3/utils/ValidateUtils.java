package com.example.case_study_md3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
    private static String REGEX;
    public static boolean isProductNameValid(String productName){
        REGEX = "^[a-zA-Z][a-zA-Z0-9\\s.,!?-]{7,120}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(productName);
        return matcher.matches();
    }public static boolean isNameValid(String name){
        REGEX = "^[a-zA-Z\\s]{7,30}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean isDescriptionValid(String description){
        REGEX = "^[a-zA-Z][a-zA-Z0-9\\s.,!?-_()]{8,245}$";
        return Pattern.matches(REGEX,description);
    }

    public static boolean isAddressValid(String address){
        REGEX = "^[a-zA-Z0-9\\s.,]{3,245}$";
        return Pattern.matches(REGEX,address);
    }
    public static boolean isPhoneValid(String phone){
        REGEX = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        return Pattern.matches(REGEX,phone);
    }
    public static boolean isEmailValid(String email){
        REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
        return Pattern.matches(REGEX,email);
    }
    public static boolean isDOBValid(String dob){
        REGEX = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        return Pattern.matches(REGEX,dob);
    }
    public static boolean passwordValidate(String password){
        REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static boolean isPrice(String price) {
        REGEX = "^[1-9]\\d{2,3}(\\.\\d{1,2})?$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }
    public static boolean isQuantity(String quantity) {
        REGEX = "^[1-9][0-9]{0,1}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(quantity);
        return matcher.matches();
    }
    public static boolean isLink(String link){
        REGEX = "^(https:\\/\\/)?[a-zA-Z0-9-_./]{20,200}";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(link);
        return matcher.matches();
    }
}
