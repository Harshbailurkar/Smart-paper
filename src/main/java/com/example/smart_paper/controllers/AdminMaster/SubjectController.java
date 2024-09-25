package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.Subject;
import com.example.smart_paper.service.AdminMaster.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // Create a new Subject
    @PostMapping("/add")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        subject.setUsername(loggedInUsername);
        Subject savedSubject = subjectService.saveSubject(subject);
        return ResponseEntity.ok(savedSubject);
    }

    // Get all Subjects
    @PostMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    // Get a Subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a Subject by ID
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
        updatedSubject.setUsername("automatic_username");  // Replace with actual logic to get username
        Subject subject = subjectService.updateSubject(id, updatedSubject);
        return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.notFound().build();
    }

    // Delete a Subject by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
