package com.example.smart_paper.service.AdminMaster;

import com.example.smart_paper.models.DifficultyLevel;
import com.example.smart_paper.repository.AdminMaster.DifficultyLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DifficultyLevelService {

    @Autowired
    private DifficultyLevelRepository difficultyLevelRepository;

    public DifficultyLevel save(DifficultyLevel difficultyLevel, String username) {
        difficultyLevel.setUsername(username);
        return difficultyLevelRepository.save(difficultyLevel);
    }

    public DifficultyLevel update(Long id, DifficultyLevel updatedDifficultyLevel, String username) {
        Optional<DifficultyLevel> existingDifficultyLevelOpt = difficultyLevelRepository.findById(id);
        if (existingDifficultyLevelOpt.isPresent()) {
            DifficultyLevel existingDifficultyLevel = existingDifficultyLevelOpt.get();

            existingDifficultyLevel.setLevel(updatedDifficultyLevel.getLevel());
            existingDifficultyLevel.setActive(updatedDifficultyLevel.isActive());
            existingDifficultyLevel.setSortOrder(updatedDifficultyLevel.getSortOrder());
            existingDifficultyLevel.setUsername(username);

            return difficultyLevelRepository.save(existingDifficultyLevel);
        }
        return null;
    }

    public Optional<DifficultyLevel> getById(Long id) {
        return difficultyLevelRepository.findById(id);
    }

    public void deleteById(Long id) {
        difficultyLevelRepository.deleteById(id);
    }
}
