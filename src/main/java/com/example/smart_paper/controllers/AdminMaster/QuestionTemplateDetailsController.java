package com.example.smart_paper.controllers.AdminMaster;

import com.example.smart_paper.models.QuestionTemplateDetails;
import com.example.smart_paper.service.AdminMaster.QuestionTemplateDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/question-template-details")
public class QuestionTemplateDetailsController {

    @Autowired
    private QuestionTemplateDetailsService questionTemplateDetailsService;

    @PostMapping
    public ResponseEntity<QuestionTemplateDetails> createQuestionTemplateDetails(@RequestBody QuestionTemplateDetails questionTemplateDetails,
                                                                                 @RequestHeader("username") String username) {
        QuestionTemplateDetails savedDetails = questionTemplateDetailsService.save(questionTemplateDetails, username);
        return ResponseEntity.ok(savedDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionTemplateDetails> updateQuestionTemplateDetails(@PathVariable Long id,
                                                                                 @RequestBody QuestionTemplateDetails updatedQuestionTemplateDetails,
                                                                                 @RequestHeader("username") String username) {
        QuestionTemplateDetails updated = questionTemplateDetailsService.update(id, updatedQuestionTemplateDetails, username);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionTemplateDetails> getQuestionTemplateDetails(@PathVariable Long id) {
        Optional<QuestionTemplateDetails> questionTemplateDetails = questionTemplateDetailsService.getById(id);
        return questionTemplateDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionTemplateDetails(@PathVariable Long id) {
        questionTemplateDetailsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
