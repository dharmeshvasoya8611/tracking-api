package com.tracking.service.constant;

public class ServiceConstant {

    private ServiceConstant() {}

    public static final String REGEX_WEIGHT = "[0-9]+([.][0-9]{1,3})?";
    public static final String DATE_FORMAT_RFC3339 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String TRACKING_NUMBER_REGEX = "^[A-Z0-9]{16}$";
}
