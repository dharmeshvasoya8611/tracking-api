package com.tracking.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;

import com.tracking.service.TrackingService;
import com.tracking.service.util.ServiceUtil;
import com.tracking.web.util.WebMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(
        controllers = TrackingApiControllerImpl.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes = TrackingApiControllerImpl.class)
@Import({ServiceUtil.class})
@ComponentScan(basePackages = "com.tracking")
class TrackingApiControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private TrackingService trackingService;

    @Autowired private ServiceUtil serviceUtil;

    private WebMock webMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webMock = new WebMock(serviceUtil);
    }

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public ServiceUtil serviceUtil() {
            return new ServiceUtil();
        }
    }

    @Test
    void withoutParameter() throws Exception {

        Mockito.when(
                        trackingService.getTrackingNumber(
                                anyString(),
                                anyString(),
                                anyDouble(),
                                anyString(),
                                anyString(),
                                anyString(),
                                anyString()))
                .thenReturn(webMock.mockTrackingNumber());

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/get-tracking-number")
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void withParameter() throws Exception {

        Mockito.when(
                        trackingService.getTrackingNumber(
                                anyString(),
                                anyString(),
                                anyDouble(),
                                anyString(),
                                anyString(),
                                anyString(),
                                anyString()))
                .thenReturn(webMock.mockTrackingNumber());

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/get-tracking-number")
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParams(webMock.getqueryParamsToGenerateTrackingNumber());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}
