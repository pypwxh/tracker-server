package com.yfyld.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.yfyld"})
public class TrackerCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackerCoreApplication.class, args);
    }

}
