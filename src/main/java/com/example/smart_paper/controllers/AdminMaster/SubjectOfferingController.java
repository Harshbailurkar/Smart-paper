package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.SubjectOffering;
import com.example.smart_paper.service.AdminMaster.SubjectOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/subject-offerings")
public class SubjectOfferingController {

    @Autowired
    private SubjectOfferingService subjectOfferingService;

    @PostMapping
    public ResponseEntity<SubjectOffering> createSubjectOffering(@RequestBody SubjectOffering subjectOffering,
                                                                 @RequestHeader("username") String username) {
        SubjectOffering savedSubjectOffering = subjectOfferingService.save(subjectOffering, username);
        return ResponseEntity.ok(savedSubjectOffering);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectOffering> updateSubjectOffering(@PathVariable Long id,
                                                                 @RequestBody SubjectOffering updatedSubjectOffering,
                                                                 @RequestHeader("username") String username) {
        SubjectOffering updated = subjectOfferingService.update(id, updatedSubjectOffering, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectOffering> getSubjectOffering(@PathVariable Long id) {
        Optional<SubjectOffering> subjectOffering = subjectOfferingService.getById(id);
        return subjectOffering.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubjectOffering(@PathVariable Long id) {
        subjectOfferingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
