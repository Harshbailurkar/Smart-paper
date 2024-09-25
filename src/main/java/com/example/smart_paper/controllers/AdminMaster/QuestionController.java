package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.Question;
import com.example.smart_paper.service.AdminMaster.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Create a new question
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question,
                                                   @RequestHeader("username") String username)
    {
        Question savedQuestion = questionService.save(question, username);
        return ResponseEntity.ok(savedQuestion);
    }

    // Update an existing question
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id,
                                                   @RequestBody Question updatedQuestion,
                                                   @RequestHeader("username") String username) {
        Question updated = questionService.update(id, updatedQuestion, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Get a question by id
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        Optional<Question> question = questionService.getById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Soft delete a question
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteQuestion(@PathVariable Long id) {
        questionService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
