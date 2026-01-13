package com.logisticshub.demo.model;

import java.util.UUID;

public class Packages {
     String id;
     String destination;
     double weight ;
     String status;
     String deliveryType;

    public Packages(String destination, double weight, String status, String deliveryType) {
        this.id = UUID.randomUUID().toString();
        this.destination = destination;
        this.weight = weight;
        this.status = status;
        this.deliveryType = deliveryType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
