package com.tracking.service.util;

import com.tracking.service.constant.ServiceConstant;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {

    public String generateTrackingNumber() {
        return RandomStringUtils.randomAlphanumeric(1, 16).toUpperCase();
    }

    public String generateDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(ServiceConstant.DATE_FORMAT_RFC3339);
        return format.format(date);
    }
}
