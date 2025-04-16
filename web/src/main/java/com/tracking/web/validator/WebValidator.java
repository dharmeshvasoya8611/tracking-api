package com.tracking.web.validator;

import com.tracking.service.constant.ServiceConstant;
import com.tracking.web.model.ValidationMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class WebValidator {

    public ValidationMessage validateGetTrackingNumber(
            String originCountryId,
            String destinationCountryId,
            Double weight,
            String createdAt,
            String customerId,
            String customerName,
            String customerSlug) {

        ValidationMessage message = new ValidationMessage();

        if (originCountryId == null || originCountryId.isEmpty() || originCountryId.length() != 2) {
            message.setValid(false);
            message.setMessage("invalid originCountryId: " + originCountryId);
        } else if (destinationCountryId == null
                || destinationCountryId.isEmpty()
                || destinationCountryId.length() != 2) {
            message.setValid(false);
            message.setMessage("invalid destinationCountryId: " + destinationCountryId);
        } else if (weight == null || weight < 0 || !isValidWeight(weight)) {
            message.setValid(false);
            message.setMessage("invalid weight: " + weight);
        } else if (createdAt == null || createdAt.isEmpty() || !isValidDate(createdAt)) {
            message.setValid(false);
            message.setMessage("invalid createdAt: " + createdAt);
        } else if (customerId == null || customerId.isEmpty() || customerId.length() > 36) {
            message.setValid(false);
            message.setMessage("invalid customerId: " + customerId);
        } else if (customerName == null || customerName.isEmpty() || customerName.length() > 50) {
            message.setValid(false);
            message.setMessage("invalid customerName: " + customerName);
        } else if (customerSlug == null || customerSlug.isEmpty() || customerSlug.length() > 50) {
            message.setValid(false);
            message.setMessage("invalid customerSlug: " + customerSlug);
        } else {
            message.setValid(true);
            message.setMessage("valid request");
        }

        return message;
    }

    public boolean isValidDate(String date) {

        /*
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_RFC3339);
        try {
        	format.parse(date);
        	return true;
        } catch (ParseException e) {
        	return false;
        }*/

        return true;
    }

    public boolean isValidWeight(Double weight) {

        final Pattern pattern = Pattern.compile(ServiceConstant.REGEX_WEIGHT);
        Matcher matcher = pattern.matcher(String.valueOf(weight));
        return matcher.matches();
    }
}
