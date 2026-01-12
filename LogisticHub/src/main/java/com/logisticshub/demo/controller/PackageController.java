package com.logisticshub.demo.controller;


import com.logisticshub.demo.dto.PackageDTO;
import com.logisticshub.demo.model.Packages;
import com.logisticshub.demo.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.logisticshub.demo.model.Paths.PACKAGES;

@RestController
@RequestMapping(PACKAGES)
public class PackageController {
    private final PackageService service;

    public PackageController(PackageService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Packages> addBook(@Valid @RequestBody PackageDTO req) {
        Packages created = service.saveAsync(req);
        return ResponseEntity.accepted().body(created);
    }
}
