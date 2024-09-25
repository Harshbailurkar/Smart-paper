package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.DifficultyLevel;
import com.example.smart_paper.service.AdminMaster.DifficultyLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/difficulty-levels")
public class DifficultyLevelController {

    @Autowired
    private DifficultyLevelService difficultyLevelService;

    @PostMapping
    public ResponseEntity<DifficultyLevel> createDifficultyLevel(@RequestBody DifficultyLevel difficultyLevel) {

        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        DifficultyLevel savedDifficultyLevel = difficultyLevelService.save(difficultyLevel, username);
        return ResponseEntity.ok(savedDifficultyLevel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DifficultyLevel> updateDifficultyLevel(@PathVariable Long id,
                                                                 @RequestBody DifficultyLevel updatedDifficultyLevel,
                                                                 @RequestHeader("username") String username) {
        DifficultyLevel updated = difficultyLevelService.update(id, updatedDifficultyLevel, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DifficultyLevel> getDifficultyLevel(@PathVariable Long id) {
        Optional<DifficultyLevel> difficultyLevel = difficultyLevelService.getById(id);
        return difficultyLevel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDifficultyLevel(@PathVariable Long id) {
        difficultyLevelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
