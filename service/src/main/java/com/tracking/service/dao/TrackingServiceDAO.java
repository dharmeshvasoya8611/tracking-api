package com.tracking.service.dao;

import com.tracking.service.model.TrackingNumber;

public interface TrackingServiceDAO {

    public TrackingNumber nextTrackingNumber(
            String originCountryId,
            String destinationCountryId,
            Double weight,
            String createdAt,
            String customerId,
            String customerName,
            String customerSlug);
}
