package com.tracking.service;

import com.tracking.service.model.TrackingNumber;
import com.tracking.service.util.ServiceUtil;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    private ServiceUtil serviceUtil;

    @Autowired
    public TrackingService(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public TrackingNumber getTrackingNumber(
            String originCountryId,
            String destinationCountryId,
            Double weight,
            String createdAt,
            String customerId,
            String customerName,
            String customerSlug) {

        TrackingNumber trackingNumber = new TrackingNumber();
        trackingNumber.setNumber(serviceUtil.generateTrackingNumber());
        trackingNumber.setCreatedAt(serviceUtil.generateDate(new Date()));
        return trackingNumber;
    }

    public TrackingNumber getTrackingNumber1() {

        TrackingNumber trackingNumber = new TrackingNumber();
        trackingNumber.setNumber(serviceUtil.generateTrackingNumber());
        trackingNumber.setCreatedAt(serviceUtil.generateDate(new Date()));
        return trackingNumber;
    }
}
