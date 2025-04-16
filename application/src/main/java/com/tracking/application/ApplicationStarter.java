package com.tracking.application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.tracking.*")
@EnableScheduling
public class ApplicationStarter {
    private static final String SERVER_PORT = "server.port";
    private static final String SERVER_CONTEXT_PATH = "server.servlet.context-path";
    private static final Logger log = LoggerFactory.getLogger(ApplicationStarter.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ApplicationStarter.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";

        log.info(
                "\n----------------------------------------------------------\n\t"
                        + "Application '{}' is running! Access URLs:\n\t"
                        + "Local: \t\t{}://localhost:{}\n\t"
                        + "External: \t{}://{}:{}\n\t"
                        + "Swagger: \t{}://{}:{}{}/{}\n\t"
                        + "Profile(s): \t{}\n\t"
                        + "Préfix: \t{}\n\t"
                        + "\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty(SERVER_PORT),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty(SERVER_PORT),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty(SERVER_PORT),
                env.getProperty(SERVER_CONTEXT_PATH),
                "swagger-ui.html",
                env.getActiveProfiles(),
                env.getProperty("environment.url.prefix"));
    }
}
