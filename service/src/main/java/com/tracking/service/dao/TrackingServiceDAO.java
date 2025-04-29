package com.tracking.service.dao;

import com.tracking.service.exception.InvalidTrackingNumberException;
import com.tracking.service.exception.TrackingNumberException;
import com.tracking.service.model.TrackingNumber;

public interface TrackingServiceDAO {

    public TrackingNumber nextTrackingNumber(
            String originCountryId,
            String destinationCountryId,
            Double weight,
            String createdAt,
            String customerId,
            String customerName,
            String customerSlug)
            throws TrackingNumberException, InvalidTrackingNumberException;
}
