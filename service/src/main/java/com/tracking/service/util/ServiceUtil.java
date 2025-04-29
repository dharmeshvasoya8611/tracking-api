package com.tracking.service.util;

import com.tracking.service.constant.ServiceConstant;
import com.tracking.service.exception.InvalidTrackingNumberException;
import com.tracking.service.exception.TrackingNumberException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {

    public String generateTrackingNumber(
            String originCountryId, String destinationCountryId, String customerId)
            throws TrackingNumberException, InvalidTrackingNumberException {

        StringBuilder trackingNumber = new StringBuilder();

        trackingNumber.append(originCountryId);

        String day = getCurrentDay();
        trackingNumber.append(day);

        trackingNumber.append(destinationCountryId);

        String ip = getCurrentIPAddress();

        trackingNumber.append(ip.substring(ip.length() - 2));

        trackingNumber.append(customerId.substring(customerId.length() - 4));

        String nowTime = String.valueOf(System.currentTimeMillis());
        trackingNumber.append(nowTime.substring(nowTime.length() - 4));

        String trackingNumberString = trackingNumber.toString().toUpperCase();

        if (isValidTrackingNumber(trackingNumberString)) {
            return trackingNumberString;
        } else {
            throw new InvalidTrackingNumberException(
                    "Generated tracking number " + trackingNumberString + " is invalid");
        }
    }

    private String getCurrentDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String dd = sdf.format(new Date());
        return dd;
    }

    private String getCurrentIPAddress() throws TrackingNumberException {

        try {

            InetAddress inetAddress = InetAddress.getLocalHost();

            String ip = inetAddress.getHostAddress();

            if (ip.contains(".")) {
                ip = ip.replace(".", "0");
            }

            return ip;

        } catch (UnknownHostException e) {
            throw new TrackingNumberException("Unable to get host ip address");
        }
    }

    public boolean isValidTrackingNumber(String trackingNumber) {
        final Pattern pattern = Pattern.compile(ServiceConstant.TRACKING_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(trackingNumber);
        return matcher.matches();
    }

    public String generateDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(ServiceConstant.DATE_FORMAT_RFC3339);
        return format.format(date);
    }
}
