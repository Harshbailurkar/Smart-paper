package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.QuestionOptions;
import com.example.smart_paper.service.AdminMaster.QuestionOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/question-options")
public class QuestionOptionsController {

    @Autowired
    private QuestionOptionsService questionOptionsService;

    @PostMapping
    public ResponseEntity<QuestionOptions> createQuestionOptions(@RequestBody QuestionOptions questionOptions,
                                                                 @RequestHeader("username") String username) {
        QuestionOptions savedQuestionOptions = questionOptionsService.save(questionOptions, username);
        return ResponseEntity.ok(savedQuestionOptions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionOptions> updateQuestionOptions(@PathVariable Long id,
                                                                 @RequestBody QuestionOptions updatedQuestionOptions,
                                                                 @RequestHeader("username") String username) {
        QuestionOptions updated = questionOptionsService.update(id, updatedQuestionOptions, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionOptions> getQuestionOptions(@PathVariable Long id) {
        Optional<QuestionOptions> questionOptions = questionOptionsService.getById(id);
        return questionOptions.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionOptions(@PathVariable Long id) {
        questionOptionsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
