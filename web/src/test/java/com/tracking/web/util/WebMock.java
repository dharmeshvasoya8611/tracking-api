package com.tracking.web.util;

import com.tracking.service.model.TrackingNumber;
import com.tracking.service.util.ServiceUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

public class WebMock {

    private ServiceUtil serviceUtil;

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
        trackingNumber.setNumber(serviceUtil.generateTrackingNumber());
        trackingNumber.setCreatedAt(serviceUtil.generateDate(new Date()));
        return trackingNumber;
    }
}
