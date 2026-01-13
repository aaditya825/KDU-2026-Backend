package com.logisticshub.demo.controller;

import com.logisticshub.demo.service.PackageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.logisticshub.demo.model.Paths.ANALYTICS;
import static com.logisticshub.demo.model.Paths.REVENUE;

@RestController
@RequestMapping(ANALYTICS)
public class AnalyticsController {

    private final PackageService service;

    public AnalyticsController(PackageService service) {
        this.service = service;
    }

    @GetMapping(REVENUE)
    public Map<String, Double> revenue() {
        double total = service.getTotalProjectedRevenue();
        return Map.of("totalRevenue", total);
    }
}

