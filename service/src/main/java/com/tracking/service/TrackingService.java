package com.tracking.service;

import com.tracking.service.dao.TrackingServiceDAO;
import com.tracking.service.exception.InvalidTrackingNumberException;
import com.tracking.service.exception.TrackingNumberException;
import com.tracking.service.model.TrackingNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    private TrackingServiceDAO trackingServiceDAO;

    @Autowired
    public TrackingService(TrackingServiceDAO trackingServiceDAO) {
        this.trackingServiceDAO = trackingServiceDAO;
    }

    public TrackingNumber nextTrackingNumber(
            String originCountryId,
            String destinationCountryId,
            Double weight,
            String createdAt,
            String customerId,
            String customerName,
            String customerSlug)
            throws TrackingNumberException, InvalidTrackingNumberException {
        return trackingServiceDAO.nextTrackingNumber(
                originCountryId,
                destinationCountryId,
                weight,
                createdAt,
                customerId,
                customerName,
                customerSlug);
    }
}
