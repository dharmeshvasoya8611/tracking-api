package com.tracking.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tracking.service.TrackingService;
import com.tracking.service.exception.InvalidTrackingNumberException;
import com.tracking.service.exception.TrackingNumberException;
import com.tracking.web.api.TrackingApi;
import com.tracking.web.model.RestTrackingNumber;
import com.tracking.web.model.ValidationMessage;
import com.tracking.web.util.WebConverter;
import com.tracking.web.validator.WebValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TrackingApiControllerImpl implements TrackingApi {

    private static final Log LOG = LogFactory.getLog(TrackingApiControllerImpl.class);
    private final TrackingService trackingService;
    private final WebValidator webValidator;
    private final WebConverter webConverter;

    public TrackingApiControllerImpl(
            TrackingService trackingService, WebValidator webValidator, WebConverter webConverter) {
        this.trackingService = trackingService;
        this.webValidator = webValidator;
        this.webConverter = webConverter;
    }

    @Override
    public ResponseEntity nextTrackingNumber(
            @NotNull @Valid String originCountryId,
            @NotNull @Valid String destinationCountryId,
            @NotNull @Valid String customerId,
            @Valid Double weight,
            @Valid String createdAt,
            @Valid String customerName,
            @Valid String customerSlug) {

        LOG.debug("nextTrackingNumber() started");
        try {

            ValidationMessage message =
                    webValidator.validateNextTrackingNumber(
                            originCountryId,
                            destinationCountryId,
                            weight,
                            createdAt,
                            customerId,
                            customerName,
                            customerSlug);

            if (!message.isValid()) {
                LOG.debug("nextTrackingNumber() invalid request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(webConverter.toJSONString(webConverter.toResponseError(message)));
            }

            RestTrackingNumber restTrackingNumber =
                    webConverter.toRestTrackingNumber(
                            trackingService.nextTrackingNumber(
                                    originCountryId,
                                    destinationCountryId,
                                    weight,
                                    createdAt,
                                    customerId,
                                    customerName,
                                    customerSlug));

            if (restTrackingNumber != null) {
                LOG.debug("nextTrackingNumber() success");
                return ResponseEntity.ok(restTrackingNumber);
            } else {
                LOG.debug("nextTrackingNumber() failed");
                return ResponseEntity.notFound().build();
            }

        } catch (JsonProcessingException jsonProcessingException) {
            LOG.error("nextTrackingNumber() error:" + jsonProcessingException.getMessage());
            return ResponseEntity.internalServerError().build();
        } catch (TrackingNumberException | InvalidTrackingNumberException exception) {
            LOG.error("nextTrackingNumber() error:" + exception.getMessage());
            return ResponseEntity.internalServerError().build();
        } finally {
            LOG.debug("nextTrackingNumber() ended");
        }
    }
}
