package com.tracking.service.dao.impl;

import com.tracking.service.dao.TrackingServiceDAO;
import com.tracking.service.model.TrackingNumber;
import com.tracking.service.util.ServiceUtil;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrackingServiceDAOImpl implements TrackingServiceDAO {

    private ServiceUtil serviceUtil;

    @Autowired
    public TrackingServiceDAOImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public TrackingNumber nextTrackingNumber(
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
}
