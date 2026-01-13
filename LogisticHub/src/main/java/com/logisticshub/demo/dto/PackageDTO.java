package com.logisticshub.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class PackageDTO {
    @NotBlank(message = "destinationDelivery is required")
    private String destinationDelivery;

    @Positive(message = "weight must be > 0")
    private double weight;

    @NotBlank(message = "status is required")
    private String status;

    @NotBlank(message = "deliveryType is required")
    private String deliveryType;


    public PackageDTO(String destinationDelivery, double weight, String status, String deliveryType) {
        this.destinationDelivery = destinationDelivery;
        this.weight = weight;
        this.status = status;
        this.deliveryType = deliveryType;
    }

    public String getDestinationDelivery() {
        return destinationDelivery;
    }

    public void setDestinationDelivery(String destinationDelivery) {
        this.destinationDelivery = destinationDelivery;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
