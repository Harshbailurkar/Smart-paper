package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.QuestionType;
import com.example.smart_paper.service.AdminMaster.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/question-types")
public class QuestionTypeController {

    @Autowired
    private QuestionTypeService questionTypeService;

    // Create a new question type
    @PostMapping
    public ResponseEntity<QuestionType> createQuestionType(@RequestBody QuestionType questionType,
                                                           @RequestHeader("username") String username) {
        QuestionType savedQuestionType = questionTypeService.save(questionType, username);
        return ResponseEntity.ok(savedQuestionType);
    }

    // Update an existing question type
    @PutMapping("/{id}")
    public ResponseEntity<QuestionType> updateQuestionType(@PathVariable Long id,
                                                           @RequestBody QuestionType updatedQuestionType,
                                                           @RequestHeader("username") String username) {
        QuestionType updated = questionTypeService.update(id, updatedQuestionType, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Get a question type by id
    @GetMapping("/{id}")
    public ResponseEntity<QuestionType> getQuestionType(@PathVariable Long id) {
        Optional<QuestionType> questionType = questionTypeService.getById(id);
        return questionType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a question type by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionType(@PathVariable Long id) {
        questionTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
