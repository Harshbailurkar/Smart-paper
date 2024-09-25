package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.Standard;
import com.example.smart_paper.service.AdminMaster.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/standards")
public class StandardController {

    @Autowired
    private StandardService standardService;

    // Create a new Standard

    @PostMapping("/add")
    public ResponseEntity<Standard> createStandard(@RequestBody Standard standard) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        standard.setUsername(loggedInUsername);
        System.out.println(standard);
        Standard savedStandard = standardService.saveStandard(standard);
        return ResponseEntity.ok(savedStandard);
    }

    // Get all Standards
    @PostMapping
    public ResponseEntity<List<Standard>> getAllStandards() {
        List<Standard> standards = standardService.getAllStandards();
        return ResponseEntity.ok(standards);
    }

    // Get a Standard by ID
    @GetMapping("/{id}")
    public ResponseEntity<Standard> getStandardById(@PathVariable Long id) {
        Optional<Standard> standard = standardService.getStandardById(id);
        return standard.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Standard by ID
    @PutMapping("/{id}")
    public ResponseEntity<Standard> updateStandard(@PathVariable Long id, @RequestBody Standard updatedStandard) {
        updatedStandard.setUsername("automatic_username");  // Replace with actual logic to get username
        Standard standard = standardService.updateStandard(id, updatedStandard);
        return standard != null ? ResponseEntity.ok(standard) : ResponseEntity.notFound().build();
    }

    // Delete a Standard by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStandard(@PathVariable Long id) {
        standardService.deleteStandard(id);
        return ResponseEntity.noContent().build();
    }
}
