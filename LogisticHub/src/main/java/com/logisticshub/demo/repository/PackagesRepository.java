package com.logisticshub.demo.repository;
import com.logisticshub.demo.dto.PackageDTO;
import com.logisticshub.demo.model.Packages;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PackagesRepository {
    private final List<Packages> packagesStore = new ArrayList<>();


    public PackagesRepository() {
        packagesStore.add(new Packages("New York", 10.0, "SORTED", "EXPRESS"));
        packagesStore.add(new Packages("Berlin", 5.0, "PENDING", "STANDARD"));
        packagesStore.add(new Packages("Tokyo", 8.0, "SORTED", "STANDARD"));
        packagesStore.add(new Packages("Paris", 12.5, "SORTED", "EXPRESS"));
        packagesStore.add(new Packages("London", 3.0, "PENDING", "STANDARD"));
    }

    public Packages save(PackageDTO packagesDto)
    {
        Packages packages = new Packages(packagesDto.getDestinationDelivery(),
                packagesDto.getWeight(),packagesDto.getStatus(),packagesDto.getDeliveryType());
        packagesStore.add(packages);
        return packages;
    }

    public List<Packages> findAll() {
        return new ArrayList<>(packagesStore);
    }
}