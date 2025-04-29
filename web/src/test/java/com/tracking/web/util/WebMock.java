package com.tracking.web.util;

import com.tracking.service.exception.InvalidTrackingNumberException;
import com.tracking.service.exception.TrackingNumberException;
import com.tracking.service.model.TrackingNumber;
import com.tracking.service.util.ServiceUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

public class WebMock {

    private ServiceUtil serviceUtil;

    private static final Log LOG = LogFactory.getLog(WebMock.class);

    public WebMock(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public MultiValueMap<String, String> getqueryParamsToGenerateTrackingNumber() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("origin_country_id", Arrays.asList("MY"));
        map.put("destination_country_id", Arrays.asList("ID"));
        map.put("weight", Arrays.asList("1.234"));
        map.put("created_at", Arrays.asList(serviceUtil.generateDate(new Date())));
        map.put("customer_id", Arrays.asList("de619854-b59b-425e-9db4-943979e1bd49"));
        map.put("customer_name", Arrays.asList("edBox Logistics"));
        map.put("customer_slug", Arrays.asList("redbox-logistics"));
        return CollectionUtils.toMultiValueMap(map);
    }

    public TrackingNumber mockTrackingNumber() {
        TrackingNumber trackingNumber = new TrackingNumber();
        try {
            trackingNumber.setNumber(
                    serviceUtil.generateTrackingNumber(
                            "MY", "ID", "de619854-b59b-425e-9db4-943979e1bd49"));
        } catch (TrackingNumberException | InvalidTrackingNumberException e) {
            LOG.error("mockTrackingNumber(), error:" + e.getMessage());
        }
        trackingNumber.setCreatedAt(serviceUtil.generateDate(new Date()));
        return trackingNumber;
    }
}
