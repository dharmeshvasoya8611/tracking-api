package com.tracking.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tracking.service.model.TrackingNumber;
import com.tracking.web.constant.WebConstant;
import com.tracking.web.model.ResponseError;
import com.tracking.web.model.RestTrackingNumber;
import com.tracking.web.model.ValidationMessage;
import org.springframework.stereotype.Component;

@Component
public class WebConverter {

    public RestTrackingNumber toRestTrackingNumber(TrackingNumber trackingNumber) {
        RestTrackingNumber restTrackingNumber = new RestTrackingNumber();
        restTrackingNumber.setTrackingNumber(trackingNumber.getNumber());
        restTrackingNumber.setCreatedAt(trackingNumber.getCreatedAt());
        ;
        return restTrackingNumber;
    }

    public ResponseError toResponseError(ValidationMessage message) {
        ResponseError error = new ResponseError();
        error.setCode(
                message.isValid() ? WebConstant.RESPONSE_SUCCESS : WebConstant.RESPONSE_FAILED);
        error.setError(message.getMessage());
        return error;
    }

    public String toJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}
