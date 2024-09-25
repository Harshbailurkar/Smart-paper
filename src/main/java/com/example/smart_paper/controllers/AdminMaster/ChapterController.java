package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.Chapter;
import com.example.smart_paper.service.AdminMaster.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    // Create new chapter
    @PostMapping
    public ResponseEntity<Chapter> createChapter(@RequestBody Chapter chapter) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Chapter savedChapter = chapterService.save(chapter, username);
        return ResponseEntity.ok(savedChapter);
    }

    // Update existing chapter
    @PutMapping("/{id}")
    public ResponseEntity<Chapter> updateChapter(@PathVariable Long id, @RequestBody Chapter updatedChapter) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Chapter updated = chapterService.update(id, updatedChapter, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Get chapter by id
    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getChapter(@PathVariable Long id) {
        Optional<Chapter> chapter = chapterService.getById(id);
        return chapter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete chapter
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        chapterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
