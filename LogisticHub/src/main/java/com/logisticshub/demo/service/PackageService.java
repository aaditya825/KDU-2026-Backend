package com.logisticshub.demo.service;

import com.logisticshub.demo.dto.PackageDTO;
import com.logisticshub.demo.model.Packages;
import com.logisticshub.demo.repository.PackagesRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class PackageService {
    @Autowired
    private PackagesRepository repo;
    private static final double RATE_PER_KG = 2.50;
    private final ExecutorService executor = Executors.newFixedThreadPool(3);


    public Packages save(PackageDTO packages) {
        return repo.save(packages);
    }

    @PreAuthorize("hasAnyRole('MANAGER','DRIVER')")
    public double getTotalProjectedRevenue() {
        return repo.findAll().stream()
                .filter(p -> p.getStatus() != null && p.getStatus().equalsIgnoreCase("SORTED"))
                .mapToDouble(p -> p.getWeight() * RATE_PER_KG)
                .sum();
    }

    public Packages saveAsync(PackageDTO dto) {
        Packages pkg = repo.save(dto);

        if(pkg.getStatus().equalsIgnoreCase("PENDING"))
        {
            executor.submit(() -> {
                System.out.println("Scanning Package " + pkg.getId() + "...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                pkg.setStatus("SORTED");
                System.out.println("Package " + pkg.getId() + " sorted!");
            });
        }

        return pkg;
    }

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
