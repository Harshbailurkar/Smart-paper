package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.Standard;
import com.example.smart_paper.repository.AdminMaster.StandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StandardService {

    @Autowired
    private StandardRepository standardRepository;

    // Save a new Standard
    public Standard saveStandard(Standard standard) {
        standard.setCreationDate(new Date());
        standard.setUpdationDate(new Date());
        return standardRepository.save(standard);
    }

    // Get all Standards
    public List<Standard> getAllStandards() {
        return standardRepository.findAll();
    }

    // Get a Standard by ID
    public Optional<Standard> getStandardById(Long id) {
        return standardRepository.findById(id);
    }

    // Update a Standard by ID
    public Standard updateStandard(Long id, Standard updatedStandard) {
        Optional<Standard> existingStandardOptional = standardRepository.findById(id);
        if (existingStandardOptional.isPresent()) {
            Standard existingStandard = existingStandardOptional.get();

            // Update fields

            if (updatedStandard.getName() != null) {
                existingStandard.setName(updatedStandard.getName());
            }

            if (updatedStandard.getDescription() != null) {
                existingStandard.setDescription(updatedStandard.getDescription());
            }
            if (updatedStandard.getDisplayName() != null) {
                existingStandard.setDisplayName(updatedStandard.getDisplayName());
            }

            existingStandard.setUpdationDate(new Date());  // Update the date

            return standardRepository.save(existingStandard);  // Save the updated standard
        }
        return null;  // Return null if not found
    }

    // Delete a Standard by ID
    public void deleteStandard(Long id) {
        standardRepository.deleteById(id);
    }
}
