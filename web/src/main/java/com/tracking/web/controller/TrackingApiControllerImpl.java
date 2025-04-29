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
import org.springframework.web.bind.annotation.RequestParam;

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
            @NotNull @Valid @RequestParam(value = "origin_country_id", name = "origin_country_id")
                    String originCountryId,
            @NotNull
                    @Valid
                    @RequestParam(value = "destination_country_id", name = "destination_country_id")
                    String destinationCountryId,
            @NotNull @Valid @RequestParam(value = "weight", name = "weight") Double weight,
            @NotNull @Valid @RequestParam(value = "created_at", name = "created_at")
                    String createdAt,
            @NotNull @Valid @RequestParam(value = "customer_id", name = "customer_id")
                    String customerId,
            @NotNull @Valid @RequestParam(value = "customer_name", name = "customer_name")
                    String customerName,
            @NotNull @Valid @RequestParam(value = "customer_slug", name = "customer_slug")
                    String customerSlug) {

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
